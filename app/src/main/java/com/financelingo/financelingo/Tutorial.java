package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
        //define image elements for tutorial page
        ImageView t_budgeting_image = findViewById(R.id.tutorialBudgetingImage);
        TextView t_budgetingLabel = findViewById(R.id.tutorialBudgetingLabel);
        ImageView t_budgeting_info = findViewById(R.id.tutorialBudgetingReadingInfo);
        ImageView t_acc_pic = findViewById(R.id.tutorialAccPic);
        ImageView t_budgeting_button = findViewById(R.id.tutorialBudgetingButton);
        //define text fields for tutorial text
        TextView tutorialText = findViewById(R.id.tutorialText);
        //set initial tutorial text
        tutorialText.setText(tutorialTexts[textNum]);
        //set initial image visibilities
        t_budgeting_image.setVisibility(View.INVISIBLE);
        t_budgetingLabel.setVisibility(View.INVISIBLE);
        t_budgeting_info.setVisibility(View.INVISIBLE);
        t_acc_pic.setVisibility(View.INVISIBLE);
        t_budgeting_button.setVisibility(View.INVISIBLE);
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
                if(textNum==0){
                    t_budgeting_image.setVisibility(View.INVISIBLE);
                    t_budgetingLabel.setVisibility(View.INVISIBLE);
                    t_budgeting_info.setVisibility(View.INVISIBLE);
                    t_acc_pic.setVisibility(View.INVISIBLE);
                    t_budgeting_button.setVisibility(View.INVISIBLE);
                }else if(textNum==1){
                    t_acc_pic.setVisibility(View.VISIBLE);
                }else if(textNum==2){
                    t_acc_pic.setVisibility(View.INVISIBLE);
                    t_budgeting_button.setVisibility(View.VISIBLE);
                    t_budgeting_image.setVisibility(View.VISIBLE);
                }else if(textNum==3){
                    t_budgeting_button.setVisibility(View.INVISIBLE);
                    t_budgeting_image.setVisibility(View.INVISIBLE);
                    t_budgeting_info.setVisibility(View.VISIBLE);
                }else if(textNum==4){
                    t_budgeting_image.setVisibility(View.INVISIBLE);
                    t_budgetingLabel.setVisibility(View.INVISIBLE);
                    t_budgeting_info.setVisibility(View.INVISIBLE);
                    t_acc_pic.setVisibility(View.INVISIBLE);
                    t_budgeting_button.setVisibility(View.INVISIBLE);
                }
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
                if(textNum==0){
                    t_budgeting_image.setVisibility(View.INVISIBLE);
                    t_budgetingLabel.setVisibility(View.INVISIBLE);
                    t_budgeting_info.setVisibility(View.INVISIBLE);
                    t_acc_pic.setVisibility(View.INVISIBLE);
                    t_budgeting_button.setVisibility(View.INVISIBLE);
                }else if(textNum==1){
                    t_acc_pic.setVisibility(View.VISIBLE);
                }else if(textNum==2){
                    t_acc_pic.setVisibility(View.INVISIBLE);
                    t_budgeting_button.setVisibility(View.VISIBLE);
                    t_budgeting_image.setVisibility(View.VISIBLE);
                }else if(textNum==3){
                    t_budgeting_button.setVisibility(View.INVISIBLE);
                    t_budgeting_image.setVisibility(View.INVISIBLE);
                    t_budgeting_info.setVisibility(View.VISIBLE);
                }else if(textNum==4){
                    t_budgeting_image.setVisibility(View.INVISIBLE);
                    t_budgetingLabel.setVisibility(View.INVISIBLE);
                    t_budgeting_info.setVisibility(View.INVISIBLE);
                    t_acc_pic.setVisibility(View.INVISIBLE);
                    t_budgeting_button.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void tutorialToLessons(View v){
        switchActivities(Tutorial.this, Lessons.class);
    }

    //array of tutorial texts
    public String[] tutorialTexts = {
            "Welcome to FinanceLingo! We will be going over a quick app tutorial",
            "Click image icons like this to update your account settings, profile, or join a class.",
            "Click on image icons like this to start a lesson.",
            "Click on image icons like this to access readings that will introduce different financial topics which will help you complete your lesson.",
            "Congrats on taking your first steps to financial literacy. Have fun and get started!"
    };

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}