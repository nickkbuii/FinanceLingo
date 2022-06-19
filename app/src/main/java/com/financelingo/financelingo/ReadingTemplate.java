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

        readingView = findViewById(R.id.readingBody);
        updateReading();
    }

    public void getDefinition(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(ReadingTemplate.this);
        String def = "";

        builder.setTitle(view.getTag().toString());
        builder.setMessage(def);

        Dialog dialog = builder.create();
        dialog.show();
    }

    public void updateReading(){
        readingView.setText(budgetingReadings.readings[pageNumber]);
    }
}
