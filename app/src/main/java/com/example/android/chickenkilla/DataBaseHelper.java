package com.example.android.chickenkilla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_ADDRESS = "CUSTOMER_ADDRESS";
    public static final String COLUMN_CUSTOMER_VIOLATION = "CUSTOMER_VIOLATION";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_SURVEYOR = "SURVEYOR";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    // called the first time database is accessed. code to create new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatment = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_ADDRESS + " TEXT, " + COLUMN_CUSTOMER_VIOLATION + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_SURVEYOR + " INTEGER)";

        db.execSQL(createTableStatment);
    }

    //this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_ADDRESS, customerModel.getAddress());
        cv.put(COLUMN_CUSTOMER_VIOLATION, customerModel.getViolation());
        cv.put(COLUMN_DATE, customerModel.getDate());
        cv.put(COLUMN_SURVEYOR, customerModel.getSurveyor());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<CustomerModel> getSearch(String searchTerms) {
        System.out.println("here 1");
        List<CustomerModel> returnList = new ArrayList<>();


        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " where " + " like '%" + searchTerms + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        System.out.println(queryString);
        System.out.println("here 2");
        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new customer objects. Put them into the return list
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerAddress = cursor.getString(2);
                String customerViolation = cursor.getString(3);
                String date = cursor.getString(4);
                int surveyor = cursor.getInt(5);

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAddress, customerViolation, date, surveyor);
                System.out.println("here 3");
                returnList.add(newCustomer);

            } while (cursor.moveToNext());


        } else {
            // failure. do not add anything to the list
        }
        System.out.println("here 4");
        return returnList;
    }

    // close bothe the cursor and the db when done

}


