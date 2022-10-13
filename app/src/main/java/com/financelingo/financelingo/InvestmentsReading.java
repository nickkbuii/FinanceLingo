package com.financelingo.financelingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Lessons.InvestmentsReadings;

public class InvestmentsReading extends AppCompatActivity {
    InvestmentsReadings curric = new InvestmentsReadings();
    int pageNum = 0;

    Button next;
    Button back;
    TextView pgNum;
    TextView bod;
    TextView title;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investments_reading);

        next = findViewById(R.id.inv_nextPage);
        back = findViewById(R.id.inv_backPage);
        pgNum = findViewById(R.id.inv_pageNum);
        bod = findViewById(R.id.inv_readingBody);
        title = findViewById(R.id.inv_ruleTitle);
        pic = findViewById(R.id.inv_readingPic);

        updateReading();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pageNum==5){
                    pageNum=0;
                }else{
                    pageNum++;
                }
                updateReading();
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
                updateReading();
            }
        });
    }

    public void updateReading(){
        pgNum.setText(String.valueOf(pageNum+1));
        bod.setText(curric.readings[pageNum][1]);
        title.setText(curric.readings[pageNum][0]);
        pic.setImageDrawable(getDrawable(curric.inv_pics[pageNum]));
    }

    public void inv_r_toHome(View v){
        switchActivities(InvestmentsReading.this, Lessons.class);
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }

}
