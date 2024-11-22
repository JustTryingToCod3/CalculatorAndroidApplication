package com.example.mycalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class functionCalculatorActivity extends AppCompatActivity {

    private EditText num1, keyword, result;
    private Button submit, home;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.functioncalculator);

        num1 = findViewById(R.id.num1);
        keyword = findViewById(R.id.keyword);
        result = findViewById(R.id.result);
        submit = findViewById(R.id.submit);
        home = findViewById(R.id.home); // Ensure the home button exists in the XML

        // Handle the submit button click
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String num1Str = num1.getText().toString();
                String keywordStr = keyword.getText().toString();

                if (num1Str.isEmpty() || keywordStr.isEmpty()) {
                    Toast.makeText(functionCalculatorActivity.this, "Enter all values", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    double op1 = Double.parseDouble(num1Str);
                    double calcResult = 0;

                    switch (keywordStr.toLowerCase()) {
                        case "log":
                            calcResult = Math.log(op1);
                            break;
                        case "exp":
                            calcResult = Math.exp(op1);
                            break;
                        case "sin":
                            calcResult = Math.sin(op1);
                            break;
                        case "cos":
                            calcResult = Math.cos(op1);
                            break;
                        case "tan":
                            calcResult = Math.tan(op1);
                            break;
                        case "inv":
                            calcResult = 1 / op1;
                            break;
                        default:
                            Toast.makeText(functionCalculatorActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
                            return;
                    }
                    result.setText(String.valueOf(calcResult));

                    // Log the input and result
                    String logMessage = "Function: " + keywordStr + " Input: " + num1Str + " Result: " + calcResult + "\n";
                    writeLog(logMessage);

                } catch (NumberFormatException e) {
                    Toast.makeText(functionCalculatorActivity.this, "Invalid number input", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Handle the home button click
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the main activity (main menu)
                Intent intent = new Intent(functionCalculatorActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Optional: Call finish() to close the current activity
            }
        });
    }

    // Write log message to file
    private void writeLog(String logMessage) {
        try {
            FileOutputStream fos = openFileOutput("List_of_Logs.txt", MODE_APPEND);
            fos.write(logMessage.getBytes());
            fos.close();
        } catch (IOException e) {
            Toast.makeText(this, "Error writing to log file", Toast.LENGTH_SHORT).show();
        }
    }
}
