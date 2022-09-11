package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import Lessons.Debt;
import Global.Global;
import database.Database;

public class DebtLesson extends AppCompatActivity {

    //import debt and database class
    Debt debt = new Debt();
    Database db = new Database();

    //declare text views, images and buttons
    TextView debtPrompt;
    ImageButton p1;
    ImageButton p2;
    ImageButton p3;
    ImageButton p4;
    ImageButton p5;
    Button option1;
    Button option2;
    Button option3;
    ImageView debt_pic;

    private FirebaseUser fUser;
    private FirebaseFirestore fStore;


    //declare and initialize question number and score
    public int debt_qNum=0;
    int debt_score = Global.user.getQScore(Global.DEBT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to budgeting lesson
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debt_lesson);
        //initialize prompt text view
        debtPrompt = findViewById(R.id.debt_question);
        //initialize progress buttons
        p1 = findViewById(R.id.debt_prog_1);
        p2 = findViewById(R.id.debt_prog_2);
        p3 = findViewById(R.id.debt_prog_3);
        p4 = findViewById(R.id.debt_prog_4);
        p5 = findViewById(R.id.debt_prog_5);
        //initialize option buttons
        option1 = findViewById(R.id.debt_opt1);
        option2 = findViewById(R.id.debt_opt2);
        option3 = findViewById(R.id.debt_opt3);
        //initialize debt picture
        debt_pic = findViewById(R.id.debt_mc_image);
        //set initial pic, options and prompt
        updateQuestion();

        option1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(option1.getText()==debt.answers[debt_qNum]){
                    if(Global.user.getQScore(Global.DEBT)!=5){
                        Global.user.setQScore(Global.DEBT, ++debt_score);
                    }
                    showProg();
                }
                if(debt_qNum==4){
                    db.updateScore(Global.DEBT);
                    switchActivities(DebtLesson.this, DebtResults.class);
                }else{
                    debt_qNum++;
                    updateQuestion();
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(option2.getText()==debt.answers[debt_qNum]){
                    if(Global.user.getQScore(Global.DEBT)!=5){
                        Global.user.setQScore(Global.DEBT, ++debt_score);
                    }
                    showProg();
                }
                if(debt_qNum==4){
                    db.updateScore(Global.DEBT);
                    switchActivities(DebtLesson.this, DebtResults.class);
                }else{
                    debt_qNum++;
                    updateQuestion();
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(option3.getText()==debt.answers[debt_qNum]){
                    if(Global.user.getQScore(Global.DEBT)!=5){
                        Global.user.setQScore(Global.DEBT, ++debt_score);
                    }
                    showProg();
                }
                if(debt_qNum==4){
                    db.updateScore(Global.DEBT);
                    switchActivities(DebtLesson.this, DebtResults.class);
                }else{
                    debt_qNum++;
                    updateQuestion();
                }
            }
        });
    }

    public void showProg(){
        if(debt_qNum==0){
            p1.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (debt_qNum==1){
            p2.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (debt_qNum==2){
            p3.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (debt_qNum==3){
            p4.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (debt_qNum==4){
            p5.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }
    }

    public void updateQuestion(){
        debtPrompt.setText(debt.prompts[debt_qNum]);
        option1.setText(debt.options[debt_qNum][0]);
        option2.setText(debt.options[debt_qNum][1]);
        option3.setText(debt.options[debt_qNum][2]);
//        debt_pic.setImageDrawable(getDrawable(debt.questionImages[debt_qNum]));
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
