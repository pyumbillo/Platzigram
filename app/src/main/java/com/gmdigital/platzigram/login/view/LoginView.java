package com.gmdigital.platzigram.login.view;

public interface LoginView {
    void enableInputs();
    void disableInputs();

    void showProgressBar();
    void hideProgessBar();

    void loginError(String error);

    void goCreateAccount();
    void goHome();
}
