
package com.financelingo.financelingo;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class Lessons extends AppCompatActivity{

    Button switchToLesson;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons);

        switchToLesson = findViewById(R.id.budgetingButton);
        switchToLesson.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities(Lessons.this, LessonTemplate.class);
            }
        });
    }

    public void toLesson(View v){
        switchActivities(Lessons.this, LessonTemplate.class);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}
