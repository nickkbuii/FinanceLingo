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

public class Login extends AppCompatActivity implements FirebaseAuth.AuthStateListener{
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    User tempUser;
    Global global;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //Create Login Page

        db = new Database();

        fAuth = db.getAuth();
        fStore = db.getFirestore();
        global = new Global();
    }

    public void login(View v){
        TextView text = findViewById(R.id.loginUsernameInput);
        String username = text.getText().toString();//username text field input
        text = findViewById(R.id.loginPasswordInput);
        String password = text.getText().toString();//Password text field input

        //LOGINS IN TO THE FIREBASE AUTHENTICATION
        db.login(Login.this, username, password);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        //  NOT INSTANTIATING GLOBAL.USER FAST ENOUGH
        // FIND WAY TO DO SMT (LOADING SCREEN) WHILE THE FIRESTORE IS GETTING USER DATA
        // SUGGESTION : WHILE LOOP LOADING SCREEN ACTIVITY UNTIL USER IS NOT NULL
        // SUGGESTION : FIND WHY IT IS INFINITELY LOOPING
        if(fAuth.getCurrentUser() != null){
            db.loadData(Login.this);
            View lessonsView = getLayoutInflater().inflate(R.layout.lessons, null);
            TextView name = (TextView) lessonsView.findViewById(R.id.accName);
            name.setVisibility(View.VISIBLE);
            name.setText(fAuth.getCurrentUser().getDisplayName());
            Log.d("VISIBILITY", String.valueOf(name.getVisibility()));
            Log.d("NAME: ", name.getText().toString());

            lessonsView.refreshDrawableState();

            switchActivities(Login.this, Lessons.class);
        }

    }
}