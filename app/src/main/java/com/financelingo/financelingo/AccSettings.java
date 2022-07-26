package com.financelingo.financelingo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import Global.Global;
import database.Database;

public class AccSettings extends AppCompatActivity {
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);

        db = new Database();
    }

    public void changeEmail(View v){

        //GET EMAIL FROM USER
        //PASS INTO UPDATE EMAIL
        db.updateEmail(AccSettings.this, "TestingForNow@gmail.com");
    }

    
}
