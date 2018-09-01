package com.gmdigital.platzigram.login.interactor;

import android.app.Activity;

import com.gmdigital.platzigram.login.presenter.LoginPresenter;
import com.gmdigital.platzigram.login.repository.LoginRepository;
import com.gmdigital.platzigram.login.repository.LoginRepositoryImpl;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter presenter;
    private LoginRepository repository;
    public LoginInteractorImpl(LoginPresenter presenter) {
        this. presenter= presenter;
        repository = new LoginRepositoryImpl(presenter);


    }

    @Override
    public void sigIn(String username, String password,Activity activity,FirebaseAuth firebaseAuth) {

        repository.signIn(username,password,activity,firebaseAuth);
    }
}
