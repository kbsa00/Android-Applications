package com.example.eier.grocerylist.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.eier.grocerylist.Model.Grocery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.eier.grocerylist.Util.Constants;

/**
 * Created by Eier on 1/12/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private Context ctx;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROCERY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" +
                Constants.KEY_ID +  " INTEGER PRIMARY KEY," + Constants.KEY_GROCERY_ITEM
                + " TEXT," + Constants.KEY_QTY_NUMBER + " TEXT," + Constants.KEY_DATA_NAME +
                " LONG);";

        db.execSQL(CREATE_GROCERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);

    }

    /**
     * CRUD OPERATIONS:
     */

    public void addGrocery(Grocery grocery){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATA_NAME, java.lang.System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME, null, values);

        Log.d("Saved", "Saved to DB");
    }

    public Grocery getGrocery(int id ){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.KEY_ID, Constants.KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER, Constants.KEY_DATA_NAME},
                Constants.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null
        , null);

        if (cursor != null) cursor.moveToFirst();

            Grocery grocery =  new Grocery();
            grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
            grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

            //Convert timestamp to something readable
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATA_NAME))).getTime());
            grocery.setDateItemAdded(formatedDate);


        return grocery;
    }


    public List<Grocery> getAllGroceries(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.KEY_ID, Constants.KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER,
                Constants.KEY_DATA_NAME}, null, null, null, null,
                Constants.KEY_DATA_NAME + " DESC");

        List<Grocery> groceryList = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
               Grocery grocery =  new Grocery();
               grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
               grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
               grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));


               //Convert timestamp to something readable
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATA_NAME))).getTime());
                grocery.setDateItemAdded(formatedDate);

                groceryList.add(grocery);

            }while (cursor.moveToNext());
        }

        return groceryList;
    }

    public int updateGrocery(Grocery grocery){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATA_NAME, grocery.getDateItemAdded());

        //updated row

        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[]{
                String.valueOf(grocery.getId())
        });
    }

    public void deleteGrocery(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID +"=?",
                new String []{String.valueOf(id)});

        db.close();

    }

    public int getGorceriesCount(){
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }


}
