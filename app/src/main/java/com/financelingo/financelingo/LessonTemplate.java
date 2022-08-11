package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import Global.Global;
import Lessons.*;
import database.Database;
import database.User;


public class LessonTemplate extends AppCompatActivity {

    //retrieve methods and curriculum from Budgeting.java and User.java
    private Budgeting budgeting = new Budgeting();

    private Database db;

    //initialize question text view and the 4 answer option buttons
    private TextView questionView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    //initialize answer string
    private String answer;

    //firebase database dependencies
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;

    //retrieve user's score
    public int score = Global.user.qScore();

    public int questionNumber = 0;

    //define image buttons for MC
    private ImageButton prog_1;
    private ImageButton prog_2;
    private ImageButton prog_3;
    private ImageButton prog_4;
    private ImageButton prog_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to budgeting lesson
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_lesson);

        db = new Database();

        //firebase initialization
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        //define text view and the 4 answer option buttons
        questionView = findViewById(R.id.question);
        button1 = findViewById(R.id.opt1);
        button2 = findViewById(R.id.opt2);
        button3 = findViewById(R.id.opt3);
        button4 = findViewById(R.id.opt4);
        //define progress circles for each question
        prog_1 = findViewById(R.id.prog_1);
        prog_2 = findViewById(R.id.prog_2);
        prog_3 = findViewById(R.id.prog_3);
        prog_4 = findViewById(R.id.prog_4);
        prog_5 = findViewById(R.id.prog_5);
        //set question and answer option to respective fields and buttons
        updateQuestion();
        //when a answer option button is clicked, check if correct
        //if correct, increment score, question number and change color of progress image button
        //set new question/answer options onto screen
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button1.getText() == answer){
                    if(Global.user.qScore()!=5){
                        Global.user.setQScore(++score);
                    }
                    determineButtonsWhenRight();
                }
                if (questionNumber<4){
                    questionNumber++;
                    updateQuestion();
                }else if(questionNumber==4){
                    db.updateScore();
                    switchActivities(LessonTemplate.this, BudgetingResultsPage.class);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button2.getText() == answer){
                    if(Global.user.qScore()!=5){
                        Global.user.setQScore(++score);
                    }
                    determineButtonsWhenRight();
                }
                if (questionNumber<4){
                    questionNumber++;
                    updateQuestion();
                }else if(questionNumber==4){
                    db.updateScore();
                    switchActivities(LessonTemplate.this, BudgetingResultsPage.class);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button3.getText() == answer){
                    if(Global.user.qScore()!=5){
                        Global.user.setQScore(++score);
                    }
                    determineButtonsWhenRight();
                }
                if (questionNumber<4){
                    questionNumber++;
                    updateQuestion();
                }else if(questionNumber==4){
                    db.updateScore();
                    switchActivities(LessonTemplate.this, BudgetingResultsPage.class);
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button4.getText() == answer){
                    if(Global.user.qScore()!=5){
                        Global.user.setQScore(++score);
                    }
                    determineButtonsWhenRight();
                }
                if (questionNumber<4){
                    questionNumber++;
                    updateQuestion();
                }else if(questionNumber==4){
                    db.updateScore();
                    switchActivities(LessonTemplate.this, BudgetingResultsPage.class);
                }
            }
        });
    }

    //method that determines which progress image button to change depending on qNum
    public void determineButtonsWhenRight(){
        if (questionNumber==0){
            prog_1.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (questionNumber==1){
            prog_2.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (questionNumber==2){
            prog_3.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (questionNumber==3){
            prog_4.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }else if (questionNumber==4){
            prog_5.setImageDrawable(getDrawable(R.drawable.green_circle_button));
        }
    };

    //set question and answer options to respective fields and buttons according to question number
    private void updateQuestion(){
        //Assign QuestionText to first prompt
        //Assign each ButtonOption to multiple choice answer
        questionView.setText(budgeting.question.getQuestion(questionNumber));
        button1.setText(budgeting.question.getChoice(questionNumber,0));
        button2.setText(budgeting.question.getChoice(questionNumber,1));
        button3.setText(budgeting.question.getChoice(questionNumber,2));
        button4.setText(budgeting.question.getChoice(questionNumber,3));
        answer = budgeting.question.getCorrectAnswer(questionNumber);
    }

    //method to switch classes
    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
