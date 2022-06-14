package com.financelingo.financelingo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import database.Question;


public class LessonTemplate extends AppCompatActivity {
    //DatabaseHelper db;
    //private Question question = new Question();
    private TextView questionView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    int barAmount = 0;
    private String answer;
    private int score = 0; //this is temporary, score should be retrieved from database
    private int questionNumber = 0; //this is temporary, question num should be retrieved from database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_lesson);

        questionView = findViewById(R.id.question);
        button1 = findViewById(R.id.opt1);
        button2 = findViewById(R.id.opt2);
        button3 = findViewById(R.id.opt3);
        button4 = findViewById(R.id.opt4);
        updateQuestion();
        //db = new DatabaseHelper(LessonTemplate.this);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button1.getText() == answer){
                    score++;
                }
                updateQuestion();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button2.getText() == answer){
                    score++;
                }
                updateQuestion();
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button3.getText() == answer){
                    score++;
                }
                updateQuestion();
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (button4.getText() == answer){
                    score++;
                }
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
//        questionView.setText(Question.getQuestion(questionNumber));
//        button1.setText(Question.getChoice(questionNumber,1));
//        button2.setText(Question.getChoice(questionNumber,2));
//        button3.setText(Question.getChoice(questionNumber,3));
//        button4.setText(Question.getChoice(questionNumber,4));
//        answer = Question.getCorrectAnswer(questionNumber);
//        questionNumber++;
    }

//    public void checkCorrect(View view){
//        view.setBackgroundColor(Color.CYAN);
//        Button button = (Button)view;
//
//        TextView text = findViewById(R.id.notify);
//        ProgressBar bar = findViewById(R.id.progressBar);
//
//        if(button.getText().toString().equals("correct")){
//            text.setText("Correct!");
//            animateBar(bar, 20);
//        }
//        else{
//            text.setText("Wrong!!");
//            animateBar(bar, -5);
//        }
//    }

//    private void animateBar(ProgressBar bar, int amount){
//        ValueAnimator animator = ValueAnimator.ofInt(barAmount, barAmount+amount);
//        barAmount += amount;
//        animator.setDuration(1500);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation){
//                bar.setProgress((Integer)animation.getAnimatedValue());
//            }
//        });
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                // start your activity here
//            }
//        });
//        animator.start();
//    }
}
