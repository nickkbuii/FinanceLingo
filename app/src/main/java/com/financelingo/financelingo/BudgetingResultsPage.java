package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

import database.User;
import Global.Global;

public class BudgetingResultsPage extends AppCompatActivity {

    //retrieve score from user database class
    private int score = Global.user.qScore();
    private TextView budgeting_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to lessons.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_results);
        //display score out of total questions
        //define budgeting results text view
        budgeting_results = findViewById(R.id.budg_results_text);
        budgeting_results.setText(score+"/5");
    }

    //method that switches from budgeting results screen to lessons screen
    public void switchToHome(View view){
        switchActivities(BudgetingResultsPage.this, Lessons.class);
    }

    //method that switches from budgeting results screen to lesson
    //so user can restart
    public void restartLesson(View view){
        switchActivities(BudgetingResultsPage.this, LessonTemplate.class);
    }

    //method to switch classes
    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}