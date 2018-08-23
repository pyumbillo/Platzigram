package com.gmdigital.platzigram.login.view;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gmdigital.platzigram.R;
import com.gmdigital.platzigram.login.presenter.LoginPresenter;
import com.gmdigital.platzigram.login.presenter.LoginPresenterImpl;
import com.gmdigital.platzigram.view.ContainerActivity;
import com.gmdigital.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private TextInputEditText username,password;
    private Button login;
    private ProgressBar progressbarLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username= (TextInputEditText) findViewById(R.id.username);
        password =(TextInputEditText) findViewById(R.id.password);
        login =(Button) findViewById(R.id.login);
        progressbarLogin =(ProgressBar) findViewById(R.id.progressbarLogin);
        hideProgessBar();

        presenter = new LoginPresenterImpl(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signIn(username.getText().toString(),password.getText().toString());
            }
        });

    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
    progressbarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgessBar() {
        progressbarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.login_error)+error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this,ContainerActivity.class);
        startActivity(intent);
    }

}
