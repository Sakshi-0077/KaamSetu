package com.example.kaamsetuog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountCenterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_center);

        EditText newPass = findViewById(R.id.newPassword);
        EditText confirmPass = findViewById(R.id.confirmPassword);
        Button resetBtn = findViewById(R.id.resetPasswordBtn);

        resetBtn.setOnClickListener(v -> {
            String pass = newPass.getText().toString();
            String confirm = confirmPass.getText().toString();

            if (pass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            } else if (pass.equals(confirm)) {
                Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_SHORT).show();
                newPass.setText("");
                confirmPass.setText("");
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
