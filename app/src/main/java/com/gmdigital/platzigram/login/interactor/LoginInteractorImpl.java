package com.gmdigital.platzigram.login.interactor;

import com.gmdigital.platzigram.login.presenter.LoginPresenter;
import com.gmdigital.platzigram.login.repository.LoginRepository;
import com.gmdigital.platzigram.login.repository.LoginRepositoryImpl;

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter presenter;
    private LoginRepository repository;
    public LoginInteractorImpl(LoginPresenter presenter) {
        this. presenter= presenter;
        repository = new LoginRepositoryImpl(presenter);


    }

    @Override
    public void sigIn(String username, String password) {

        repository.signIn(username,password);
    }
}
