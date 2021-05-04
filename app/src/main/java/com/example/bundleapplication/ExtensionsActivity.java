package com.example.bundleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.CaptivePortal;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bundleapplication.databinding.ActivityExtensionsBinding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class ExtensionsActivity extends AppCompatActivity {
    ActivityExtensionsBinding mExBinding;
    Intent mIntent;
    String[] mData = new String[3];

    private String[] replaceCharacter(FilesData filesData, String oldChar, String newChar) {
        mData[0] = filesData.filename1.replace(oldChar, newChar);
        mData[1] = filesData.filename2.replace(oldChar, newChar);
        mData[2] = filesData.filename3.replace(oldChar, newChar);
        return mData;
    }

    private String[] getExtensionWithoutLength(FilesData filesData) {
        mData[0] = String.valueOf(filesData.filename1.substring(0, filesData.filename1.lastIndexOf(".")).length());
        mData[1] = String.valueOf(filesData.filename2.substring(0, filesData.filename2.lastIndexOf(".")).length());
        mData[2] = String.valueOf(filesData.filename3.substring(0, filesData.filename3.lastIndexOf(".")).length());
        return mData;
    }

    private String[] getFileNamesWithSpacesRemoved(FilesData filesData) {
        return replaceCharacter(filesData, " ", "");
    }

    private String[] removeAllUnderscores(FilesData filesData) {
        return replaceCharacter(filesData, "_", "");
    }

    private void displayData(String[] data) {
        startActivity(mIntent.putExtra("data_to_show", data));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExBinding = ActivityExtensionsBinding.inflate(getLayoutInflater());
        setContentView(mExBinding.getRoot());

        mIntent = new Intent(this, ResultActivity.class);

        FilesData filesData = (FilesData) getIntent().getSerializableExtra("filenames");

        mExBinding.lenghtWithoutExBtn.setOnClickListener(v -> displayData(getExtensionWithoutLength(filesData)));
        mExBinding.removeSpaceBtn.setOnClickListener(v -> displayData(getFileNamesWithSpacesRemoved(filesData)));
        mExBinding.removeUnderscoresBtn.setOnClickListener(v -> displayData(removeAllUnderscores(filesData)));
        mExBinding.VowelWithExtenBtn.setOnClickListener(v -> displayData(getVowelWithExtensions(filesData)));
        mExBinding.LenghtBtn.setOnClickListener(v -> displayData(completeFilesLength(filesData)));
        mExBinding.replaceSpaceWithDashBtn.setOnClickListener(v -> displayData(replaceSpaceWithUnderscore(filesData)));
        mExBinding.extensionBtn.setOnClickListener(v -> displayData(showExtensions(filesData)));
        mExBinding.UpperCaseExtBtn.setOnClickListener(v -> displayData(fileNameWithExtensionUpper(filesData)));
        mExBinding.nameUpperBtn.setOnClickListener(v -> displayData(fileNameUpperWithExtensionLower(filesData)));
    }

    private String[] completeFilesLength(FilesData filesData) {
        mData[0] = String.valueOf(filesData.filename1.length());
        mData[1] = String.valueOf(filesData.filename2.length());
        mData[2] = String.valueOf(filesData.filename3.length());
        return mData;
    }

    private String[] fileNameUpperWithExtensionLower(FilesData filesData) {
        String[] propsFile1 = getFileProps(filesData.filename1);
        mData[0] = propsFile1[0].toUpperCase() + propsFile1[1];
        String[] propsFile2 = getFileProps(filesData.filename2);
        mData[1] = propsFile2[0].toUpperCase() + propsFile2[1];
        String[] propsFile3 = getFileProps(filesData.filename3);
        mData[2] = propsFile3[0].toUpperCase() + propsFile2[1];
        return mData;
    }

    private String[] getFileProps(String filename) {
        String[] props = new String[2];
        int dotIndex = filename.lastIndexOf(".");
        props[0] = filename.substring(0, dotIndex);
        props[1] = filename.substring(dotIndex);
        return props;
    }

    private String[] showExtensions(FilesData filesData) {
        mData[0] = getFileProps(filesData.filename1)[1].replace(".", "");
        mData[1] = getFileProps(filesData.filename2)[1].replace(".", "");
        mData[2] = getFileProps(filesData.filename3)[1].replace(".", "");
        return mData;
    }

    private String[] replaceSpaceWithUnderscore(FilesData filesData) {
        return replaceCharacter(filesData, " ", "_");
    }


    private String[] fileNameWithExtensionUpper(FilesData filesData) {
        String[] propsFile1 = getFileProps(filesData.filename1);
        mData[0] = propsFile1[0] + propsFile1[1].toUpperCase();
        String[] propsFile2 = getFileProps(filesData.filename2);
        mData[1] = propsFile2[0] + propsFile2[1].toUpperCase();
        String[] propsFile3 = getFileProps(filesData.filename3);
        mData[2] = propsFile3[0] + propsFile2[1].toUpperCase();
        return mData;
    }

    private String[] getVowelWithExtensions(FilesData filesData) {
        String[] propsFile1 = getFileProps(filesData.filename1);
        mData[0] = vowelsChar(propsFile1[0]) + "." + vowelsChar(propsFile1[1]);
        String[] propsFile2 = getFileProps(filesData.filename2);
        mData[1] = vowelsChar(propsFile2[0]) + "." + vowelsChar(propsFile2[1]);
        String[] propsFile3 = getFileProps(filesData.filename3);
        mData[2] = vowelsChar(propsFile3[0]) + "." + vowelsChar(propsFile3[1]);
        return mData;
    }

    String vowelsChar(String name){
        String commonVowels = "";
        String[] vowels = {
                "a","e","i","o","u"
        };
        for (int i = 0; i< name.length(); i++){
            String nameTOCheck = String.valueOf(name.charAt(i));
            for ( int j = 0; j < vowels.length; j++){
                if (nameTOCheck.toLowerCase().equals(vowels[j])){
                    commonVowels += nameTOCheck;
                }
            }
        }
        return commonVowels;
    }
}