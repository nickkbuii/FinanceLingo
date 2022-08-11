package com.financelingo.financelingo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import Lessons.DebtReadings;

public class DebtReading extends AppCompatActivity {
    DebtReadings debtReadings = new DebtReadings();
    TextView pageNumber;
    TextView readingText;
    TextView readingTitle;
    ImageView readingPic;
    Button next;
    Button back;
    int pageNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the screen to budgeting_reading.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debt_reading);

        //define text fields to update
        readingText = (TextView) findViewById(R.id.readingBody);
        pageNumber = (TextView) findViewById(R.id.pageNum);
        readingTitle = (TextView) findViewById(R.id.ruleTitle);
        readingPic = (ImageView) findViewById(R.id.readingPic);
    }

    private void setDebtElements(){
        readingText.setText(debtReadings.readings[pageNum]);
        pageNumber.setText(pageNum+1);
        readingTitle.setText(debtReadings.rules[pageNum]);
        //need to set drawable based on page num
    }

    public void debtNext(){
        pageNum++;
        setDebtElements();
    }

    public void debtBack(){
        pageNum--;
        setDebtElements();
    }
}
