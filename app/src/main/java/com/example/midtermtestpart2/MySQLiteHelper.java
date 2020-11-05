package com.example.midtermtestpart2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MySQLiteHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pet.db";
    private static final String TABLE_NAME = "RegisteredPet";
    private static final String COL_1 = "tag";
    private static final String COL_2 = "species"; //a dogs or a cat
    private static final String COL_3 = "breed"; // the breed

    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " Integer PRIMARY KEY AUTOINCREMENT," +
                COL_2 + " Text NOT NULL," +
                COL_3 + " Text NOT NULL)" + ";";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table IF EXISTS " + TABLE_NAME + ";");
    }


    /*Insert a new record in users table*/
    public boolean addRecord(String speciesIn,String breedIn){
        ContentValues values= new ContentValues();
        values.put(COL_2,speciesIn);
        values.put(COL_3,breedIn);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_NAME,null,values);
        db.close();

        if (result ==0) return false;
        else
            return true;
    }


    public ArrayList<String> retrieveRecords(){

        ArrayList<String> dogBreed = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "select " + COL_3 +" from " + TABLE_NAME
                + " where " + COL_2  + " = 'dog' ;";

        Log.d("query",query);
        Cursor cursor = db.rawQuery(query,null);

        cursor.moveToFirst();

        while(cursor.moveToNext()){
            String currentValue = cursor.getString(0);
            dogBreed.add(currentValue);
            cursor.moveToNext();

        }
        cursor.close();
        return dogBreed;
    }

}
