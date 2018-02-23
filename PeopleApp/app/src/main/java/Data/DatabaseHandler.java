package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Person;

/**
 * Created by Eier on 2/8/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private ArrayList<Person> listOfPerson = new ArrayList<>();

    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" +
                Constants.KEY_ID + " INTEGER PRIMARY KEY, " + Constants.PERSON_NAME +
                " TEXT,  " + Constants.PERSON_ADRESS + " TEXT, " + Constants.PERSON_AGE + " INT);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }


    public void addPerson(Person person){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.PERSON_NAME, person.getPersonName());
        values.put(Constants.PERSON_AGE, person.getPersonAge());
        values.put(Constants.PERSON_ADRESS, person.getPersAdress());

        db.insert(Constants.TABLE_NAME, null, values);
        db.close();
    }


    public ArrayList<Person> getListOfPerson(){
        listOfPerson.clear();
        SQLiteDatabase db = this.getReadableDatabase();

        String dbquery = "SELECT * FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(dbquery, null);

        if (cursor.moveToNext()){

            do {
                Person person = new Person();
                person.setPersonName(cursor.getString(cursor.getColumnIndex(Constants.PERSON_NAME)));
                person.setPersonAge(cursor.getInt(cursor.getColumnIndex(Constants.PERSON_AGE)));
                person.setPersId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
                person.setPersAdress(cursor.getString(cursor.getColumnIndex(Constants.PERSON_ADRESS)));
                listOfPerson.add(person);

            }while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return listOfPerson;
    }
}
