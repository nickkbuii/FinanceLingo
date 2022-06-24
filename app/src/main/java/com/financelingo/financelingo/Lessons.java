
package com.financelingo.financelingo;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.BreakIterator;

import Global.Global;

public class Lessons extends AppCompatActivity{

    private ImageButton switchToLesson;
    private ImageButton switchToReading;
    private static TextView accName = null;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set screen to lessons.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons);

//        switchToLesson = findViewById(R.id.budgetingButton);
//        switchToLesson.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                switchActivities(Lessons.this, LessonTemplate.class);
//            }
//        });
//
//        switchToReading = findViewById(R.id.budgetingReadingButton);
//        switchToReading.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                switchActivities(Lessons.this, ReadingTemplate.class);
//            }
//        });
        accName = findViewById(R.id.accName);


        //define "to lesson" button
        switchToLesson = findViewById(R.id.budgetingButton);
        switchToLesson.setOnClickListener(new View.OnClickListener(){
            @Override
            //when user clicks the "to lesson button", take user to the lesson
            public void onClick(View view){
                switchActivities(Lessons.this, LessonTemplate.class);
            }
        });

        //define "to reading" button
        switchToReading = findViewById(R.id.budgetingReadingButton);
        switchToReading.setOnClickListener(new View.OnClickListener(){
            @Override
            //when user clicks the "to reading button", take user to the reading
            public void onClick(View view){
                switchActivities(Lessons.this, ReadingTemplate.class);
            }
        });
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


    //method that allows us to switch methods and UI aka activities
    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}
