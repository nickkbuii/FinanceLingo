package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Lessons.Debt;
import Global.Global;
import database.Database;

public class DebtLesson extends AppCompatActivity {

    Debt debt = new Debt();
    Database db = new Database();
    TextView debtInput1;
    TextView debtInput2;
    TextView debtPrompt;
    ImageButton p1;
    ImageButton p2;
    ImageButton p3;
    ImageButton p4;
    ImageButton p5;
    Button checkButton;
    int debt_qNum;
    public int debt_score = 0; //incorporate db score retrieval later

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to budgeting lesson
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debt_lesson);

        debt_qNum = 0;
        debtInput1 = findViewById(R.id.debt_input1);
        debtInput2 = findViewById(R.id.debt_input2);
        debtPrompt = findViewById(R.id.debt_question);

        p1 = findViewById(R.id.debt_prog_1);
        p2 = findViewById(R.id.debt_prog_2);
        p3 = findViewById(R.id.debt_prog_3);
        p4 = findViewById(R.id.debt_prog_4);
        p5 = findViewById(R.id.debt_prog_5);
        checkButton = findViewById(R.id.checkButton);

        updateQuestion();

        checkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkAns();
            }
        });
    }

    public void checkAns(){
        System.out.println(debtInput1.getText().toString());
        if (debtInput1.getText().toString().equals(debt.answers[debt_qNum][0]) && debtInput2.getText().toString().equals(debt.answers[debt_qNum][1])){
            debt_score++;
            if(debt_qNum==0){
                Log.d("penis", String.valueOf(debt_qNum));
                p1.setImageDrawable(getDrawable(R.drawable.green_circle_button));
            }else if(debt_qNum==1){
                p2.setImageDrawable(getDrawable(R.drawable.green_circle_button));
            }else if(debt_qNum==2) {
                p3.setImageDrawable(getDrawable(R.drawable.green_circle_button));
            }else if(debt_qNum==3) {
                p4.setImageDrawable(getDrawable(R.drawable.green_circle_button));
            }else if(debt_qNum==4) {
                p5.setImageDrawable(getDrawable(R.drawable.green_circle_button));
                db.updateScore(Global.DEBT);
                switchActivities(DebtLesson.this, DebtResults.class);
            }
        }
        debt_qNum++;
        debtInput1.setText("");
        debtInput2.setText("");
        updateQuestion();
    }

    public void updateQuestion(){
        debtPrompt.setText(debt.prompts[debt_qNum]);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
