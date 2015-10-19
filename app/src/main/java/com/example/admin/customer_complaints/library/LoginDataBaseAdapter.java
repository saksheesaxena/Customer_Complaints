package com.example.admin.customer_complaints.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.customer_complaints.DatabaseHandler;

import java.math.BigInteger;
import java.sql.SQLException;

/**
 * Created by Sakshee on 15-Oct-15.
 */
public class LoginDataBaseAdapter {
    // Database Name
    private static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    // table name
    private static final String TABLE_SIGNUP = "signup";

    // Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
   // public static final String KEY_MOB_NUMBER = "phone_number";
   // public static final String KEY_ADDRESS = "address";
   // public static final String KEY_ULB = "ulb";
   // public static final String KEY_GENDER = "gender";
   // public static final String KEY_EMAIL = "email";
   public static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,MOBILE text, GENDER text , ADDRESS text, EMAIL text , ULB text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;

    // Database open/upgrade helper
    private DatabaseHandler dbHandler;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHandler = new DatabaseHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHandler.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String mobile, String gender, String address, String email, String ulb)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("MOBILE",mobile);
        newValues.put("GENDER",gender);
        newValues.put("ADDRESS",address);
        newValues.put("EMAIL",email);
        newValues.put ("ULB", ulb);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
       String phone= cursor.getString(cursor.getColumnIndex("MOBILE"));
        cursor.close();
        return phone;
    }
    public void  updateEntry(String userName,String mobile)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("MOBILE",mobile);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }



    }
