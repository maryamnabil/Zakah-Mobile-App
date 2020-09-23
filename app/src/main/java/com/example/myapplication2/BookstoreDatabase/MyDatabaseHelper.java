package com.example.myapplication2.BookstoreDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //Table Name
    public static final String TABLE_NAME="Books";
    // Table Cols
    public static final String _ID = "_id";
    public static final String NAME = "Name";
    public static final String PRICE = "Price";
    public static final String QUANTITY = "Quantity";
    //DB INFO
    public static final String DB_NAME = "books.DB";
    public static final int DB_VERSION = 1;
    // Creating Database Instance
    private SQLiteDatabase database;
    // Queris
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT,"
            + PRICE + " TEXT,"
            + QUANTITY + " TEXT"+ ")";

    public static final String UPGRADE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UPGRADE);
        onCreate(db);
    }

    // Constructor
    public MyDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    public void open() throws SQLException {
        database = getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public void insert(String name,String price , String quantity){
        ContentValues insertValues = new ContentValues();
        insertValues.put(NAME, name);
        insertValues.put(PRICE, price);
        insertValues.put(QUANTITY, quantity);
        database.insert(TABLE_NAME,null,insertValues);
    }

    public Cursor getProducts(){
        String[] projection = {_ID ,NAME , PRICE,QUANTITY};
        // The Cursor class has an API that allows an app to read (in a type-safe manner) the columns that were returned from the query
        Cursor cursor = database.query(TABLE_NAME,projection,null,null,null,null,null);
        return cursor;
    }

    public int update(long _id, String name, String price , String quantity){
        ContentValues updateValues = new ContentValues();
        updateValues.put(NAME,name);
        updateValues.put(PRICE,price);
        updateValues.put(QUANTITY,quantity);
        int count = database.update(TABLE_NAME,updateValues,this._ID + "=" + _id , null);
        return count;
    }

    public void delete(long _id){
        database.delete(TABLE_NAME,_ID +"="+  _id,null);
    }


}
