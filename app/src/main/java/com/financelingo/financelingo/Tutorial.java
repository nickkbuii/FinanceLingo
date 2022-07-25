package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Tutorial extends AppCompatActivity {

    Button tutorial;
    private int textNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);
        tutorial = findViewById(R.id.tutorialButton);
    }

    public void flipTutorialText(){
        textNum++;
        //pass textNum into array
    }

    private String[] tutorialTexts = {
            "Click here to view or update your account settings, profile, or join a class.",
            "Click any of these image icons to start a lesson.",
            "These progress bars will fill in as you achieve more progress in your lessons.",
            "Click any of these information icons to access readings that will introduce different financial topics that will help you complete your lesson.",
            "Congrats on taking your first steps to financial literacy. Have fun and get started!"
    };

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
