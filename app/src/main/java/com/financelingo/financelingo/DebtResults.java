package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import database.Database;

public class DebtResults extends AppCompatActivity {

    TextView debt_results_out;
    Database db = new Database();
    public int debt_score; //incorporate db score retrieval

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debt_results);
        debt_results_out = findViewById(R.id.debt_results_text);
        debt_results_out.setText((debt_score)+"/5");
    }

    public void debtResultsToHome(View view){
        switchActivities(DebtResults.this, Lessons.class);
    }

    public void restartDebt(View view){
        //db update -> if score!=5: score=0
        switchActivities(DebtResults.this, DebtLesson.class);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
