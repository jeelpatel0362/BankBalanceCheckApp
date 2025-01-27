package com.example.bankbalancecheckapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoanServices extends AppCompatActivity {

    private EditText etLoanAmount,etLoanDuration;
    private TextView tvEmiResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan_services);


        etLoanAmount = findViewById(R.id.loanAmount_content);
        etLoanDuration = findViewById(R.id.loanDuration_content);
        tvEmiResult = findViewById(R.id.tvEmiResult);


        findViewById(R.id.calculateBtn).setOnClickListener(this::calculateEMI);
    }

    private void calculateEMI(View view)
    {
        double loanAmounts = Double.parseDouble(etLoanAmount.getText().toString().trim());
        int durations = Integer.parseInt(etLoanDuration.getText().toString().trim());
        double intrestRate = .12;
        double emi = (loanAmounts * (intrestRate/12) * Math.pow(1+(intrestRate/12),durations))/(Math.pow(1+(intrestRate/12),durations) - 1) ;

        tvEmiResult.setText(String.format("EMI : ₹%.2f",emi));


    }
}