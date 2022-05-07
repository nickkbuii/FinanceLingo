package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_SETTING = 0;
    Button switchToCreate;
    Button switchToLogin;
    boolean sentToSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToCreate = findViewById(R.id.createButton);
        switchToCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities(create.class);
            }
        });
        switchToLogin = findViewById(R.id.loginButton);
        switchToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities(login.class);
            }
        });
    }


    private void switchActivities(Class c){
        Intent switchActivityIntent = new Intent (this, c);
        startActivity(switchActivityIntent);
    }

}