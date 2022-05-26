package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import database.DatabaseHelper;
import database.User;

public class Create extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        db = new DatabaseHelper();
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

        /*Testing something*/
        Button b = findViewById(R.id.createButton);
        if(b.getTag().equals("correct")){
            Log.d("Testing", "this is correct");
        }

        User user = new User(username, password);

        db.addUser(user);

        //Log.d("testing", db.getUser("testing", "testing").toString());
        switchActivities(Create.this, MainActivity.class);
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