package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import database.DatabaseHelper;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        DatabaseHelper db = new DatabaseHelper(login.this);

        TextView t = findViewById(R.id.user);
        String username = t.getText().toString();

        t = findViewById(R.id.pw);
        String password = t.getText().toString();

        db.getUser()

    }
}