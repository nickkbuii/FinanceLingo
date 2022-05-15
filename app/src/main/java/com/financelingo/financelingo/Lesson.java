package com.financelingo.financelingo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import database.DatabaseHelper;

public class Lesson extends AppCompatActivity {
    DatabaseHelper db;
    int barAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_lesson);
        db = new DatabaseHelper(Lesson.this);
    }

    public void checkCorrect(View view){
        TextView text = findViewById(R.id.notify);
        ProgressBar bar = findViewById(R.id.progressBar);

        if(view.getTag().toString().equals("correct")){
            text.setText("Correct!");
            animateBar(bar, 20);
        }
        else{
            text.setText("Wrong!!");
            animateBar(bar, -5);
        }

    }

    public void animateBar(ProgressBar bar, int amount){
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
