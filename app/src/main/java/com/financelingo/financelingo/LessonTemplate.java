package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


public class LessonTemplate extends AppCompatActivity {

    //DatabaseHelper db;
    //retrieve methods and curriculum from Budgeting.java
    private Budgeting budgeting = new Budgeting();
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
    //retrieve user's score and questionNumber
    private int score = 0;
    private int questionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to budgeting lesson
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_lesson);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();

        //define text view and the 4 answer option buttons
        questionView = findViewById(R.id.question);
        button1 = findViewById(R.id.opt1);
        button2 = findViewById(R.id.opt2);
        button3 = findViewById(R.id.opt3);
        button4 = findViewById(R.id.opt4);
        //set question and answer option to respective fields and buttons
        updateQuestion();

        //when a answer option button is clicked, check if correct
        //if correct, increment score, question number
        //set new question/answer options onto screen
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button1.getText() == answer){
                    score++;
                }
                questionNumber++;
                updateQuestion();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button2.getText() == answer){
                    score++;
                }
                questionNumber++;
                updateQuestion();
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button3.getText() == answer){
                    score++;
                }
                questionNumber++;
                updateQuestion();
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button4.getText() == answer){
                    score++;
                }
                questionNumber++;
                updateQuestion();
            }
        });

        //if user did not answer all 5 questions correctly, reset score/question number
        if (questionNumber==4 && score!=4){
            score=0;
            questionNumber=0;
            //take user back to lessons (home) screen
            //temporary, need to add a lesson results page for user
            switchActivities(LessonTemplate.this, Lessons.class);
        }

    }

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

    public boolean checkFinished(){
        final boolean[] passed = {true};
        fStore.collection("Lessons").document(Global.user.getUsername()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                if(doc.exists()){
                    Map<String, Object> map = doc.getData();
                    for(Map.Entry<String, Object> entry : map.entrySet()){
                        if(entry.getKey().equals("Budgeting")){
                            Map<String, Object> questionScore = (Map<String, Object>)entry.getValue();
                            for(Map.Entry<String, Object> e : questionScore.entrySet()){
                                if(e.getKey().equals("Question")){
                                    int qNum = Integer.valueOf((int)e.getValue());
                                    if(qNum != 5){
                                        passed[0] = false;
                                    }
                                }
                                if(e.getKey().equals("Score")){
                                    int scoreNum = Integer.valueOf((int)e.getValue());
                                    if(scoreNum != 5){
                                        passed[0] = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        return passed[0];
    }

    //method to switch classes
    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
