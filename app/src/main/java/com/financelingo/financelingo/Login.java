package com.financelingo.financelingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import database.User;

public class Login extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fAuth = FirebaseAuth.getInstance();
    }

    public void login(View v){

        TextView t = findViewById(R.id.loginUsernameInput);
        String username = t.getText().toString();

        t = findViewById(R.id.loginPasswordInput);
        String password = t.getText().toString();
        User user = new User(username, password);

        fAuth.signInWithEmailAndPassword(user.getName()+"@gmail.com", user.getPw()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void loadData(FirebaseUser user){

    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}