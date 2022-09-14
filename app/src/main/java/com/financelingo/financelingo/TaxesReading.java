package com.financelingo.financelingo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import Lessons.TaxesReadings;

public class TaxesReading extends AppCompatActivity {

    private TaxesReadings taxesReadings = new TaxesReadings();
    private int tax_pageNum = 0;

    Button tax_next;
    Button tax_back;
    TextView tax_page_number;
    TextView tax_readingBod;
    TextView tax_title;
    ImageView tax_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //set the screen to taxes_reading.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taxes_reading);

        //declare and define buttons, text and images
        tax_next = (Button)findViewById(R.id.taxes_nextPage);
        tax_back = (Button)findViewById(R.id.taxes_backPage);
        tax_page_number = (TextView)findViewById(R.id.taxes_pageNum);
        tax_readingBod = (TextView)findViewById(R.id.taxes_readingBody);
        tax_title = (TextView)findViewById(R.id.taxes_ruleTitle);
        tax_pic = (ImageView)findViewById(R.id.taxes_readingPic);

        //set initial texts and images
        updateReading();

        //set listener for next button
        tax_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tax_pageNum==4){
                    tax_pageNum=0;
                }else{
                    tax_pageNum++;
                }
                updateReading();
            }
        });

        //set listener for back button
        tax_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view){
               if(tax_pageNum==0){
                   tax_pageNum=4;
               }else{
                   tax_pageNum--;
               }
               updateReading();
           }
        });
    }

    public void updateReading(){
        tax_page_number.setText(String.valueOf(tax_pageNum+1));
        tax_readingBod.setText(taxesReadings.readings[tax_pageNum][1]);
        tax_title.setText(taxesReadings.readings[tax_pageNum][0]);
        tax_pic.setImageDrawable(getDrawable(taxesReadings.pics[tax_pageNum]));
    }

}
