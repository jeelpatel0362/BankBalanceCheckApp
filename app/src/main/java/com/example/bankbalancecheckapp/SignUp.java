package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    private EditText etUserName, etAccountNumber, etMobileNumber, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        etUserName = findViewById(R.id.userName);
        etAccountNumber = findViewById(R.id.accountNumber);
        etMobileNumber = findViewById(R.id.mobileNumber);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirmPassword);

        findViewById(R.id.signUpBtn).setOnClickListener(v -> signUpUser());

        findViewById(R.id.login).setOnClickListener(v -> {

            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        });

    }

    private void signUpUser() {

        String userName = etUserName.getText().toString().trim();
        String accountNumber = etAccountNumber.getText().toString().trim();
        String mobileNumber = etMobileNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();


        if (TextUtils.isEmpty(userName)) {
            etUserName.setError("Please enter your name");
            etUserName.requestFocus();
            return;
        } else if (TextUtils.isEmpty(accountNumber)) {
            etAccountNumber.setError("Please enter your account number");
            etAccountNumber.requestFocus();
            return;
        } else if (TextUtils.isEmpty(mobileNumber)) {
            etMobileNumber.setError("Please enter your Mobile number");
            etMobileNumber.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        } else if (password.length() < 6) {
            etPassword.setError("Password should be at least 6 characters");
            etPassword.requestFocus();
            return;
        } else if (!password.matches(".*[A-Z].*")) {
            etPassword.setError("Password should have at least 1 Uppercase characters");
            etPassword.requestFocus();
            return;
        } else if (!password.matches(".*[a-z].*")) {
            etPassword.setError("Password should have at least 1 lower characters");
            etPassword.requestFocus();
            return;
        } else if (!password.matches(".*[0-9].*")) {
            etPassword.setError("Password should have at least 1 digit");
            etPassword.requestFocus();

            return;
        } else if (!password.matches(".*[@#$%^&*+=_!].*")) {
            etPassword.setError("Password should have at least 1 special character");
            etPassword.requestFocus();
            return;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Please enter your confirm password");
            etConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password and confirm password should be same");
            etConfirmPassword.requestFocus();
            return;
        }
        getSharedPreferences("BankAppPrefs", MODE_PRIVATE).edit().putString("userName", userName).putString("accountNumber", accountNumber).putString("mobileNumber", mobileNumber).putString("password", password).putBoolean("isLoggedIn", false).apply();
        Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUp.this, LoginPage.class));
        finish();

    }
}