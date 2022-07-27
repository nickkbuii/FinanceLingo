package com.financelingo.financelingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Map;

import Global.Global;
import database.Database;
import database.User;

public class Login extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    User tempUser;
    Global global;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //Create Login Page
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        global = new Global();

        db = new Database();
    }

    public void login(View v){
        TextView text = findViewById(R.id.loginUsernameInput);
        String username = text.getText().toString();//username text field input
        text = findViewById(R.id.loginPasswordInput);
        String password = text.getText().toString();//Password text field input

        //LOGINS IN TO THE FIREBASE AUTHENTICATION
        if(db.login(Login.this, username, password)){
            switchActivities(Login.this, Lessons.class);
        }
    }



    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}