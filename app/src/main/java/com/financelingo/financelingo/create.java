package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import database.DatabaseHelper;
import database.User;

public class create extends AppCompatActivity {
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

    }

    public void makeAcc(View v){
        TextView t = findViewById(R.id.user);
        String username = t.getText().toString();

        t = findViewById(R.id.pw);
        String password = t.getText().toString();

        User user = new User(username, password);
        DatabaseHelper db = new DatabaseHelper(create.this);
        if(db.addUser(user)){
            Log.d("info", user.toString());
        }
        else{
            Log.d("info", "No");
        }



    }

}