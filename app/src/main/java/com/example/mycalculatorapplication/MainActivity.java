package com.example.mycalculatorapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewDisplay;
    private StringBuilder inputExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewDisplay);
        inputExpression = new StringBuilder();

        // Set OnClickListener for all buttons
        findViewById(R.id.button0).setOnClickListener(onClickListener);
        findViewById(R.id.button1).setOnClickListener(onClickListener);
        findViewById(R.id.button2).setOnClickListener(onClickListener);
        findViewById(R.id.button3).setOnClickListener(onClickListener);
        findViewById(R.id.button4).setOnClickListener(onClickListener);
        findViewById(R.id.button5).setOnClickListener(onClickListener);
        findViewById(R.id.button6).setOnClickListener(onClickListener);
        findViewById(R.id.button7).setOnClickListener(onClickListener);
        findViewById(R.id.button8).setOnClickListener(onClickListener);
        findViewById(R.id.button9).setOnClickListener(onClickListener);
        findViewById(R.id.buttonDot).setOnClickListener(onClickListener);
        findViewById(R.id.buttonAdd).setOnClickListener(onClickListener);
        findViewById(R.id.buttonSubtract).setOnClickListener(onClickListener);
        findViewById(R.id.buttonMultiply).setOnClickListener(onClickListener);
        findViewById(R.id.buttonDivide).setOnClickListener(onClickListener);
        findViewById(R.id.buttonEquals).setOnClickListener(onClickListener);
        findViewById(R.id.buttonClear).setOnClickListener(onClickListener);
        findViewById(R.id.buttonBracketLeft).setOnClickListener(onClickListener);
        findViewById(R.id.buttonBracketRight).setOnClickListener(onClickListener);
    }

    // OnClickListener for all buttons
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();
            if (buttonText.equals("=")) {
                evaluateExpression();
            } else if (buttonText.equals("C")) {
                clearExpression();
            } else {
                inputExpression.append(buttonText);
                textViewDisplay.setText(inputExpression.toString());
            }
        }
    };

    // Evaluate the input expression and display the result
    private void evaluateExpression() {
        try {
            String expression = inputExpression.toString();
            // Calculate the result of the expression
            double result = evaluate(expression);
            // Display the result
            textViewDisplay.setText(String.valueOf(result));
        } catch (Exception e) {
            textViewDisplay.setText("Error");
        }
    }

    // Clear the input expression
    private void clearExpression() {
        inputExpression.setLength(0);
        textViewDisplay.setText("");
    }

    // Evaluate the expression using a simple method
    private double evaluate(String expression) {
        // Split the expression by operators
        String[] tokens = expression.split("(?=[-+*/()])|(?<=[-+*/()])");
        // Simple evaluation logic (no precedence and no parentheses handling)
        double result = Double.parseDouble(tokens[0]);
        char operator = ' ';
        for (String token : tokens) {
            if (token.matches("[-+*/]")) {
                operator = token.charAt(0);
            } else {
                double operand = Double.parseDouble(token);
                switch (operator) {
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        result /= operand;
                        break;
                    default:
                        result = operand; // For the first number
                        break;
                }
            }
        }
        return result;
    }
}
