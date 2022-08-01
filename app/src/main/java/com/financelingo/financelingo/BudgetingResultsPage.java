package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

public class BudgetingResultsPage extends AppCompatActivity {

    private LessonTemplate lessonTemplate = new LessonTemplate();
    private int score = lessonTemplate.getScore();
    private TextView budgeting_results = findViewById(R.id.budg_results_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to lessons.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_results);
        budgeting_results.setText(String.valueOf(score)+"/5");
    }

    public void switchToHome(View view){
        switchActivities(BudgetingResultsPage.this, Lessons.class);
    }

    public void restartLesson(View view){
        switchActivities(BudgetingResultsPage.this, LessonTemplate.class);
    }

    //method to switch classes
    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
