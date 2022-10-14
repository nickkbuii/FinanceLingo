package com.financelingo.financelingo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_SETTING = 0;
    //define buttons to switch to create or login screen
    Button switchToCreate;
    Button switchToLogin;
    boolean sentToSettings;
    //initialize google sign in variables
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set view to activity_main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        //button listener to switch to create account screen
        switchToCreate = findViewById(R.id.createButton);
        switchToCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities(MainActivity.this, Create.class);
            }
        });

        //button listener to switch to login screen
        switchToLogin = findViewById(R.id.loginButton);
        switchToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities(MainActivity.this, Login.class);
            }
        });

        //google sign in
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.hiphop);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    //method to switch screens
    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}