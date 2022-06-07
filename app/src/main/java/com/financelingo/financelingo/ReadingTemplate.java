package com.financelingo.financelingo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ReadingTemplate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgeting_reading);
    }

    public void getDefinition(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(ReadingTemplate.this);
        String def = "";

        builder.setTitle(view.getTag().toString());
        builder.setMessage(def);

        Dialog dialog = builder.create();
        dialog.show();
    }
}