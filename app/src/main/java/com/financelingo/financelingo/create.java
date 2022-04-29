package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class create extends AppCompatActivity {
    Button create;
    Users user;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        user = new Users();
        json = new JSONObject();
    }

    public void makeAcc(View v) throws JSONException, IOException {
        TextView t = findViewById(R.id.user);
        String username = t.getText().toString();

        t = findViewById(R.id.pw);
        String password = t.getText().toString();

        Log.d("info", username);
        Log.d("info", password);

        json.put("Username", "Test");
        json.put("Password", "test");
        System.out.println("here");


        try(FileWriter file = new FileWriter("data/users.json", false)){
            file.write(json.toString(4));
            file.flush();
        }
        catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }

}