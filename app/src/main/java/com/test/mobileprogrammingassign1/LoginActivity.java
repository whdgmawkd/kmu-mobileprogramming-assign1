package com.test.mobileprogrammingassign1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.mobileprogrammingassign1.models.Item;

public class LoginActivity extends AppCompatActivity {

    private Button bLogin;
    private Button bRegister;

    private EditText username;
    private EditText password;

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .build();

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        bLogin = findViewById(R.id.button_login);
        bRegister = findViewById(R.id.button_register);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText())){
                    // empty field
                    username.setError(getResources().getString(R.string.error_username_empty));
                    return;
                }
                // query info
                Item userinfo = database.getItemDAO().getItemByLoginInfo(username.getText().toString(), password.getText().toString());
                if(userinfo == null){
                    // TODO: 10/4/19 Show Error dialog
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password!", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}
