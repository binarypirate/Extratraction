package com.example.bundleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bundleapplication.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding mResultBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(mResultBinding.getRoot());

        String[] data = getIntent().getStringArrayExtra("data_to_show");

        String displayText = "";

        for (String val: data) {
            displayText = displayText + val + "\n";
        }

        mResultBinding.displayBox.setText(displayText);
    }
}