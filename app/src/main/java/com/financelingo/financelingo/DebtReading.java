package com.financelingo.financelingo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import Lessons.DebtReadings;

public class DebtReading extends AppCompatActivity {

    private DebtReadings debtReadings = new DebtReadings();
    private int pageNum = 0;
    
    TextView pageNumber;
    TextView readingText;
    TextView readingTitle;
    ImageView readingPic;
    Button next;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the screen to budgeting_reading.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debt_reading);

        //define text fields to update
        readingText = (TextView) findViewById(R.id.taxes_readingBody);
        pageNumber = (TextView) findViewById(R.id.taxes_pageNum);
        readingTitle = (TextView) findViewById(R.id.taxes_ruleTitle);
        readingPic = (ImageView) findViewById(R.id.taxes_readingPic);

        next = (Button)findViewById(R.id.taxes_nextPage);
        back = (Button)findViewById(R.id.taxes_backPage);

        //set initial elements
        setDebtElements();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pageNum==5){
                    pageNum=0;
                }else{
                    pageNum++;
                }
                setDebtElements();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pageNum==0){
                    pageNum=5;
                }else{
                    pageNum--;
                }
                setDebtElements();
            }
        });

    }

    public void setDebtElements(){
        readingText.setText(debtReadings.readings[pageNum]);
        pageNumber.setText(String.valueOf(pageNum+1));
        readingTitle.setText(debtReadings.rules[pageNum]);
        readingPic.setImageDrawable(getDrawable(debtReadings.pictures[pageNum]));
    }

}
