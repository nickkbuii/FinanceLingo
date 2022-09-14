package com.financelingo.financelingo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import Lessons.Taxes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TaxLesson extends AppCompatActivity {

    Taxes tax = new Taxes();

    private ToggleButton option1, option2, option3, option4;
    private Button checkAnswer;
    private boolean b1, b2, b3;
    private TextView tax_prompt;
    private int qNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxes_lesson);
        qNum = 0;
        tax_prompt = findViewById(R.id.taxes_Text);
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        option1 = findViewById(R.id.taxes_button1);
        option2 = findViewById(R.id.taxes_button2);
        option3 = findViewById(R.id.taxes_button3);
        checkAnswer = findViewById(R.id.taxes_checkAnswer);
        answers.add("contribution");
        answers.add("support");
        answers.add("government");

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option1.toggle();
                if(!b1){
                    words.add(tax.options[qNum][0]);
                    System.out.println("added");
                    option1.setChecked(true);
                    String prompt = tax_prompt.getText().toString().substring(0, tax_prompt.getText().toString().indexOf("____")) + tax.options[qNum][0] +
                            tax_prompt.getText().toString().substring(tax_prompt.getText().toString().indexOf("____") + 4);
                    System.out.println("prompt changed");
                    tax_prompt.setText(prompt);
                }
                else{
                    words.remove(tax.options[qNum][0]);
                    option1.setChecked(false);
                    String undo = tax_prompt.getText().toString().substring(0, tax_prompt.getText().toString().indexOf(tax.options[qNum][0])) + "____" +
                            tax_prompt.getText().toString().substring(tax_prompt.getText().toString().indexOf(tax.options[qNum][0]) + tax.options[qNum][0].length());
                    tax_prompt.setText(undo);
                    System.out.println("prompt changed1");
                }
                Log.d("poop", String.valueOf(words));
                b1 = !b1;
            }

        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("qnum:" + qNum);
                option2.toggle();
                if(!b2){
                    words.add(tax.options[qNum][1]);
                    System.out.println("added1");
                    option2.setChecked(true);
                    String prompt = tax_prompt.getText().toString().substring(0, tax_prompt.getText().toString().indexOf("____")) + tax.options[qNum][1] +
                            tax_prompt.getText().toString().substring(tax_prompt.getText().toString().indexOf("____") + 4);
                    System.out.println("prompt changed 2");
                    tax_prompt.setText(prompt);
                }
                else{
                    words.remove(tax.options[qNum][1]);
                    System.out.println("removed");
                    option2.setChecked(false);
                    String undo = tax_prompt.getText().toString().substring(0, tax_prompt.getText().toString().indexOf(tax.options[qNum][1])) + "____" +
                            tax_prompt.getText().toString().substring(tax_prompt.getText().toString().indexOf(tax.options[qNum][1]) + tax.options[qNum][1].length());
                    System.out.println("prompt changed 3");
                    tax_prompt.setText(undo);
                }
                Log.d("poop", String.valueOf(words));
                b2= !b2;
            }

        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option3.toggle();
                if(!b3){
                    words.add(tax.options[qNum][2]);
                    option3.setChecked(true);
                    String prompt = tax_prompt.getText().toString().substring(0, tax_prompt.getText().toString().indexOf("____")) + tax.options[qNum][2] +
                            tax_prompt.getText().toString().substring(tax_prompt.getText().toString().indexOf("____") + 4);
                    tax_prompt.setText(prompt);
                }
                else{
                    words.remove(tax.options[qNum][2]);
                    option3.setChecked(false);
                    String undo = tax_prompt.getText().toString().substring(0, tax_prompt.getText().toString().indexOf(tax.options[qNum][2])) + "____" +
                            tax_prompt.getText().toString().substring(tax_prompt.getText().toString().indexOf(tax.options[qNum][2]) + tax.options[qNum][2].length());
                    tax_prompt.setText(undo);
                }
                Log.d("poop", String.valueOf(words));
                b3 = !b3;
            }

        });

        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!words.equals(answers)) tax_prompt.setTextColor(Color.RED);
                else {
                    tax_prompt.setTextColor(getResources().getColor(R.color.green));
                    words.clear();
                    qNum++;
                    changeQuestion();
                }
            }
        });
    }

    public void changeQuestion(){
        tax_prompt.setText(tax.prompts[qNum]);
        option1.setTextOff(tax.options[qNum][0]);
        option1.setTextOn(tax.options[qNum][0]);
        option2.setTextOff(tax.options[qNum][1]);
        option2.setTextOn(tax.options[qNum][1]);
        option3.setTextOff(tax.options[qNum][2]);
        option3.setTextOn(tax.options[qNum][2]);
        b1 = !b1;
        b2 = !b2;
        b3 = !b3;
        tax_prompt.setTextColor(getResources().getColor(R.color.white));
        option1.toggle();
        option2.toggle();
        option3.toggle();

    }
}
