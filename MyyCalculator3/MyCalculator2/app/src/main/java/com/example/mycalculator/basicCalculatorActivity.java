package com.example.mycalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;

public class basicCalculatorActivity extends AppCompatActivity {

    private EditText operator1, operator2, operand, result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basiccalculator);

        operator1 = findViewById(R.id.operator1);
        operator2 = findViewById(R.id.operator2);
        operand = findViewById(R.id.operand);
        result = findViewById(R.id.result);
        Button calculate = findViewById(R.id.calculate);
        Button home = findViewById(R.id.home);

        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String operandStr = operand.getText().toString();
                String operator1Str = operator1.getText().toString();
                String operator2Str = operator2.getText().toString();

                if (operator1Str.isEmpty() || operator2Str.isEmpty() || operandStr.isEmpty()) {
                    Toast.makeText(basicCalculatorActivity.this, "Enter all values", Toast.LENGTH_LONG).show();
                }

                try {
                    double op1 = Double.parseDouble(operator1Str);
                    double op2 = Double.parseDouble(operator2Str);

                    double calcResult;

                    switch (operandStr) {
                        case "+":
                            calcResult = op1 + op2;
                            break;
                        case "-":
                            calcResult = op1 - op2;
                            break;
                        case "*":
                            calcResult = op1 * op2;
                            break;
                        case "/":
                            if (op2 != 0) {
                                calcResult = op1 / op2;
                                break;
                            } else {
                                Toast.makeText(basicCalculatorActivity.this, "Can't Divide by 0", Toast.LENGTH_LONG).show();
                                return;
                            }
                        case "^":
                            calcResult = Math.pow(op1, op2);
                            break;
                        default:
                            Toast.makeText(basicCalculatorActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
                            return;
                    }
                    result.setText(String.valueOf(calcResult));

                    String logMessage = "Basic Calculation: " + op1 + " " + operandStr + " " + op2 + " Result: " + calcResult + "\n";
                    writeLog(logMessage);

                } catch (NumberFormatException e) {
                    Toast.makeText(basicCalculatorActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
                }
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(basicCalculatorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
}
