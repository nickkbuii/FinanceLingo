package com.financelingo.financelingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import database.User;

public class Create extends AppCompatActivity {
    FirebaseAuth fAuth;
    DatabaseReference db;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    private void switchActivities(Class c){
        Intent switchActivityIntent = new Intent (this, c);
        startActivity(switchActivityIntent);
    }


    public void makeAcc(View v){
        TextView t = findViewById(R.id.user);
        String username = t.getText().toString();

        //FIX LATER
//        if(!db.checkUser(username)){
//            Toast.makeText(Create.this, "USER TAKEN", Toast.LENGTH_SHORT).show();
//            return;
//        }

        t = findViewById(R.id.pw);
        String password = t.getText().toString();

        if(checkPass(password) != ""){
            Toast.makeText(Create.this, checkPass(password), Toast.LENGTH_LONG).show();
            return;
        }

        User user = new User(username, password);
        Log.d("INFO", user.toString());

        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        fAuth.createUserWithEmailAndPassword(user.getName()+"@gmail.com", user.getPw()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Create.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                    fStore.collection("User")
                            .document(fAuth.getCurrentUser().getUid())
                            .set(user);
                    fAuth.signOut();
                }
                else{
                    Toast.makeText(Create.this, "FAIL", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

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

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}