package com.example.kaamsetuog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtpActivity extends AppCompatActivity {

    private EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;
    private Button buttonVerify;
    private TextView textViewResend;
    private String correctOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpDigit1 = findViewById(R.id.editText1);
        otpDigit2 = findViewById(R.id.editText2);
        otpDigit3 = findViewById(R.id.editText3);
        otpDigit4 = findViewById(R.id.editText4);
        buttonVerify = findViewById(R.id.button);
        textViewResend = findViewById(R.id.textView3);

        // Get the OTP sent from SignupActivity
        correctOtp = getIntent().getStringExtra("otp");

        setupOtpInputs();

        buttonVerify.setOnClickListener(v -> {
            String enteredOtp = otpDigit1.getText().toString() +
                                otpDigit2.getText().toString() +
                                otpDigit3.getText().toString() +
                                otpDigit4.getText().toString();
            
            if (enteredOtp.length() == 4) {
                if (enteredOtp.equals(correctOtp)) {
                    // Save login state and user name
                    SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("is_logged_in", true);
                    
                    String fullName = getIntent().getStringExtra("fullName");
                    if (fullName != null) {
                        editor.putString("username", fullName);
                    }

                    String address = getIntent().getStringExtra("address");
                    if (address != null && !address.isEmpty()) {
                        editor.putString("address", address);
                        // Extract city (assuming last part of address is city or just use address as location)
                        String[] parts = address.split(",");
                        String city = parts[parts.length - 1].trim();
                        editor.putString("city", city);
                    }

                    editor.apply();

                    Toast.makeText(OtpActivity.this, "OTP Verified Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OtpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(OtpActivity.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(OtpActivity.this, "Please enter 4-digit OTP", Toast.LENGTH_SHORT).show();
            }
        });

        textViewResend.setOnClickListener(v -> {
            // Logic for resending OTP could be added here
            Toast.makeText(OtpActivity.this, "OTP Resent!", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupOtpInputs() {
        otpDigit1.addTextChangedListener(new OtpTextWatcher(otpDigit1, otpDigit2));
        otpDigit2.addTextChangedListener(new OtpTextWatcher(otpDigit2, otpDigit3));
        otpDigit3.addTextChangedListener(new OtpTextWatcher(otpDigit3, otpDigit4));
        otpDigit4.addTextChangedListener(new OtpTextWatcher(otpDigit4, null));
    }

    private class OtpTextWatcher implements TextWatcher {
        private View currentView;
        private View nextView;

        public OtpTextWatcher(View currentView, View nextView) {
            this.currentView = currentView;
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 1 && nextView != null) {
                nextView.requestFocus();
            }
        }
    }
}