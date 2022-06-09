package com.financelingo.financelingo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_SETTING = 0;
    Button switchToCreate;
    Button switchToLogin;

    boolean sentToSettings;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToCreate = findViewById(R.id.createButton);
        switchToCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities(MainActivity.this, Create.class);
            }
        });
//        switchToLogin = findViewById(R.id.loginButton);
//        switchToLogin.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                switchActivities(MainActivity.this, Login.class);
//            }
//        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);

    }




    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }



}