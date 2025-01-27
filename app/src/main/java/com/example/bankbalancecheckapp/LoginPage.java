package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginPage extends AppCompatActivity {

    private EditText etAccountNumber, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        etAccountNumber = findViewById(R.id.accountNumber_content);
        etPassword = findViewById(R.id.password_content);

        findViewById(R.id.loginUpBtn).setOnClickListener(v -> loginUser());

        findViewById(R.id.signUp).setOnClickListener(v -> {
            startActivity(new Intent(this, SignUp.class));
            finish();
        });

    }

    private void loginUser() {

        String accountNumber = etAccountNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        SharedPreferences prefs = getSharedPreferences("BankAppPrefs", MODE_PRIVATE);
        String storedAccountNumber = prefs.getString("accountNumber", null);
        String storedPassword = prefs.getString("password", null);

        if (TextUtils.isEmpty(accountNumber) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!accountNumber.equals(storedAccountNumber)) {
            etAccountNumber.setError("Invalid Account Number");
            etAccountNumber.requestFocus();
            return;
        } else if (!password.equals(storedPassword)) {
            etPassword.setError("Password does not match.");
            etPassword.requestFocus();
            return;
        } else {

            prefs.edit().putBoolean("isLoggedIn", true).apply();

            String name = prefs.getString("userName", "user");
            UserData userData = new UserData(name, storedAccountNumber, 10000.0);
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeScreen.class);
            intent.putExtra("user", userData);
            startActivity(intent);
            finish();

        }


    }

}