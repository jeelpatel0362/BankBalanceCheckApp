package com.example.bankbalancecheckapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 100;
    private CircleImageView profile_image;
    private EditText etUserName, etAccountNumber, etMobileNumber, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        etUserName = findViewById(R.id.userName_content);
        etAccountNumber = findViewById(R.id.accountNumber_content);
        etMobileNumber = findViewById(R.id.mobileNumber_content);
        etPassword = findViewById(R.id.password_content);
        etConfirmPassword = findViewById(R.id.confirmPassword_content);
        profile_image = findViewById(R.id.user_profile);

        findViewById(R.id.signUpBtn).setOnClickListener(v -> signUpUser());

        findViewById(R.id.login).setOnClickListener(v -> {

            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        });

    }

    private void signUpUser(){

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

        File tempFile;
        try {
            tempFile = saveImageToCache();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getSharedPreferences("BankAppPrefs", MODE_PRIVATE).edit().putString("profile_image", tempFile.getAbsolutePath()).putString("userName", userName).putString("accountNumber", accountNumber).putString("mobileNumber", mobileNumber).putString("password", password).putBoolean("isLoggedIn", false).apply();
        Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUp.this, LoginPage.class));
        finish();

    }

    public void pickImage(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            profile_image.setImageURI(uri);
        }
    }

    private File saveImageToCache() throws IOException {
        if (profile_image.getDrawable() == null) {
            throw new IOException("No image selected");
        }
        Bitmap bitmap = ((BitmapDrawable) profile_image.getDrawable()).getBitmap();
        File tempFile = new File(getCacheDir(), "profile_picture.jpg");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
        return tempFile;
    }
}