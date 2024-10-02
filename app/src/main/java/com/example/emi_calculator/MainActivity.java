package com.example.emi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get Button and TextView ID's
        Button calculateBtn = findViewById(R.id.btnCalculate);

        TextInputEditText mortgageAmountET = findViewById(R.id.mortgageAmount);
        TextInputEditText interestRateET = findViewById(R.id.interestRate);
        TextInputEditText mortgagePeriodET = findViewById(R.id.mortgagePeriod);
        TextView errorTextView = findViewById(R.id.displayError);

        // Set an onClickListener for when user presses calculateBtn
        calculateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Get the values that the user inputs
                String mortgageAmountString = mortgageAmountET.getText().toString();
                String interestRateString = interestRateET.getText().toString();
                String mortgagePeriodString = mortgagePeriodET.getText().toString();

                // Make sure the input fields are not empty
                if (!mortgageAmountString.isEmpty() && !interestRateString.isEmpty() && !mortgagePeriodString.isEmpty()) {
                    // Get the values and convert the them to double
                    double mortgageAmount = Double.parseDouble(mortgageAmountString);
                    double interestRate = Double.parseDouble(interestRateString);
                    double monthlyInterestRate = interestRate / 12 / 100;

                    // Get integer values
                    int mortgagePeriod = Integer.parseInt(mortgagePeriodString);
                    int numberOfMonthlyPayments = mortgagePeriod * 12;

                    // Calculate EMI using inputs the user gave
                    double EMI = mortgageAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments)) /
                            (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - 1);

                    // Redirect user to calculation_activity.java and send calculated EMI value to display to user
                    Intent calculationIntent = new Intent(MainActivity.this, calculation_activity.class);
                    calculationIntent.putExtra("emi", EMI);

                    startActivity(calculationIntent);
                }
                else
                {
                    // Display error message if any field is left empty
                    errorTextView.setText("Please fill in all fields");
                }
            }
        });

    }
}