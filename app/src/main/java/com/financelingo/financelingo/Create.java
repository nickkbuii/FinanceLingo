package com.financelingo.financelingo;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import Global.Global;
import database.Database;
import database.User;

public class Create extends AppCompatActivity {
    Database db;

    Global global = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = new Database();
    }

    public void makeAcc(View v){
        TextView userText = findViewById(R.id.user);
        String username = userText.getText().toString();

        TextView pwText = findViewById(R.id.pw);
        String password = pwText.getText().toString();

        TextView emailText = findViewById(R.id.email);
        String email = emailText.getText().toString();

        if(!checkPass(password).equals("")){
            pwText.setError(checkPass(password));
            return;
        }

        if(!db.checkUser(username).equals("")){
            userText.setError("Username is taken!");
            return;
        }

        User user = new User(username, password, email);

        if(db.createAcc(Create.this, username, email, password)){
            Toast.makeText(Create.this, "HERE", Toast.LENGTH_SHORT).show();
            switchActivities(Create.this, MainActivity.class);
        }
    }



    //Checks if password passes test values
    public String checkPass(String pass){
        String send = "";
        boolean hasUpper = false;
        for(char c : pass.toCharArray()){
            if(Character.isUpperCase(c)){
                hasUpper = true;
            }
        }

        if(!hasUpper){
            send += "Password must have an uppercase letter\n";
        }
        if(pass.length() < 8){
            send +=  "Password must be atleast 8 letters long!\n";
        }


        return send;
    }

    public void showPw(View v){
        Button b = findViewById(R.id.showPw);
        TextView pwBox = findViewById(R.id.pw);

        if(b.getTag().equals("hide")){
            b.setTag("show");
            pwBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        else{
            b.setTag("hide");
            pwBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }



    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}