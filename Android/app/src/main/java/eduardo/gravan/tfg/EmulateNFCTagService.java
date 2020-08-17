package eduardo.gravan.tfg;

import android.app.Service;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Clase que hereda del servicio de Android encargado de la emulación de tarjetas NFC basadas en host (HCE).
 * La clase se encarga de hacer el handshake entre un lector de tarjetas NFC y el emulador de tarjetas, sirviendo
 * un mensaje NDEF con la información del empleado.
 */
public class EmulateNFCTagService extends HostApduService {
    private final byte[] NDEF_ID = {
            (byte) 0xE1,
            (byte) 0x04
    };

    private final byte[] APDU_SELECT = {
        (byte) 0x00, // CLA	- Class - Class of instruction
        (byte) 0xA4, // INS	- Instruction - Instruction code
        (byte) 0x04, // P1	- Parameter 1 - Instruction parameter 1
        (byte) 0x00, // P2	- Parameter 2 - Instruction parameter 2
        (byte) 0x07, // Lc field    - Number of bytes present in the data field of the command
        (byte) 0xD2,
        (byte) 0x76,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x85,
        (byte) 0x01,
        (byte) 0x01, // NDEF Tag Application name
        (byte) 0x00 // Le field	- Maximum number of bytes expected in the data field of the response to the command
    };

    private final byte[] CAPABILITY_CONTAINER_OK = {
            (byte) 0x00, // CLA	- Class - Class of instruction
            (byte) 0xa4, // INS	- Instruction - Instruction code
            (byte) 0x00, // P1	- Parameter 1 - Instruction parameter 1
            (byte) 0x0c, // P2	- Parameter 2 - Instruction parameter 2
            (byte) 0x02, // Lc field	- Number of bytes present in the data field of the command
            (byte) 0xe1, (byte) 0x03 // file identifier of the CC file
    };

    private final byte[] READ_CAPABILITY_CONTAINER = {
            (byte) 0x00, // CLA	- Class - Class of instruction
            (byte) 0xb0, // INS	- Instruction - Instruction code
            (byte) 0x00, // P1	- Parameter 1 - Instruction parameter 1
            (byte) 0x00, // P2	- Parameter 2 - Instruction parameter 2
            (byte) 0x0f  // Lc field	- Number of bytes present in the data field of the command
    };

    // Booleano para evitar que se hagan varias lecturas del archivo CC seguidas
    private boolean READ_CAPABILITY_CONTAINER_CHECK = false;

    private final byte[] READ_CAPABILITY_CONTAINER_RESPONSE = {
            (byte) 0x00, (byte) 0x11, // CCLEN length of the CC file
            (byte) 0x20, // Mapping Version 2.0
            (byte) 0xFF, (byte) 0xFF, // MLe maximum
            (byte) 0xFF, (byte) 0xFF, // MLc maximum
            (byte) 0x04, // T field of the NDEF File Control TLV
            (byte) 0x06, // L field of the NDEF File Control TLV
            (byte) 0xE1, (byte) 0x04, // File Identifier of NDEF file
            (byte) 0xFF, (byte) 0xFE, // Maximum NDEF file size of 65534 bytes
            (byte) 0x00, // Read access without any security
            (byte) 0xFF, // Write access without any security
            (byte) 0x90, (byte) 0x00 // A_OKAY
    };

    private final byte[] NDEF_SELECT_OK = {
            (byte) 0x00, // CLA	- Class - Class of instruction
            (byte) 0xa4, // Instruction byte (INS) for Select command
            (byte) 0x00, // Parameter byte (P1), select by identifier
            (byte) 0x0c, // Parameter byte (P1), select by identifier
            (byte) 0x02, // Lc field	- Number of bytes present in the data field of the command
            (byte) 0xE1, (byte) 0x04 // file identifier of the NDEF file retrieved from the CC file
    };

    private final byte[] NDEF_READ_BINARY = {
            (byte) 0x00, // Class byte (CLA)
            (byte) 0xB0 // Instruction byte (INS) for ReadBinary command
    };

    private final byte[] NDEF_READ_BINARY_NLEN = {
            (byte) 0x00, // Class byte (CLA)
            (byte) 0xB0, // Instruction byte (INS) for ReadBinary command
            (byte) 0x00, (byte) 0x00, // Parameter byte (P1, P2), offset inside the CC file
            (byte) 0x02  // Le field
    };

    private final byte[] APDU_OKAY = {
            (byte) 0x90, // SW1	Status byte 1 - Command processing status
            (byte) 0x00  // SW2	Status byte 2 - Command processing qualifier
    };

