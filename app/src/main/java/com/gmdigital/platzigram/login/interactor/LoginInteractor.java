package com.gmdigital.platzigram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginInteractor {
    void sigIn(String username,String password,Activity activity,FirebaseAuth firebaseAuth);
}
