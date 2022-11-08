package com.example.panthera.database;

import android.provider.BaseColumns;

//Create a final class to define all tables for the database
public final class DBMaster {

    private DBMaster() {
    }

    //ANIMAL CLASS TO STORE ANIMAL TABLE DATA
    public static class Animals implements BaseColumns {
        public static final String TABLE_NAME1 = "animal";
        //COLUMN NAMES FOR ANIMAL
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_NAME_SCIENTIFIC = "sciname";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
    }
}
