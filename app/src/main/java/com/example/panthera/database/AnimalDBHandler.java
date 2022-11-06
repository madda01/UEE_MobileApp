package com.example.panthera.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.panthera.models.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalDBHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1; //version

    private static final String DB_NAME = "panthera"; //database name

    public AnimalDBHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating animal table
        String SQL_CREATE_ANIMAL_ENTRIES =
                "CREATE TABLE " + DBMaster.Animals.TABLE_NAME1 + "(" +
                        DBMaster.Animals._ID + " INTEGER PRIMARY KEY," +
                        DBMaster.Animals.COLUMN_NAME_TITLE + " TEXT," +
                        DBMaster.Animals.COLUMN_NAME_SCIENTIFIC + " REAL," +
                        DBMaster.Animals.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        DBMaster.Animals.COLUMN_NAME_IMAGE + " BLOB" +
                        ");";
        //this will execute the contents of the SQL_CREATE_ANIMAL_ENTRIES
        sqLiteDatabase.execSQL(SQL_CREATE_ANIMAL_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_QUERY1 = "DROP TABLE IF EXISTS " + DBMaster.Animals.TABLE_NAME1;

        // Drop older table if existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY1);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    //add a new animal
    public boolean insertAnimal(Animal animal) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBMaster.Animals.COLUMN_NAME_TITLE, animal.getName());
        values.put(DBMaster.Animals.COLUMN_NAME_SCIENTIFIC, animal.getScientificName());
        values.put(DBMaster.Animals.COLUMN_NAME_DESCRIPTION, animal.getDescription());
        values.put(DBMaster.Animals.COLUMN_NAME_IMAGE, animal.getAnimalImage());
        long newRowId = db.insert(DBMaster.Animals.TABLE_NAME1, null, values);
        if (newRowId >= 1)
            return true;
        else
            return false;
    }

    //delete animal
    public void deleteAnimal(int animalid) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(animalid)};
        // delete animal record by id
        db.delete(DBMaster.Animals.TABLE_NAME1, DBMaster.Animals._ID + " LIKE ?", selectionArgs);
        db.close();
    }

    //update animal
    public int updateSingleAnimal(Animal animal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBMaster.Animals.COLUMN_NAME_TITLE, animal.getName());
        values.put(DBMaster.Animals.COLUMN_NAME_SCIENTIFIC, animal.getScientificName());
        values.put(DBMaster.Animals.COLUMN_NAME_DESCRIPTION, animal.getDescription());

        int status = db.update(DBMaster.Animals.TABLE_NAME1, values, DBMaster.Animals._ID + " = ?",
                new String[]{String.valueOf(animal.getId())});

        db.close();
        return status;
    }

    // Count animal table records
    public int countAnimals() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + DBMaster.Animals.TABLE_NAME1;

        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    // Get all animals into a list
    public List<Animal> getAllAnimal() {

        List<Animal> animals = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + DBMaster.Animals.TABLE_NAME1;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Create new animal object
                Animal animal = new Animal();

                animal.setId(cursor.getInt(0));
                animal.setName(cursor.getString(1));
                animal.setScientificName(cursor.getString(2));
                animal.setDescription(cursor.getString(3));
                animal.setAnimalImage(cursor.getBlob(4));

                animals.add(animal);
            } while (cursor.moveToNext());
        }
        return animals;
    }


    // Get a single animal
    public Animal getSingleAnimal(int animalid) {
        Animal animal;
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(DBMaster.Animals.TABLE_NAME1, new String[]{DBMaster.Animals._ID, DBMaster.Animals.COLUMN_NAME_TITLE, DBMaster.Animals.COLUMN_NAME_SCIENTIFIC, DBMaster.Animals.COLUMN_NAME_DESCRIPTION}, DBMaster.Animals._ID + " =?", new String[]{String.valueOf(animalid)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            animal = new Animal(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            return animal;

        }
        return null;
    }
}
