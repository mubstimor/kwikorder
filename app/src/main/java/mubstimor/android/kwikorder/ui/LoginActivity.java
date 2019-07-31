package mubstimor.android.kwikorder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mubstimor.android.kwikorder.R;
import mubstimor.android.kwikorder.model.LoginModel;
import mubstimor.android.kwikorder.presenter.LoginPresenter;
import mubstimor.android.kwikorder.util.Constants;
import mubstimor.android.kwikorder.util.PreferencesManager;
import mubstimor.android.kwikorder.view.LoginView;


public class LoginActivity extends AppCompatActivity implements LoginView {
    Button btnLogin;
    EditText txtEmail;
    EditText txtPassword;
    TextView tvResponse;
    ProgressBar progressBar;

    PreferencesManager preferencesManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencesManager = new PreferencesManager(getApplicationContext());
        Log.i("CURRENT USER is ", preferencesManager.getValue(Constants.KEY_USERNAME));
        if(preferencesManager.getValue(Constants.KEY_USERNAME).length() > 1) {
            loadHome();
        }

        txtEmail = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        tvResponse = (TextView) findViewById(R.id.response);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        final LoginView loginView = this;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                btnLogin.setEnabled(false);
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                postLoginData(new LoginModel(email, password), loginView);
            }
        });
    }

    public void postLoginData(LoginModel loginModel, LoginView loginView) {
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.postLoginInfo(loginModel, loginView);
    }

    @Override
    public void responseReady(LoginModel loginResponse) {
        progressBar.setVisibility(View.INVISIBLE);
        btnLogin.setEnabled(true);

        if (loginResponse != null) {
            preferencesManager.setValue(Constants.KEY_USEREMAIL, loginResponse.getEmail());
            preferencesManager.setValue(Constants.KEY_USERNAME, loginResponse.getUsername());
            preferencesManager.setValue(Constants.KEY_USERTOKEN, loginResponse.getToken());
            loadHome();
        } else {
            tvResponse.setText("Invalid login details");
        }
    }

    private void loadHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
