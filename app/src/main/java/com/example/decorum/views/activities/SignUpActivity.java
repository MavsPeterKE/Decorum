package com.example.decorum.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.example.decorum.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.btlogin)
    Button btlogin;

    @BindView(R.id.btstart)
    RelativeLayout btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);

        btlogin.setOnClickListener(v -> startActivity(new Intent(SignUpActivity.this,LoginActivity.class)));

        btStart.setOnClickListener(v -> startActivity(new Intent(SignUpActivity.this,HomeActivity.class)));
    }
}
