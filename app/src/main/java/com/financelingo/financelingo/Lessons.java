
package com.financelingo.financelingo;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.text.BreakIterator;

import Global.Global;

public class Lessons extends AppCompatActivity{

    private Button switchToLesson;
    private Button switchToReading;
    private static TextView accName = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        accName = findViewById(R.id.testingName);


    }

    public void toLesson(View v){
        switchActivities(Lessons.this, LessonTemplate.class);
    }

    public void toReading(View v){
        switchActivities(Lessons.this, ReadingTemplate.class);
    }

    public static void setUser(String username){
        accName.setText(username);
    }


    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}
