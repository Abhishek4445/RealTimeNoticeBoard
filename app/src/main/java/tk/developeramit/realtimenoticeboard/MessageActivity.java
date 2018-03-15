package tk.developeramit.realtimenoticeboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {
    String type = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    public void onClickMessage(View view) {
        String message = ((EditText) findViewById(R.id.message)).getText().toString();

        Toast.makeText(this, "Msg: " + message, Toast.LENGTH_SHORT).show();

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, message);
    }
}