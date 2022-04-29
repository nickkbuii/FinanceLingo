package com.financelingo.financelingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    Button switchToCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToCreate = findViewById(R.id.createButton);
        switchToCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivities();
            }
        });
    }


    private void switchActivities(){
        Intent switchActivityIntent = new Intent (this, create.class);
        startActivity(switchActivityIntent);
    }

}