package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {

            SharedPreferences pref = getSharedPreferences("BankAppPrefs", MODE_PRIVATE);

            boolean isRegistered = pref.contains("accountNumber");
            boolean isLoggedIn = pref.getBoolean("isLoggedIn", false);


            if (isRegistered) {
                if (isLoggedIn) {
                    String name = pref.getString("userName", "user");
                    String accountNumber = pref.getString("accountNumber", "");
                    double balance = 100000.0;
                    String mobileNumber = pref.getString("mobileNumber", "");

                    UserData userData = new UserData(name, accountNumber, balance,mobileNumber);

                    Intent intent = new Intent(this, HomeScreen.class);
                    intent.putExtra("user",userData);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(this, LoginPage.class);
                    startActivity(intent);
                    finish();
                }
            }else {
                Intent intent = new Intent(this, SignUp.class);
                startActivity(intent);
                finish();

            }
        }, 3000);

    }
}