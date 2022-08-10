package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
    private Button classCodeConfButton;

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
        classCodeConfButton = findViewById(R.id.classCodeConfButton);

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

        emailConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(changeEmail(view)) {
                    changeEmailButton.setVisibility(View.VISIBLE);
                    changeEmailType.setVisibility(View.GONE);
                    emailConfirm.setVisibility(View.GONE);
                }
            }
        });

        passwordConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                changePasswordButton.setVisibility(View.VISIBLE);
                changePasswordType.setVisibility(View.GONE);
                passwordConfirm.setVisibility(View.GONE);
            }
        });

        classCodeConfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClass(view);
            }
        });
    }


    public boolean changeEmail(View v){

        //GET EMAIL FROM USER
        //PASS INTO UPDATE EMAIL
        String email = changeEmailType.getText().toString().trim();
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            db.updateEmail(AccSettings.this, email);
            changeEmailType.setText("");
            return true;
        }
        else {
            changeEmailType.setError("Please provide a valid email");
            changeEmailType.requestFocus();
            changeEmailType.setText("");
            return false;
        }
    }

<<<<<<< HEAD
    public boolean changePw(View v){
        return false;
    }

    public void addClass(View v){
        db.addStudent();
    }
    
=======
    public void switchToHome(View view){
        switchActivities(AccSettings.this, Lessons.class);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
>>>>>>> 5b1f520e6535450e0e1b3bf9fa3d22d680aa70ef
}
