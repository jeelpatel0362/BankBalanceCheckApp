package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.updateProfileBtn).setOnClickListener(this::updateProfile);
        findViewById(R.id.updateMobileNumberBtn).setOnClickListener(this::updateMobileNumber);
        findViewById(R.id.changePassword).setOnClickListener(this::changePassword);

    }

    public void updateProfile(View view) {
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
    }

    public void updateMobileNumber(View view) {

        Intent intent = new Intent(this, UpdateMobileNumber.class);
        startActivity(intent);


    }

    public void changePassword(View view) {

        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);

    }

    public void logoutUser(View view) {
        SharedPreferences pref = getSharedPreferences("BankAppPrefs", MODE_PRIVATE);
        pref.edit().putBoolean("isLoggedIn", false).apply();

        Toast.makeText(this, "User logout successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginPage.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}