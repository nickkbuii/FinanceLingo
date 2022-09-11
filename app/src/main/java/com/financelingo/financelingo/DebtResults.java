package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import database.Database;
import Global.Global;

public class DebtResults extends AppCompatActivity {

    TextView debt_results_out;
    Database db = new Database();
    public int debt_score = Global.user.getQScore(Global.DEBT);

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
        if(debt_score!=4){
            Global.user.setQScore(Global.DEBT, 0);
            db.updateScore(Global.DEBT);
        }
        switchActivities(DebtResults.this, DebtLesson.class);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
