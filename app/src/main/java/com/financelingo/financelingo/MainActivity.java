package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

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
                switchActivities(MainActivity.this, Create.class);
            }
        });
        switchToLogin = findViewById(R.id.loginButton);
        switchToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities(MainActivity.this, Login.class);
            }
        });
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }



}