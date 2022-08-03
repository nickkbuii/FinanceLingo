package com.financelingo.financelingo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private Database db;
    private Button changeEmailButton;
    private Button changePasswordButton;
    private EditText changeEmailType;
    private EditText changePasswordType;
    private Button emailConfirm;
    private Button passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        changeEmailButton = findViewById(R.id.changeEmail);
        changePasswordButton = findViewById(R.id.changePassword);
        changeEmailType = findViewById(R.id.changeEmailType);
        changePasswordType = findViewById(R.id.changePasswordType);
        emailConfirm = findViewById(R.id.emailConfirm);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        db = new Database();


        changeEmailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                changeEmailButton.setVisibility(View.GONE);
                changeEmailType.setVisibility(View.VISIBLE);
                emailConfirm.setVisibility(View.VISIBLE);
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                changePasswordButton.setVisibility(View.GONE);
                changePasswordType.setVisibility(View.VISIBLE);
                passwordConfirm.setVisibility(View.VISIBLE);
            }
        });
    }

    public void changeEmail(View v){
        String newEmail = ((TextView)findViewById(R.id.changeEmailType)).getText().toString();
        db.updateEmail(AccSettings.this, newEmail);
    }

    
}
