package com.example.decorum.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.decorum.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(R.id.btReset)
    Button btReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForgotPasswordActivity.this, "Password Reset Submitted Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
            }
        });
    }
}
