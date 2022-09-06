package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TaxesLesson extends AppCompatActivity {

    ArrayList<String> words;
    ToggleButton option1;
    ToggleButton option2;
    ToggleButton option3;
    Button checkAnswer;
    TextView text;

    ArrayList<String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxes_lesson);

        words = new ArrayList<>();
        words.add("contribution");
        words.add("support");
        words.add("government");

        answers = new ArrayList<>();

        option1 = findViewById(R.id.taxes_button1);
        option2 = findViewById(R.id.taxes_button2);
        option3 = findViewById(R.id.taxes_button3);
        checkAnswer = findViewById(R.id.taxes_checkAnswer);
        text = findViewById(R.id.taxes_Text);

        option1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                words.add("government");
            }
        });

        option2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                words.add("contribution");
            }
        });

        option3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                words.add("support");
            }
        });

        checkAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(words.equals(answers)) text.setTextColor(Color.GREEN);
                else text.setTextColor(Color.RED);
            }
        });
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}
