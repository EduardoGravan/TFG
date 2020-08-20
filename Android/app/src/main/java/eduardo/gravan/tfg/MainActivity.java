package eduardo.gravan.tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Clase que representa la actividad de Android principal.
 * Nexo de unión con el resto de menues de la aplicación.
 */
public class MainActivity extends AppCompatActivity {
    private Button readNFCButton;
    private Button emulateNFCButton;
    private Button employeeInfoButton;
    private Button employeeCheckScheduleButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLoginStatus();

        readNFCButton = (Button) findViewById(R.id.readNFCButton);
        emulateNFCButton = (Button) findViewById(R.id.serveNFCTagButton);
        employeeInfoButton = (Button) findViewById(R.id.employeeInfoButton);
        employeeCheckScheduleButton = (Button) findViewById(R.id.employeeCheckScheduleButton);

        if(sharedPreferences.getBoolean("ADMIN",false)) {
            emulateNFCButton.setVisibility(View.GONE);
            employeeCheckScheduleButton.setVisibility(View.GONE);
            readNFCButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    openReadNFCActivity();
                }
            });
            employeeInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    openServeEmployeeInfo();
                }
            });
        }
        else {
            readNFCButton.setVisibility(View.GONE);
            employeeInfoButton.setVisibility(View.GONE);
            emulateNFCButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    openEmulateNFCActivity();
                }
            });
            employeeCheckScheduleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCheckScheduleInfo();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_log_out) {
            clearUserInfo();
        }

        return super.onOptionsItemSelected(item);
    }

    private void openReadNFCActivity() {
        Intent intent = new Intent(this, ReadNFCActivity.class);
        startActivity(intent);
    }

    private void openEmulateNFCActivity() {
        Intent intent = new Intent(this, EmulateNFCTagActivity.class);
        startActivity(intent);
    }

    private void openServeEmployeeInfo() {
        Intent intent = new Intent(this, RegisterEmployeeActivity.class);
        startActivity(intent);
    }

    private void openCheckScheduleInfo() {
        Intent intent = new Intent(this, CheckScheduleActivity.class);
        startActivity(intent);
    }

    private void checkLoginStatus() {
        sharedPreferences = getSharedPreferences("LOGIN_INFO", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("USER", "").equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else {
            Log.i("MainActivity",
                    (sharedPreferences.getBoolean("ADMIN", false) ? "ADMIN " : "EMPLOYEE ")
                            + sharedPreferences.getString("USER", ""));

            Toast.makeText(this, "Bienvenido " + sharedPreferences.getString("USER", "null"), Toast.LENGTH_LONG).show();
        }
    }

    private void clearUserInfo() {
        sharedPreferences.edit().clear().apply();
        finish();
        startActivity(getIntent());
    }
}
