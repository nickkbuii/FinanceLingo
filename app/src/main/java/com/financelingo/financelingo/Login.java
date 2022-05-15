package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import database.DatabaseHelper;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        DatabaseHelper db = new DatabaseHelper(Login.this);

        TextView t = findViewById(R.id.loginUsernameInput);
        String username = t.getText().toString();

        t = findViewById(R.id.loginPasswordInput);
        String password = t.getText().toString();

        Log.d("testing", "Hi");
        if(db.getUser(username, password) != null){
            switchActivities(Login.this, Lesson.class);
        }
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}