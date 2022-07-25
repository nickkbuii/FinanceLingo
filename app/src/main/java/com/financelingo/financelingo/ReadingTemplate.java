package com.financelingo.financelingo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class ReadingTemplate extends AppCompatActivity {

    //access readings and definitions from BudgetingReadings.java and initialize reading text view, next/back buttons
    //set default page number to 0
    private BudgetingReadings budgetingReadings = new BudgetingReadings();
    private int pageNumber = 0;
    Button next;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the screen to budgeting_reading.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_reading);

        //define text fields to update
        TextView readingView = (TextView)findViewById(R.id.readingBody);
        TextView pageNum = (TextView)findViewById(R.id.pageNum);
        TextView rule = (TextView)findViewById(R.id.ruleTitle);
        ImageView pic = (ImageView)findViewById(R.id.readingPic);

        rule.setText(budgetingReadings.rules[pageNumber]);

        //set and define button listener for flipping next
        next = (Button)findViewById(R.id.nextPage);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (pageNumber==3) {
                    pageNumber = 0;
                }else {
                    pageNumber++;
                }
                readingView.setText(budgetingReadings.readings[pageNumber]);
                pageNum.setText(String.valueOf(pageNumber+1));
                rule.setText(budgetingReadings.rules[pageNumber]);
                pic.setImageDrawable(getDrawable(budgetingReadings.imageList[pageNumber]));
            }
        });

        //set and define button listener for flipping back
        back = (Button)findViewById(R.id.backPage);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (pageNumber==0){
                    pageNumber=3;
                }else{
                    pageNumber--;
                }
                readingView.setText(budgetingReadings.getReading(pageNumber));
                pageNum.setText(String.valueOf(pageNumber+1));
                rule.setText(budgetingReadings.getRule(pageNumber));
                pic.setImageDrawable(getDrawable(budgetingReadings.imageList[pageNumber]));
            }
        });

    }
}
