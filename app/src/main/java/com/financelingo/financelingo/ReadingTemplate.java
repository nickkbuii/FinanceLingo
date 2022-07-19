package com.financelingo.financelingo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ReadingTemplate extends AppCompatActivity {

    //access readings and definitions from BudgetingReadings.java and initialize reading text view, next/back buttons
    //set default page number to 0
    private BudgetingReadings budgetingReadings = new BudgetingReadings();
    private TextView readingView;
    private int pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the screen to budgeting_reading.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_reading);
    }

    //increment page number and update reading text view accordingly
    //when at the last page, loop back to the first page
    public void flipToNextPage(){
        if (pageNumber==3) {
            pageNumber = 0;
        }else {
            pageNumber++;
        }
        readingView.setText(budgetingReadings.readings[pageNumber]);
    }

    //reduce page number and update reading text view accordingly
    //when at the first page, loop to the last page
    public void flipToPreviousPage(){
        if (pageNumber==0){
            pageNumber=3;
        }else{
            pageNumber--;
        }
        readingView.setText(budgetingReadings.readings[pageNumber]);
    }
}
