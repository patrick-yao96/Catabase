package com.example.catabase;

import java.util.ArrayList;

public class DataHolder {

    final ArrayList<String> cat_breeds = new ArrayList<>();


    private DataHolder() {}

    static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}
