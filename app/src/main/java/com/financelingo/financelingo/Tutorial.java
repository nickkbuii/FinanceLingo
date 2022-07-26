package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Tutorial extends AppCompatActivity {

    private int textNum = 0;
    TextView tutorialText = findViewById(R.id.tutorialText);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);
    }

    public void flipNextTutorialText(){
        if (textNum==4) {
            textNum = 0;
        }else {
            textNum++;
        }
        tutorialText.setText(tutorialTexts[textNum]);
    }

    public void flipBackTutorialText(){
        if (textNum==0) {
            textNum = 4;
        }else {
            textNum--;
        }
        tutorialText.setText(tutorialTexts[textNum]);
    }

    private String[] tutorialTexts = {
            "Click here to view or update your account settings, profile, or join a class.",
            "Click any of these image icons to start a lesson.",
            "These progress bars will fill in as you achieve more progress in your lessons.",
            "Click any of these information icons to access readings that will introduce different financial topics that will help you complete your lesson.",
            "Congrats on taking your first steps to financial literacy. Have fun and get started!"
    };
}
