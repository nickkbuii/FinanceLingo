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


public class LessonTemplate extends AppCompatActivity {
    //DatabaseHelper db;
    int barAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_lesson);
        //db = new DatabaseHelper(LessonTemplate.this);
    }

    public void checkCorrect(View view){
        view.setBackgroundColor(Color.CYAN);
        Button button = (Button)view;

        TextView text = findViewById(R.id.notify);
        ProgressBar bar = findViewById(R.id.progressBar);

        if(button.getText().toString().equals("correct")){
            text.setText("Correct!");
            animateBar(bar, 20);
        }
        else{
            text.setText("Wrong!!");
            animateBar(bar, -5);
        }
    }

    private void animateBar(ProgressBar bar, int amount){
        ValueAnimator animator = ValueAnimator.ofInt(barAmount, barAmount+amount);
        barAmount += amount;
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                bar.setProgress((Integer)animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // start your activity here
            }
        });
        animator.start();
    }






}
