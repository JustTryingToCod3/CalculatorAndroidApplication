package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button basicCalculator = findViewById(R.id.basiccalculator);
        Button functionCalculator = findViewById(R.id.functioncalculator);
        Button showLog = findViewById(R.id.showlog);

        basicCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, basicCalculatorActivity.class);
                startActivity(intent);
            }
        });

        functionCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, functionCalculatorActivity.class);
                startActivity(intent);
            }
        });

        showLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, showLogsActivity.class);
                startActivity(intent);
            }
        });
    }
}
