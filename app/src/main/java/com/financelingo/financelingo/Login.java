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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import database.User;

public class Login extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    User tempUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.budgeting_reading);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    public void login(View v){
        String username = "";//username text field input
        String password = "";//Password text field input

        //LOGINS IN TO THE FIREBASE AUTHENTICATION
        if(username.contains("@")){
            fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "LOGGED IN", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            fStore.collection("Emails").document(username).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot doc) {
                    if(doc.exists()){
                        String email = doc.getString("Emails");
                        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Login.this, "LOGGED IN", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Login.this, "LOGIN FAIL", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(Login.this, "NO USERNAME IN DATABASE", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        //GRABS DATA FROM FIRESTORE DATABASE
        String getData = fAuth.getCurrentUser().getUid().toString() + " + " + fAuth.getCurrentUser().getEmail();
        fStore.collection("User").document(getData).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot doc) {
                if(doc.exists()){
                    String user = doc.getString("name");//Change to "Username"
                    String pw = doc.getString("pw");
                    String email = doc.getString("email");
                    String id = doc.getString("id");

                    loadData(user, pw, email, id);
                }
            }
        });


    }

    public void loadData(String user, String pw, String email, String id){
        tempUser = new User(user, pw, email, id);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}