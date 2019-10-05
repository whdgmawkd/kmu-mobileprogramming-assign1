package com.test.mobileprogrammingassign1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.mobileprogrammingassign1.models.Item;

public class RegisterActivity extends AppCompatActivity {

    private Button bSubmit;

    private EditText tUsername;
    private EditText tPassword;
    private EditText tName;
    private EditText tPhone;
    private EditText tAddress;

    private RadioGroup rgPrivacy;

    private TextView tPrivacyError;

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        database = Room.databaseBuilder(this, AppDatabase.class, getResources().getString(R.string.db_name))
                .allowMainThreadQueries()
                .build();

        rgPrivacy = findViewById(R.id.radio_group_privacy);

        tPrivacyError = findViewById(R.id.privacy_error);

        tUsername = findViewById(R.id.register_username);
        tPassword = findViewById(R.id.register_password);
        tName = findViewById(R.id.register_name);
        tPhone = findViewById(R.id.register_phone);
        tAddress = findViewById(R.id.register_address);

        bSubmit = findViewById(R.id.button_submit);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check userinfo
                Item userInfo = new Item(tUsername.getText().toString(), tPassword.getText().toString(), tName.getText().toString(), tPhone.getText().toString(), tAddress.getText().toString());
                Item checkInfo = database.getItemDAO().getItemByUsername(userInfo.getUsername());
                if (checkInfo != null) {
                    // already used username
                    tUsername.setError(getResources().getString(R.string.error_username_in_use));
                    tUsername.requestFocus();
                    return;
                }
                // check privacy agree
                int radioId = rgPrivacy.getCheckedRadioButtonId();
                // no radio checked or diagree
                if(radioId == -1 || radioId == R.id.radio_disagree) {
                    tPrivacyError.setText(getResources().getString(R.string.error_privacy));
                    tPrivacyError.setVisibility(View.VISIBLE);
                    rgPrivacy.requestFocus();
                    return;
                }
                // no issue. register user.
                database.getItemDAO().insert(userInfo);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // back icon on action bar
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
