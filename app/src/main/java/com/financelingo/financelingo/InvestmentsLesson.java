package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Global.Global;
import Lessons.Investments;
import database.Database;

public class InvestmentsLesson extends AppCompatActivity {
    Investments investments = new Investments();
    Database db = new Database();
    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();


    private ToggleButton option1, option2, option3;
    private Button checkAnswer;
    private boolean b1, b2, b3;
    private TextView investments_prompt;
    private int qNum;
    int score = Global.user.getQScore(Global.INVESTMENTS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investments_lesson);
        qNum = 0;
        investments_prompt = findViewById(R.id.inv_Text);
        option1 = findViewById(R.id.inv_button1);
        option2 = findViewById(R.id.inv_button2);
        option3 = findViewById(R.id.inv_button3);
        checkAnswer = findViewById(R.id.inv_checkAnswer);

        answers.add(investments.answers[qNum][0]);
        answers.add(investments.answers[qNum][1]);
        answers.add(investments.answers[qNum][2]);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option1.toggle();
                if(!b1){
                    words.add(investments.options[qNum][0]);
                    option1.setChecked(true);
                    String prompt = investments_prompt.getText().toString().substring(0, investments_prompt.getText().toString().indexOf("____")) + investments.options[qNum][0] +
                            investments_prompt.getText().toString().substring(investments_prompt.getText().toString().indexOf("____") + 4);
                    investments_prompt.setText(prompt);
                }
                else{
                    words.remove(investments.options[qNum][0]);
                    option1.setChecked(false);
                    String undo = investments_prompt.getText().toString().substring(0, investments_prompt.getText().toString().indexOf(investments.options[qNum][0])) + "____" +
                            investments_prompt.getText().toString().substring(investments_prompt.getText().toString().indexOf(investments.options[qNum][0]) + investments.options[qNum][0].length());
                    investments_prompt.setText(undo);
                }
                b1 = !b1;
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option2.toggle();
                if(!b2){
                    words.add(investments.options[qNum][1]);
                    option2.setChecked(true);
                    String prompt = investments_prompt.getText().toString().substring(0, investments_prompt.getText().toString().indexOf("____")) + investments.options[qNum][1] +
                            investments_prompt.getText().toString().substring(investments_prompt.getText().toString().indexOf("____") + 4);
                    investments_prompt.setText(prompt);
                }
                else{
                    words.remove(investments.options[qNum][1]);
                    option2.setChecked(false);
                    String undo = investments_prompt.getText().toString().substring(0, investments_prompt.getText().toString().indexOf(investments.options[qNum][1])) + "____" +
                            investments_prompt.getText().toString().substring(investments_prompt.getText().toString().indexOf(investments.options[qNum][1]) + investments.options[qNum][1].length());
                    investments_prompt.setText(undo);
                }
                b2 = !b2;
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option3.toggle();
                if(!b3){
                    words.add(investments.options[qNum][2]);
                    option3.setChecked(true);
                    String prompt = investments_prompt.getText().toString().substring(0, investments_prompt.getText().toString().indexOf("____")) + investments.options[qNum][2] +
                            investments_prompt.getText().toString().substring(investments_prompt.getText().toString().indexOf("____") + 4);
                    investments_prompt.setText(prompt);
                }
                else{
                    words.remove(investments.options[qNum][2]);
                    option3.setChecked(false);
                    String undo = investments_prompt.getText().toString().substring(0, investments_prompt.getText().toString().indexOf(investments.options[qNum][2])) + "____" +
                            investments_prompt.getText().toString().substring(investments_prompt.getText().toString().indexOf(investments.options[qNum][2]) + investments.options[qNum][2].length());
                    investments_prompt.setText(undo);
                }
                b3 = !b3;
            }
        });

        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(words.equals(answers)){
                    investments_prompt.setTextColor(getResources().getColor(R.color.green));
                    Global.user.setQScore(Global.INVESTMENTS, ++score);
                    next();
                    option1.toggle();
                    option2.toggle();
                    option3.toggle();
                    investments_prompt.setTextColor(getResources().getColor(R.color.white));
                }
                else {
                    investments_prompt.setTextColor(Color.RED);

                    if(qNum == 2){ //right no matter the order for q3
                        investments_prompt.setTextColor(getResources().getColor(R.color.green));
                        Global.user.setQScore(Global.INVESTMENTS, ++score);
                        next();
                        option1.toggle();
                        option2.toggle();
                        option3.toggle();
                        investments_prompt.setTextColor(getResources().getColor(R.color.white));
                    }

                }
            }
        });
    }

    public void next(){
        qNum++;
        if(qNum == 5){
            db.updateScore(Global.INVESTMENTS);
            switchActivities(InvestmentsLesson.this, Lessons.class);
        }else {

            b1 = !b1;
            b2 = !b2;
            b3 = !b3;

            words.clear();
            answers.clear();

            investments_prompt.setText(investments.prompts[qNum]);

            option1.setTextOff(investments.options[qNum][0]);
            option1.setTextOn(investments.options[qNum][0]);

            option2.setTextOff(investments.options[qNum][1]);
            option2.setTextOn(investments.options[qNum][1]);

            option3.setTextOff(investments.options[qNum][2]);
            option3.setTextOn(investments.options[qNum][2]);

            answers.add(investments.answers[qNum][0]);
            answers.add(investments.answers[qNum][1]);
            answers.add(investments.answers[qNum][2]);
        }
        System.out.println(qNum);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}
