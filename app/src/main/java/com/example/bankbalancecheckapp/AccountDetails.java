package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountDetails extends AppCompatActivity {

    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_details);

        CircleImageView profileImageView = findViewById(R.id.user_profile);

        userData = (UserData) getIntent().getSerializableExtra("user");
        TextView nameText = findViewById(R.id.nameText);
        TextView accountNumberText = findViewById(R.id.accountNumber);
        TextView balanceText = findViewById(R.id.balanceText);
        TextView mobileNumber = findViewById(R.id.mobileNumber);


        if (userData != null) {
            nameText.setText("Name: " + userData.getName());
            accountNumberText.setText("Account Number: " + userData.getAccountNumber());
            balanceText.setText("Account Balance: " + userData.getBalance());
            mobileNumber.setText("Mobile Number: " + userData.getMobileNumber());
        }
    }
        public void logoutUser (View view){
            SharedPreferences pref = getSharedPreferences("BankAppPrefs", MODE_PRIVATE);

            pref.edit().putBoolean("isLoggedIn",false).apply();

            Toast.makeText(this,"User logout successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this , LoginPage.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

}

