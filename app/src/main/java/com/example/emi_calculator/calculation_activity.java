package com.example.emi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class calculation_activity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get Button and TextView ID's
        Button returnBtn = findViewById(R.id.btnReturn);
        TextView displayCalc = findViewById(R.id.displayCalculation);

        // Get intent and emi value
        Intent calculationDataIntent = getIntent();
        double emi = calculationDataIntent.getDoubleExtra("emi", 0.0);

        // Format as string with 2 decimal places
        String emiMessage = String.format("Monthly Payment: $%.2f", emi);
        displayCalc.setText(emiMessage);

        // Set an onClickListener for when user presses returnBtn
        returnBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Redirect user to MainActivity.java
                Intent mainIntent = new Intent(calculation_activity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}