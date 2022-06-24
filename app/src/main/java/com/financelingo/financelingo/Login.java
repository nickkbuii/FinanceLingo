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

import Global.Global;
import database.User;

public class Login extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    User tempUser;
    Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //Create Login Page
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        global = new Global();
    }

    public void login(View v){
        TextView text = findViewById(R.id.loginUsernameInput);
        String username = text.getText().toString();//username text field input
        text = findViewById(R.id.loginPasswordInput);
        String password = text.getText().toString();//Password text field input

        //Checks if User is logged in
        //TEMPORARY : AUTO LOGOUT -> ASK USER IF THEY WISH TO LOG OUT
        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }


        //LOGINS IN TO THE FIREBASE AUTHENTICATION
        if(username.contains("@")){
            fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "LOGGED IN", Toast.LENGTH_SHORT).show();
                        loadData();
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
                        String email = doc.getString("Email");
                        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Login.this, fAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                    loadData();
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

        switchActivities(Login.this, Lessons.class);
    }

    public void loadData(){
        //GRABS DATA FROM FIRESTORE DATABASE
        String getData = fAuth.getCurrentUser().getUid().toString() + " + " + fAuth.getCurrentUser().getEmail();
        fStore.collection("User").document(getData).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                if(doc.exists()){
                    String user = doc.getString("username");//Change to "Username"
                    String pw = doc.getString("pw");
                    String email = doc.getString("email");
                    String id = doc.getString("id");

                    Global.user = new User(user, pw, email, id);

                    Lessons.setUser(Global.user.getUsername().toUpperCase());
                }
                else{
                    Log.d("BIG BALLZ", "Failed");
                }
            }
        });

    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}