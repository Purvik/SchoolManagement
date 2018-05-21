package com.ourwork.schoolmanagement.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ourwork.schoolmanagement.singleton.AccountUser;

/**
 * Created by Purvik Rana on 18-05-2018.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "account";
    private static final String TABLE_ACCOUNT_DETAILS = "accountDetails";

    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD =  "password";
    private static final String KEY_ACC_TYPE = "acc_type";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqliteDb) {

        String CREATE_ACCOUNT_DETAILS_TABLE = "CREATE TABLE "+ TABLE_ACCOUNT_DETAILS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_USERNAME + " TEXT,"
                    + KEY_PASSWORD + " TEXT,"
                    + KEY_ACC_TYPE + " TEXT " + ")";

        sqliteDb.execSQL(CREATE_ACCOUNT_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDb, int oldVersion, int newVersion) {

        // Drop older table if existed
        sqliteDb.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT_DETAILS);

        // Create tables again
        onCreate(sqliteDb);

    }

    // Adding new Student Information
    void addNewAccountUser(AccountUser accountUser) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, accountUser.getUsername());
        values.put(KEY_PASSWORD, accountUser.getPassword());
        values.put(KEY_ACC_TYPE, accountUser.getUsertype());


        // Inserting Row
        db.insert(TABLE_ACCOUNT_DETAILS, null, values);
        db.close(); // Closing database connection
    }

    /*public boolean updateAccountUserInfo(int updId, int updEnrolNo, String updName, String updPhoneNo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_ENROLL_NO, updEnrolNo);
        args.put(KEY_NAME, updName);
        args.put(KEY_PHONE_NO, updPhoneNo);

        return db.update(TABLE_STUDENT_DETAIL, args, KEY_ID + "=" + updId, null) > 0;
    }*/

    AccountUser validateAccount(AccountUser accountUser){

        int accFound = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String getUserQuery = "SELECT * FROM "+ TABLE_ACCOUNT_DETAILS
                                + " WHERE " + KEY_USERNAME + "= " + accountUser.getUsername()
                                + " AND " + KEY_PASSWORD + "= " + accountUser.getPassword();

        Cursor cursor = db.rawQuery(getUserQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            /*do {
                if (accountUser.getPassword() .equalsIgnoreCase( cursor.getString(2))) {
                    accFound = 1;
                    break;
                    //return true;
                }
            } while (cursor.moveToNext());*/

            return (new AccountUser(cursor.getString(1), cursor.getString(2), cursor.getString(3)));

        }else{

            return (new AccountUser());
        }

        /*if (accFound == 1) {
            return true;
        }else {
            return false;
        }*/
    }
}
