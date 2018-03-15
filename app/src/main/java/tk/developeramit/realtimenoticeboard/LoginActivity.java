package tk.developeramit.realtimenoticeboard;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.type;

public class LoginActivity extends AppCompatActivity {
    public String usernameLogin = "", passwordLogin = "", type = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button link_button = (Button) findViewById(R.id.link_button);

        link_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent linkToRegister = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(linkToRegister);
            }
        });

    }

    public void onLogin(View view) {
        //Username
        usernameLogin = ((EditText) findViewById(R.id.username_login)).getText().toString();
        //Password
        passwordLogin = ((EditText) findViewById(R.id.password_login)).getText().toString();

        //Toast.makeText(this, usernameLogin + " => " + passwordLogin, Toast.LENGTH_SHORT).show();

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, usernameLogin, passwordLogin);
    }
}
