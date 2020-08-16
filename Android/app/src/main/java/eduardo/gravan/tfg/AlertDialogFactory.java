package eduardo.gravan.tfg;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Clase encargada de crear AlerDialogs basados en el fichero de Layout "progress_alert_dialog".
 * Estos AlertDialogs tienen un mensaje personalizado que debe ser pasado en el constructor.
 */
public class AlertDialogFactory {
    private Context context;
    private String message;

    public AlertDialogFactory(Context context, String message) {
        this.message = message;
        this.context = context;
    }

    /**
     * Crea una instancia del AlertDialog basado en el fichero de Layout "progress_alert_dialog" y la devuelve.
     * @return AlertDialog con el texto pasado en el constructor
     */
    public AlertDialog create(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.progress_alert_dialog, null);
        TextView messageView = (TextView) customView.findViewById(R.id.loading_msg);
        builder.setView(customView);
        messageView.setText(message);

        return builder.create();
    }
}
