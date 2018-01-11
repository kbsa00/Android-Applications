package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Util;

/**
 * Created by Eier on 1/3/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context,Util.DATABASE_NAME, null, Util.DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - Structured Query Language

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.DATABASE_TABLE +
                Util.KEY_ID + " INTEGER PRIMARY KEY, " + Util.KEY_NAME + " TEXT," +
                Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropping is deleting the table!
        db.execSQL("DROP TABLE IF EXISTS " + Util.DATABASE_TABLE);


        //CREATE TABLE AGAIN
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Util.KEY_NAME, contact.getName());
        value.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());


        //Inserting to row
        db.insert(Util.DATABASE_TABLE, null, value);

    }

    public Contact getContact(int id){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(Util.DATABASE_TABLE, new String[] {Util.KEY_ID, Util.KEY_NAME
            , Util.KEY_PHONE_NUMBER}, Util.KEY_ID + "=?",
                    new String[] {String.valueOf(id)}, null, null, null, null);

            if(cursor != null)
                cursor.moveToFirst();


            Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));

            return contact;
    }

    public List<Contact> getAllContacts(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> contactList = new ArrayList<>();
        String selectAll = "SELECT * FROM " + Util.DATABASE_TABLE;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact object to our contact LIST
                contactList.add(contact);

            }while (cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        return db.update(Util.DATABASE_TABLE, values, Util.KEY_ID + "?=", new String[]
                {String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.DATABASE_TABLE, Util.KEY_ID, new String[] {String.valueOf(contact.getId())});


    }

    public int getContactCount(){
        String countQuery = "SELECT * FROM " + Util.DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
        //cursor.close();
    }
}
