package com.example.bundleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bundleapplication.databinding.ActivityMainBinding;
import com.example.bundleapplication.databinding.ActivityResultBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    private void setErrorWithFocus(EditText editText, String errorMessage) {
        editText.setError(errorMessage);
        editText.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.filename1.setText("Hello,______ Riddim.mp4");
        mBinding.filename2.setText("Dwayne, Jo___hnson.mkv");
        mBinding.filename3.setText("Lords of Rings.avi");

        mBinding.nextBtn.setOnClickListener(v -> {

            String firstFile = mBinding.filename1.getText().toString().trim();
            String secondFile = mBinding.filename2.getText().toString().trim();
            String thirdFile = mBinding.filename3.getText().toString().trim();

            if (firstFile.isEmpty()) {
                setErrorWithFocus(mBinding.filename1, "Please Enter filename");
            } else if (secondFile.isEmpty()) {
                setErrorWithFocus(mBinding.filename2, "Please Enter filename");
            } else if (thirdFile.isEmpty()) {
                setErrorWithFocus(mBinding.filename3, "Please Enter filename");
            } else if (!firstFile.contains(".")) {
                setErrorWithFocus(mBinding.filename1, "Please Enter valid name");
            } else if (!secondFile.contains(".")) {
                setErrorWithFocus(mBinding.filename2, "Please Enter valid name");
            } else if (!thirdFile.contains(".")) {
                setErrorWithFocus(mBinding.filename3, "Please Enter valid name");
            } else {
                boolean allValid = false;

                if (firstFile.lastIndexOf(".") != firstFile.length() - 1) {
                    allValid = true;
                }

                if (allValid && secondFile.lastIndexOf(".") != secondFile.length() - 1) {
                    allValid = true;
                }

                if (allValid && thirdFile.lastIndexOf(".") != thirdFile.length() - 1) {
                    allValid = true;
                }

                if (!allValid) {
                    Toast.makeText(this, "Please Enter valid file names!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ExtensionsActivity.class);
                    intent.putExtra("filenames", new FilesData(
                            firstFile, secondFile, thirdFile
                    ));
                    startActivity(intent);
                }
            }
        });
    }
}