package mubstimor.android.kwikorder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mubstimor.android.kwikorder.R;
import mubstimor.android.kwikorder.model.LoginModel;
import mubstimor.android.kwikorder.presenter.LoginPresenter;
import mubstimor.android.kwikorder.view.LoginView;


public class LoginActivity extends AppCompatActivity implements LoginView {
    Button btnLogin;
    EditText txtEmail;
    EditText txtPassword;
    TextView tvResponse;
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
    public void responseReady(String response) {
        progressBar.setVisibility(View.INVISIBLE);
        tvResponse.setText(response);
        btnLogin.setEnabled(true);

        if (response == "Login Successful") {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
