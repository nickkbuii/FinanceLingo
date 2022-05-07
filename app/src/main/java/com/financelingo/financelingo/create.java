package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import database.DatabaseHelper;
import database.User;

public class create extends AppCompatActivity {

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
            Toast.makeText(create.this, "Account Created", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(create.this, "Create Failed", Toast.LENGTH_SHORT).show();
        }
        Log.d("testing", db.getUser("testing", "testing").toString());
    }

}