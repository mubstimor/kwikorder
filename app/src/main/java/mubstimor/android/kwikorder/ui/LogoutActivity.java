package mubstimor.android.kwikorder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import mubstimor.android.kwikorder.R;
import mubstimor.android.kwikorder.util.PreferencesManager;

public class LogoutActivity extends AppCompatActivity {

    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        preferencesManager = new PreferencesManager(getApplicationContext());
        preferencesManager.clear();

        // go to login screen
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
