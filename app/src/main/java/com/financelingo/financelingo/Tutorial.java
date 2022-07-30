package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class Tutorial extends AppCompatActivity {

    //declare initial text number
    private int textNum = 0;

    //initializing buttons
    Button next;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //set screen to tutorial.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);
        //define text fields for tutorial text
        TextView tutorialText = findViewById(R.id.tutorialText);
        //set initial tutoral text
        tutorialText.setText(tutorialTexts[textNum]);
        //define buttons for flipping tutorial texts
        next = findViewById(R.id.nextText);
        back = findViewById(R.id.backText);
        //set button listener for flipping next
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textNum == 4) {
                    textNum = 0;
                } else {
                    textNum++;
                }
                tutorialText.setText(tutorialTexts[textNum]);
            }
        });
        //set button listener for flipping back
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (textNum==0) {
                    textNum = 4;
                }else {
                    textNum--;
                }
                tutorialText.setText(tutorialTexts[textNum]);
            }
        });
    }

    public void tutorialToLessons(View v){
        switchActivities(Tutorial.this, Lessons.class);
    }

    //array of tutorial texts
    public String[] tutorialTexts = {
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