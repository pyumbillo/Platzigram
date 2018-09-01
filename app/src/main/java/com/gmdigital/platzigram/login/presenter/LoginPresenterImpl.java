package com.gmdigital.platzigram.login.presenter;

import android.app.Activity;

import com.gmdigital.platzigram.login.interactor.LoginInteractor;
import com.gmdigital.platzigram.login.interactor.LoginInteractorImpl;
import com.gmdigital.platzigram.login.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenterImpl implements LoginPresenter{
    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password,Activity activity,FirebaseAuth firebaseAuth) {
        loginView.disableInputs();
        loginView.showProgressBar();

        interactor.sigIn(username,password,activity,firebaseAuth);

    }

    @Override
    public void loginSuccess() {
        loginView.goHome();

        loginView.hideProgessBar();
    }

    @Override
    public void loginError(String error) {

        loginView.enableInputs();
        loginView.hideProgessBar();
        loginView.loginError(error);

    }
}
