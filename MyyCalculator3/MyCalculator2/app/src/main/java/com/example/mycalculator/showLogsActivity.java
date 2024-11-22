package com.example.mycalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class showLogsActivity extends AppCompatActivity {

    private TextView logs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlog);

        logs = findViewById(R.id.logs);

        Exists();

        String logList = getLogs();
        logs.setText(logList);

        // Corrected the button ID to match the XML (lowercase 'home')
        Button home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to navigate to MainActivity
                Intent intent = new Intent(showLogsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Exists() {
        try {
            File file = new File(getFilesDir(), "List_of_Logs.txt");
            if (!file.exists()) {
                writeLog("Initial log entry: Log file created.\n");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error creating or checking log file", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeLog(String logMessage) {
        try {
            FileOutputStream fos = openFileOutput("List_of_Logs.txt", MODE_APPEND);
            fos.write(logMessage.getBytes());
            fos.close();
        } catch (IOException e) {
            Toast.makeText(this, "Error writing to log file", Toast.LENGTH_SHORT).show();
        }
    }

    private String getLogs() {
        StringBuilder logslist = new StringBuilder();

        try {
            FileInputStream list = openFileInput("List_of_Logs.txt");
            InputStreamReader reader = new InputStreamReader(list);

            int input;
            while ((input = reader.read()) != -1) {
                logslist.append((char) input);
            }
            reader.close();
        } catch (IOException e) {
            Toast.makeText(this, "Error reading logs", Toast.LENGTH_SHORT).show();
        }

        return logslist.toString();
    }
}