    private final byte[] APDU_ERROR = {
            (byte) 0x6A, // SW1	Status byte 1 - Command processing status
            (byte) 0x82  // SW2	Status byte 2 - Command processing qualifier
    };

    private NdefMessage ndefMessage;
    private byte[] ndefMessageBytes;
    private byte[] ndefMessageNLEN;

    /**
     * Cuando se lanza un servicio de forma manual, este es el primer método llamado dentro de la clase.
     * El método se encarga de recuperar la información almacenada en el intent, en este caso, el email del usuario
     * que ha iniciado la emulación de tarjetas NFC para servirlo como mensaje NDEF.
     *
     * @param intent intent con el que se lanza el servicio
     * @param flags flags con los que se lanza el servicio
     * @param startId ID del servicio
     * @return int que representa la forma de ejecución del servicio
     */
    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        if (intent.hasExtra("ndefMessage")) {
            ndefMessage = new NdefMessage(createTextRecord("en", intent.getStringExtra("ndefMessage"), NDEF_ID));
            ndefMessageBytes = ndefMessage.toByteArray();
            ndefMessageNLEN = fillByteArrayToFixedDimension(BigInteger.valueOf(ndefMessageBytes.length).toByteArray(), 2);
            Log.i("EmulateNFCTagService", "onStartCommand() | NDEF" + ndefMessage.toString());
        }

