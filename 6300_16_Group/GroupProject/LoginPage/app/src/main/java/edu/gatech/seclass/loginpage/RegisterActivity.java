package edu.gatech.seclass.loginpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText tName = (EditText) findViewById(R.id.tName);
        final EditText tUsername = (EditText) findViewById(R.id.tUsername);
        final EditText tPassword = (EditText) findViewById(R.id.tPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

    }
}
