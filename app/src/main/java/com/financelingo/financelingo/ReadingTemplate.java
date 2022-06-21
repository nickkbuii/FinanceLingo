package com.financelingo.financelingo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ReadingTemplate extends AppCompatActivity {

    private BudgetingReadings budgetingReadings = new BudgetingReadings();
    private TextView readingView;
    private Button nextPage;
    private Button backPage;

    private int pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_reading);

        nextPage = findViewById(R.id.nextPage);

        readingView = findViewById(R.id.readingBody);
        readingView.setText(budgetingReadings.readings[pageNumber]);

        nextPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                flipToNextPage();
            }
        });

        backPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                flipToPreviousPage();
            }
        });
    }

    public void getDefinition(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(ReadingTemplate.this);
        String def = "";

        builder.setTitle(view.getTag().toString());
        builder.setMessage(def);

        Dialog dialog = builder.create();
        dialog.show();
    }

    public void flipToNextPage(){
        if (pageNumber==3) {
            pageNumber = 0;
        }else {
            pageNumber++;
        }
        readingView.setText(budgetingReadings.readings[pageNumber]);
    }

    public void flipToPreviousPage(){
        if (pageNumber==0){
            pageNumber=3;
        }else{
            pageNumber--;
        }
        readingView.setText(budgetingReadings.readings[pageNumber]);
    }

}