        return Service.START_STICKY;
    }

    /**
     * Cuando el teléfono en modo emulación detecta un comando APDU (comando enviado por el lector de tarjetas NFC)
     * se llama a este método. El método analiza qué comando de todos los posibles es y contesta en consecuencia,
     * realizándose el handshake entre el lector y el emulador.
     * <p>
     *     El ciclo de vida normal es
     *     <ul>
     *         <li>1: NDEF Tag Application Select</li>
     *         <li>2: Capability Container Select</li>
     *         <li>3: ReadBinary for CC file</li>
     *         <li>4: NDEF Select</li>
     *         <li>5: Read NLEN of NDEF message</li>
     *         <li>6: Send the NDEF message</li>
     *     </ul>
     * </p>
     * En caso de que lleguen comandos APDU y el servicio no se haya iniciado a través del Intent lanzado al pulsar el
     * botón de emulación, se cancela la lectura de la tarjeta.
     *
     * @param commandApdu array de bytes con comando enviado por el lector
     * @param extras información extra
     * @return array de bytes con el comando de respuesta
     */
    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        Log.i("EmulateNFCTagService", "processCommandApdu() | incoming commandApdu: " + toHex(commandApdu));

        if(ndefMessage == null) {
            Log.i("EmulateNFCTagService", "processCommandApdu() | No NDEF message to write.");
            return APDU_ERROR;
        }

        // First command: NDEF Tag Application Select
        if(Arrays.equals(APDU_SELECT, commandApdu)) {
            Log.i("EmulateNFCTagService", "APDU_SELECT triggered.");
            return APDU_OKAY;
        }

        // Second command: Capability Container Select
        if (Arrays.equals(CAPABILITY_CONTAINER_OK, commandApdu)) {
            Log.i("EmulateNFCTagService", "CAPABILITY_CONTAINER_OK triggered");
            return APDU_OKAY;
        }

        // Third command: ReadBinary for CC file
        if (Arrays.equals(READ_CAPABILITY_CONTAINER, commandApdu) && !READ_CAPABILITY_CONTAINER_CHECK) {
            Log.i("EmulateNFCTagService", "READ_CAPABILITY_CONTAINER triggered");
            READ_CAPABILITY_CONTAINER_CHECK = true;
            return READ_CAPABILITY_CONTAINER_RESPONSE;
        }

        // Fourth command: NDEF Select
        if (Arrays.equals(NDEF_SELECT_OK, commandApdu)) {
            Log.i("EmulateNFCTagService", "NDEF_SELECT_OK triggered");
            return APDU_OKAY;
        }

        // Fifth command: Read NLEN of NDEF message
        if(Arrays.equals(NDEF_READ_BINARY_NLEN, commandApdu)) {
            // Construimos la respuesta
            byte[] response = new byte[ndefMessageNLEN.length + APDU_OKAY.length];
            System.arraycopy(ndefMessageNLEN, 0, response, 0, ndefMessageNLEN.length);
            System.arraycopy(APDU_OKAY, 0, response, ndefMessageNLEN.length, APDU_OKAY.length);

            Log.i("EmulateNFCTagService", "NDEF_READ_BINARY_NLEN triggered");

            READ_CAPABILITY_CONTAINER_CHECK = false;
            return response;
        }

        // Sixth command: Read NDEF message
        if (Arrays.equals(Arrays.copyOfRange(commandApdu, 0, 2), NDEF_READ_BINARY)) {
            int offset = Integer.parseInt(toHex(Arrays.copyOfRange(commandApdu, 2, 4)), 16);
            int length =  Integer.parseInt(toHex(Arrays.copyOfRange(commandApdu, 4, 5)), 16);

            byte[] fullResponse = new byte [ndefMessageNLEN.length + ndefMessageBytes.length];
            System.arraycopy(ndefMessageNLEN, 0, fullResponse, 0, ndefMessageNLEN.length);
            System.arraycopy(ndefMessageBytes, 0, fullResponse, ndefMessageNLEN.length, ndefMessageBytes.length);

            Log.i("EmulateNFCTagService", "NDEF_READ_BINARY triggered");
            Log.i("EmulateNFCTagService", "READ_BINARY - OFFSET: " + offset + " - LEN: " + length);

            byte[] slicedResponse = Arrays.copyOfRange(fullResponse, offset, fullResponse.length);

            // Construimos la respuesta
            int realLength = Math.min(slicedResponse.length, length);
            byte[] response = new byte[realLength + APDU_OKAY.length];

            System.arraycopy(slicedResponse, 0, response, 0, realLength);
            System.arraycopy(APDU_OKAY, 0, response, realLength, APDU_OKAY.length);

            READ_CAPABILITY_CONTAINER_CHECK = false;
            sendBroadcast(true);
            return response;
        }

        Log.e("EmulateNFCTagService", "processCommandApdu() | Cannot recognize APDU command.");
        sendBroadcast(false);
        return APDU_ERROR;
    }

    @Override
    public void onDeactivated(int reason) {
        Log.i("EmulateNFCTagService", "onDeactivated() Fired! Reason: " + reason);
    }

    /**
     * Método encargado de crear el mensaje NDEF a partir de un String
     * @param language string con idioma del mensaje (siempre será "en")
     * @param text string con el texto del mensaje NDEF
     * @param id metadata del identificador
     * @return NdefRecord con el record NDEF construido del mensaje
     */
    private NdefRecord createTextRecord(String language, String text, byte[] id) {
        byte[] languageBytes;
        byte[] textBytes;

        languageBytes = language.getBytes(StandardCharsets.US_ASCII);
        textBytes = text.getBytes(StandardCharsets.UTF_8);

        byte[] recordPayload = new byte[1 + (languageBytes.length & 0x03f) + textBytes.length];

        recordPayload[0] = (byte) (languageBytes.length & 0x03F);

        System.arraycopy(languageBytes, 0, recordPayload, 1, languageBytes.length & 0x03F);
        System.arraycopy(textBytes, 0, recordPayload, 1 + (languageBytes.length & 0x03F), textBytes.length);

        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, id, recordPayload);
    }

    /**
     * Método auxilar encargado de rellenar el array hasta una dimensión fija haciendo uso de recursión
     * @param array array a llenar
     * @param fixedSize tamaño fijo
     * @return array de tamaño fijo
     */
    private byte[] fillByteArrayToFixedDimension(byte[] array, int fixedSize) {
        if (array.length == fixedSize) {
            return array;
        }

        byte[] start = {(byte) 0x00};
        byte[] filledArray = new byte[start.length + array.length];
        System.arraycopy(start, 0, filledArray, 0, start.length);
        System.arraycopy(array, 0, filledArray, start.length, array.length);
        return fillByteArrayToFixedDimension(filledArray, fixedSize);
    }

    private final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    /**
     * Método auxiliar encargado de convertir un array de bytes en un String representando número hexadecimales
     * @param array array de bytes a traducir
     * @return String la traducción a hexadecimal
     */
    private String toHex(byte[] array) {
        StringBuilder result = new StringBuilder();

        for (byte b : array) {
            int firstIndex = ((int) b & 0xF0) >> 4;
            int secondIndex = ((int) b & 0x0F);
            result.append(HEX_CHARS[firstIndex]);
            result.append(HEX_CHARS[secondIndex]);
        }
        return result.toString();
    }

    /**
     * Método encargado de enviar el broadcast. Una vez se ha lanzado el sexto comando APDU (send NDEF message),
     * se envia un broadcast que será recogido por la actividad "EmulateNFCTagActivity" para notificarla de que se
     * ha hecho una lectura correcta.
     *
     * @param success booleano que se enviará en el broadcast. true = correcto, false = error
     */
    private void sendBroadcast(boolean success) {
        Intent intent = new Intent("message");
        intent.putExtra("success", success);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
