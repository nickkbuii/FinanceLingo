
package com.financelingo.financelingo;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.BreakIterator;

import Global.Global;
import database.User;

public class Lessons extends AppCompatActivity{

    private ProgressBar budgetingProgressBar2;
    private User user = new User();
    private int score = user.qScore();

    private static TextView accName = null;
    int barAmount = 0;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to lessons.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons);
        //define account name
        accName = findViewById(R.id.accName);
        animateBar(budgetingProgressBar2, score);
    }

    //method that changes from lessons (home) class to the lesson class
    public void toLesson(View v){
        switchActivities(Lessons.this, LessonTemplate.class);
    }

    //method that changes from lessons (home) class to the reading class
    public void toReading(View v){
        switchActivities(Lessons.this, ReadingTemplate.class);
    }

    public static void setUser(String username){
        accName.setText(username);
    }

    //method to increment progress bar
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

    //method that allows us to switch methods and UI aka activities
    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}
