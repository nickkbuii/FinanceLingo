package com.financelingo.financelingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Global.Global;
import database.User;

public class Create extends AppCompatActivity {
    FirebaseAuth fAuth;
    DatabaseReference db;
    FirebaseFirestore fStore;

    Global global = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }



    public void makeAcc(View v){
        TextView t = findViewById(R.id.user);
        String username = t.getText().toString();

        t = findViewById(R.id.pw);
        String password = t.getText().toString();

        t = findViewById(R.id.email);
        String email = t.getText().toString();

        if(checkPass(password) != ""){
            Toast.makeText(Create.this, checkPass(password), Toast.LENGTH_LONG).show();
            return;
        }

        User user = new User(username, password, email);

        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        //Creates new User onto firebase authentication
        //user.getEmail() method change when UI is ready
        fAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPw())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Set new UID
                    user.setId(fAuth.getCurrentUser().getUid());
                    //Log.d("INFO", fAuth.getCurrentUser().getDisplayName());
                    Toast.makeText(Create.this, "SUCCESS", Toast.LENGTH_SHORT).show();

                    //Adds to profile firebase firestore
                    fStore.collection("User")
                            .document(fAuth.getCurrentUser().getUid() + " + " + user.getEmail())
                            .set(user);
                    global.switchActivities(Create.this, MainActivity.class);

                    //Make Update user profile
                }
                else{
                    Toast.makeText(Create.this, "FAIL", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}