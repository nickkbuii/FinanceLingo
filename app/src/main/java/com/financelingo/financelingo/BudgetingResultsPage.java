package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

import database.User;

public class BudgetingResultsPage extends AppCompatActivity {

    //retrieve score from user database class
    private User user = new User();
    private int score = user.qScore();

    //define budgeting results text view
    private TextView budgeting_results = findViewById(R.id.budg_results_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to lessons.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_results);
        //display score out of total questions
        budgeting_results.setText(String.valueOf(score/5));
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
