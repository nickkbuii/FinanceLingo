package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import database.DatabaseHelper;
import database.User;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        DatabaseHelper db = new DatabaseHelper();

        TextView t = findViewById(R.id.loginUsernameInput);
        String username = t.getText().toString();

        t = findViewById(R.id.loginPasswordInput);
        String password = t.getText().toString();
        User user = new User(username, password);
        //db.getUser(user);

    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}