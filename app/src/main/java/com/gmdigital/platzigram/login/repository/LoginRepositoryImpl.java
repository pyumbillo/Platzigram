package com.gmdigital.platzigram.login.repository;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.gmdigital.platzigram.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepositoryImpl implements  LoginRepository {

    LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String username, String password,Activity activity,FirebaseAuth firebaseAuth) {
        //boolean success= true;
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    presenter.loginSuccess();
                }else
                {
                    presenter.loginError("Ocurrio un error");
                }

            }
        });
      /*  if (success){
            presenter.loginSuccess();
        }
        else {
            presenter.loginError("Ocurrio un error");
        }*/
    }
}
