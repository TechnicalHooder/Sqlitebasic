package com.approcket.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="student.db";
    private static final String TABLE_NAME="student_table";
    private static final String COL_1="ID";
    private static final String COL_2="NAME";
    private static final String COL_3="SIRNAME";
    private static final String COL_4="MARKS";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     sqLiteDatabase.execSQL(" create table "+TABLE_NAME+" ( ID INTEGER PRIMARY KEY ,NAME TEXT,SIRNAME TEXT,MARKS INTEGER ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean inserted(String id,String name,String sirname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,sirname);
        contentValues.put(COL_4,marks);
        long inserted= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(inserted==-1)
        return  false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
    return cursor;
    }
    public  boolean updateData(String id,String name,String sirname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,sirname);
        contentValues.put(COL_4,marks);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        return true;
    }
    public boolean deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"ID = ?",new String[]{id});
        return  true;
    }
}
