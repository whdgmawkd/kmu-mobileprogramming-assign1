package com.test.mobileprogrammingassign1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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

        setTitle(R.string.title_activity_login);

        database = Room.databaseBuilder(this, AppDatabase.class, getResources().getString(R.string.db_name))
                .allowMainThreadQueries()
                .build();

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        bLogin = findViewById(R.id.button_login);
        bRegister = findViewById(R.id.button_register);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check filed
                boolean isError = false;
                if (TextUtils.isEmpty(username.getText())) {
                    // empty username field
                    username.setError(getResources().getString(R.string.error_username_empty));
                    isError = true;
                }
                if (TextUtils.isEmpty(password.getText())) {
                    // empty password field
                    password.setError(getResources().getString(R.string.error_password_empty));
                    isError = true;
                }
                // nothing to do.
                if (isError)
                    return;

                // query info
                Item userInfo = database.getItemDAO().getItemByLoginInfo(username.getText().toString(), password.getText().toString());
                if (userInfo == null) {
                    AlertDialog.Builder dialogLoginBuilder = new AlertDialog.Builder(LoginActivity.this, R.style.Theme_AppCompat_Light_Dialog);
                    AlertDialog dialogLogin = dialogLoginBuilder.setTitle(R.string.dialog_title_login_incorrent)
                            .setMessage(R.string.error_login_incorrect)
                            .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).create();
                    dialogLogin.show();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
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
