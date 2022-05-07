package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class create extends AppCompatActivity {

    Users user;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        user = new Users();
        json = new JSONObject();

        if(!checkPermission()){
            requestPermissions();
        }
    }

    boolean checkPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            return Environment.isExternalStorageManager();
        }
        else{
            int readcheck = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        }
    }

    public void makeAcc(View v) throws JSONException{
        TextView t = findViewById(R.id.user);
        String username = t.getText().toString();

        t = findViewById(R.id.pw);
        String password = t.getText().toString();

        Log.d("info", username);
        Log.d("info", password);

    }

}