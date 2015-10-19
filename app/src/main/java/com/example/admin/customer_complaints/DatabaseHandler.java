package com.example.admin.customer_complaints;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.customer_complaints.library.CustomerDetails;
import com.example.admin.customer_complaints.library.LoginDataBaseAdapter;

import java.util.List;

/**
 * Created by Sakshee on 12-Oct-15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

        public DatabaseHandler(Context context, String name,SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }
        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase _db)
        {

            _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);

        }
        // Called when there is a database version mismatch meaning that the version
        // of the database on disk needs to be upgraded to the current version.
        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
        {
            // Log the version upgrade.
            Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to " + _newVersion + ", which will destroy all old data");

            // Upgrade the existing database to conform to the new version. Multiple
            // previous versions can be handled by comparing _oldVersion and _newVersion
            // values.
            // The simplest case is to drop the old table and create a new one.
            _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
            // Create a new one.
            onCreate(_db);
        }

  /*  // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "JanSeva";

    // Contacts table name
    private static final String TABLE_SIGNUP = "signup";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOB_NUMBER = "phone_number";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_ULB = "ulb";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_EMAIL = "email";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SIGNUP_TABLE = "CREATE TABLE " + TABLE_SIGNUP + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_MOB_NUMBER+ " TEXT" + KEY_ADDRESS + "TEXT" + KEY_ULB + "TEXT"+KEY_GENDER + "TEXT"+KEY_EMAIL + "TEXT" + ")";
        db.execSQL(CREATE_SIGNUP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGNUP);

        // Create tables again
        onCreate(db);

    }
    // Adding new user

    // Adding new user
    public void addContact(CustomerDetails user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,user.getID());
        values.put(KEY_NAME, user.getNAME()); //User Name
        values.put(KEY_MOB_NUMBER, user.getMOBILENUMBER()); // USer Phone Number
        values.put(KEY_ADDRESS,user.getADDRESS());
        values.put(KEY_ULB,user.getULB());
        values.put(KEY_GENDER,user.getGENDER());
        values.put(KEY_EMAIL,user.getEMAIL());

        // Inserting Row
        db.insert(TABLE_SIGNUP, null, values);
        db.close(); // Closing database connection


    } */


}
