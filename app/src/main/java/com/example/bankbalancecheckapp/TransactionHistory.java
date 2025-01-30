package com.example.bankbalancecheckapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class TransactionHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transaction_history);

        UserData userData = (UserData) getIntent().getSerializableExtra("user");

        ListView transactionListView = findViewById(R.id.transactionHistory);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_text_color, userData.getTransaction());
        transactionListView.setAdapter(adapter);
    }
}