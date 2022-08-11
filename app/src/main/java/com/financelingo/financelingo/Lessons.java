
package com.financelingo.financelingo;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import Global.Global;
import database.Database;

public class Lessons extends AppCompatActivity{

    //retrieve score from user class
    private int score = Global.user.qScore();

    //initialize account name text view
    private TextView accName;

    //initialize bar amount
    int barAmount = 0;

    //initialize progress bar
    ProgressBar budgetingProgressBar;
    Database db;

    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to lessons.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons);

        db = new Database();

        //define account name text view
        accName = findViewById(R.id.accName);
        if(db.getAuth().getCurrentUser().getDisplayName() == null){
            accName.setText(Global.user.getUsername());
        }
        else{
            accName.setText(db.getAuth().getCurrentUser().getDisplayName().toString().toUpperCase());
        }


        //define progress bar view
        budgetingProgressBar = findViewById(R.id.budgetingProgressBar);

        //animate progress bar based on score
        animateBar(budgetingProgressBar, score);
    }

    //method that changes from lessons (home) class to the lesson class
    public void toLesson(View v){
        switchActivities(Lessons.this, BudgetingLesson.class);
        if(score!=5){
            Global.user.setQScore(0);
        }
    }

    //method that changes from lessons (home) class to the reading class
    public void toReading(View v){
        switchActivities(Lessons.this, BudgetingReading.class);
    }

    //method that changes from lessons (home) class to the account settings class
    public void toAccSettings(View v){
        switchActivities(Lessons.this, AccSettings.class);
    }

    //method that changes from lessons (home) class to the tutorial class
    public void toTutorial(View v){
        switchActivities(Lessons.this, Tutorial.class);
    }

    //method that declares username
    //public static void setUser(String username){ accName.setText(username); }



    //method to increment progress bar
    public void animateBar(ProgressBar bar, int amount){
        ValueAnimator animator = ValueAnimator.ofInt(barAmount, (barAmount+amount)*20);
        barAmount = (barAmount+amount)*20;
        animator.setDuration(100);
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
