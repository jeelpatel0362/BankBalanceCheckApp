package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TransferMoney extends AppCompatActivity {
    private EditText recipient , amount ;
    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transfer_money);

        userData = (UserData) getIntent().getSerializableExtra("user");

        recipient = findViewById(R.id.accountNumber_content);
        amount = findViewById(R.id.amount_content);

        findViewById(R.id.sendMoneyBtn).setOnClickListener(this::sendMoney);
    }

    private  void sendMoney(View view){


        String recipientText = recipient.getText().toString().trim();
        String amountText = amount.getText().toString().trim();

        if(amountText.isEmpty() && recipientText.isEmpty()){
            Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show();
            return ;
        }

        if(recipientText.isEmpty())
        {
            Toast.makeText(this,"Recipient account number field is Empty",Toast.LENGTH_SHORT).show();
            return ;
        } else if (recipientText.equals(userData.getAccountNumber())) {
            Toast.makeText(this,"You cannot send money to your own account",Toast.LENGTH_SHORT).show();
            return ;

        }

        if(amountText.isEmpty()){
            Toast.makeText(this,"Amount field is Empty",Toast.LENGTH_SHORT).show();
            amount.setError("Amount is Empty");
            return ;
        }

        double amount = Double.parseDouble(amountText);

        if(amount > userData.getBalance())
        {
            Toast.makeText(this,"Insufficient Balance",Toast.LENGTH_SHORT).show();
            return;
        }

        userData.setBalance(userData.getBalance()-amount);
        userData.addTransaction("₹" + amount +  " sent to " + recipientText );

        Toast.makeText(this,"Transaction successfull",Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this,HomeScreen.class);
        intent.putExtra("user",userData);
        startActivity(intent);
    }

}