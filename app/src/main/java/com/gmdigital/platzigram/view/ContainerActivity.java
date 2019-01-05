package com.gmdigital.platzigram.view;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.gmdigital.platzigram.R;
import com.gmdigital.platzigram.login.view.CreateAccountActivity;
import com.gmdigital.platzigram.login.view.LoginActivity;
import com.gmdigital.platzigram.post.view.HomeFragment;
import com.gmdigital.platzigram.view.fragments.ProfileFragment;
import com.gmdigital.platzigram.view.fragments.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContainerActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        firebaseInitialize();
        BottomNavigationView  bottomBar = (BottomNavigationView) findViewById(R.id.bottonbar);
        bottomBar.setSelectedItemId(R.id.home);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit();

                        break;
                    case R.id.search:
                        SearchFragment searcFragment = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searcFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                            break;
                    case R.id.profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;



                }
                //updateNavigationBarState(item.getItemId());

                return true;
            }
        });
    }
    private void firebaseInitialize()
    {
        firebaseAuth =FirebaseAuth.getInstance();
        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser!=null)
                {
                    Toast.makeText(ContainerActivity.this,"Cuenta creada Exitosamente",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(ContainerActivity.this,"Ocurrio un Error al crear una cuenta",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mSignOut:
                firebaseAuth.signOut();
                if (LoginManager.getInstance()!=null) {
                    LoginManager.getInstance().logOut();
                }
                Toast.makeText(this,"Sesion Cerrada",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ContainerActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.mAbout:
                Toast.makeText(this,"Platzigram by Patricio Yumbillo",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
