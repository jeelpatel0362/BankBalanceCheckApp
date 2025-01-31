package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateMobileNumber extends AppCompatActivity {

    private EditText newMobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_mobile_number);

        newMobileNumber = findViewById(R.id.newMobileNumber_content);

        findViewById(R.id.updateMobileNumberBtn).setOnClickListener(this::updateMobileNumber);

    }

    public void updateMobileNumber(View view) {

        String newNumber = newMobileNumber.getText().toString().trim();

        SharedPreferences prefs = getSharedPreferences("BankAppPrefs", MODE_PRIVATE);
        String storedMobileNumber = prefs.getString("mobileNumber", null);
        String storedAccountNumber = prefs.getString("accountNumber", null);
        String name = prefs.getString("userName", null);

        if (newNumber.isEmpty()) {
            newMobileNumber.setError("Please enter your new mobile number");
            newMobileNumber.requestFocus();
            return;
        }
        if (newNumber.equals(storedMobileNumber)) {
            Toast.makeText(this, "This is already your current mobile number", Toast.LENGTH_SHORT).show();
            newMobileNumber.requestFocus();
            return;
        }else {
             UserData userData = new UserData(name, storedAccountNumber, 100000.0, newNumber);

            prefs.edit().putString("mobileNumber", newNumber).apply();
            Toast.makeText(this, "Mobile number updated successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeScreen.class);
            intent.putExtra("user", userData);
            startActivity(intent);
            finish();
        }
    }
}
