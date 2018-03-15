package tk.developeramit.realtimenoticeboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.transition.Fade.IN;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button loginLink = (Button) findViewById(R.id.link_button_to_login);

        loginLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent linkToLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(linkToLogin);
            }
        });
    }

    public void onRegister(View view){
        String first_name = ((EditText) findViewById(R.id.first_name)).getText().toString();
        String last_name = ((EditText) findViewById(R.id.last_name)).getText().toString();
        String username = ((EditText) findViewById(R.id.username_resister)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_register)).getText().toString();

        Toast.makeText(this, first_name+", "+last_name+", "+", "+username+", "+password, Toast.LENGTH_SHORT).show();

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("registration",first_name,last_name,username,password);

    }
}