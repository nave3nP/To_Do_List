package com.npbandara.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Todolist.db";
    public static final String TABLE_NAME="todolist";
    public static final String COL_1="ID";

    public static final String COL_2="STUDENT_ID";
    public static final String COL_3="DESCRIPTION";
    public static final String COL_4="DATE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1 );
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " +TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "STUDENT_ID TEXT, DESCRIPTION TEXT, DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String sId, String task,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,sId);
        contentValues.put(COL_3,task);
        contentValues.put(COL_4,date);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + TABLE_NAME, null);
        return result;
    }
    public boolean updateData(String id, String stId, String task, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,stId);
        contentValues.put(COL_3,task);
        contentValues.put(COL_4,date);
        db.update(TABLE_NAME, contentValues, "id = ?",new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[] {id});
    }

}
