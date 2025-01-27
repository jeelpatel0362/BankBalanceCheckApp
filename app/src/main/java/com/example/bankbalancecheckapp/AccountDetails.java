package com.example.bankbalancecheckapp;

import android.content.Intent;
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


        if (userData != null) {
            nameText.setText("Name: " + userData.getName());
            accountNumberText.setText("Account Number: " + userData.getAccountNumber());
            balanceText.setText("Account Balance: " + userData.getBalance());
        }
    }
        public void logoutUser (View view){
            Toast.makeText(this, "User logged out successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SplashScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

}

