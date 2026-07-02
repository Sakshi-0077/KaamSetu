package com.example.kaamsetuog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class SignupActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPassword, etConfirmPassword, etMobile, etAddress;
    private Button btnSignup;
    private TextView tvLogin;
    private String generatedOtp;

    private static final int SMS_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFullName = findViewById(R.id.editTextText4);
        etEmail = findViewById(R.id.editTextText2);
        etPassword = findViewById(R.id.editTextText3);
        etConfirmPassword = findViewById(R.id.editTextText);
        etMobile = findViewById(R.id.editTextText6);
        etAddress = findViewById(R.id.editTextText7);
        btnSignup = findViewById(R.id.button);
        tvLogin = findViewById(R.id.textView4);

        btnSignup.setOnClickListener(v -> {
            if (validateInputs()) {
                checkPermissionAndSendSms();
            }
        });

        tvLogin.setOnClickListener(v -> {
            finish(); // Go back to Login
        });
    }

    private void checkPermissionAndSendSms() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            sendOtpSms();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    private void sendOtpSms() {
        String phoneNumber = etMobile.getText().toString().trim();
        generatedOtp = String.format("%04d", new Random().nextInt(10000));

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, "OTP generated is: " + generatedOtp, null, null);
            Toast.makeText(this, "OTP sent to " + phoneNumber, Toast.LENGTH_SHORT).show();

            // Pass OTP and phone number and Full Name and Address to OtpActivity
            Intent intent = new Intent(SignupActivity.this, OtpActivity.class);
            intent.putExtra("otp", generatedOtp);
            intent.putExtra("mobile", phoneNumber);
            intent.putExtra("fullName", etFullName.getText().toString().trim());
            intent.putExtra("address", etAddress.getText().toString().trim());
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOtpSms();
            } else {
                Toast.makeText(this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateInputs() {
        if (etFullName.getText().toString().isEmpty() ||
            etEmail.getText().toString().isEmpty() ||
            etPassword.getText().toString().isEmpty() ||
            etMobile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        return true;
    }
}