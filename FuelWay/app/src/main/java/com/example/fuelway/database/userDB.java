/**
 * User Database Management
 */

package com.example.fuelway.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fuelway.model.user;

import java.util.ArrayList;

public class userDB extends SQLiteOpenHelper {
//Create variable
    private static  String dbName= "userDB";
    private static  String tableName= "user";
    private static  String idColumn= "id";
    private static  String userNameColumn= "name";
    private static  String mobileColumn= "userMobile";
    private static  String nicColumn= "nic";
    private static  String passwordColumn= "password";

    public userDB(Context context){
        super(context, dbName, null,1);

    }
//Create table query
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " integer primary key autoincrement, " +
                userNameColumn + " text," +
                mobileColumn + " text," +
                nicColumn + " text," +
                passwordColumn + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

//Create Table
    public boolean create(user userdb){
        boolean result = true;
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(userNameColumn, userdb.getName());
            contentValues.put(mobileColumn, userdb.getUserMobile());
            contentValues.put(nicColumn, userdb.getNic());
            contentValues.put(passwordColumn, userdb.getPassword());
            result = sqLiteDatabase.insert(tableName, null, contentValues) > 0;

        }catch (Exception e){
            result = false;
        }
        return result;
    }

//Login Method
    public user login(String username, String password){
        user account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where name = ? and password = ?", new String[]{username, password});
            if (cursor.moveToFirst()){
                account = new user();
                account.setId(cursor.getInt(0));
                account.setName(cursor.getString(1));
                account.setUserMobile(cursor.getString(2));
                account.setNic(cursor.getString(3));
                account.setPassword(cursor.getString(4));

            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    //check username when registering new user
    public user checkUsername(String username){
        user account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where name = ?", new String[]{username});
            if (cursor.moveToFirst()){
                account = new user();
                account.setId(cursor.getInt(0));
                account.setName(cursor.getString(1));
                account.setUserMobile(cursor.getString(2));
                account.setNic(cursor.getString(3));
                account.setPassword(cursor.getString(4));

            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public user type(String username){
        user account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where name = ?", new String[]{username});
            if (cursor.moveToFirst()){
                account = new user();
                account.setId(cursor.getInt(0));
                account.setName(cursor.getString(1));
                account.setUserMobile(cursor.getString(2));
                account.setNic(cursor.getString(3));
                account.setPassword(cursor.getString(4));

            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

}
