package com.example.bundleapplication;

import java.io.Serializable;

public class FilesData implements Serializable {
    final String filename1, filename2, filename3;

    public FilesData(String filename1, String filename2, String filename3) {
        this.filename1 = filename1;
        this.filename2 = filename2;
        this.filename3 = filename3;
    }
}
