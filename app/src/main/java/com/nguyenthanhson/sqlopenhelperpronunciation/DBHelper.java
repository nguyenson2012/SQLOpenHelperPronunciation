package com.nguyenthanhson.sqlopenhelperpronunciation;

/**
 * Created by SON on 12/10/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pronunciation.db";
    public static final String WORD_TABLE_NAME = "wordinfotb";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_PHONETIC = "phonetic";
    public static final String COLUMN_NUMBER_PHONETIC = "numberphonetic";
    public static final String COLUMN_GROUP = "nhom";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table wordinfotb " +
                        "(nhom text,word text primary key, phonetic text,numberphonetic text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS wordinfotb");
        onCreate(db);
    }

    public boolean insertWord  (String word, String phonetic, String numberphonetic, String group)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GROUP,group);
        contentValues.put(COLUMN_WORD,word);
        contentValues.put(COLUMN_PHONETIC,phonetic);
        contentValues.put(COLUMN_NUMBER_PHONETIC, numberphonetic);
        long kt=db.insert("wordinfotb", null, contentValues);
        return true;
    }

    public Cursor getData(String word){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+WORD_TABLE_NAME+" where word=", new String[]{word} );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, WORD_TABLE_NAME);
        return numRows;
    }

    public boolean updateWord (String word, String phonetic, String numberphonetic, int group)
    {
        boolean kt=false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_WORD,word);
        contentValues.put(COLUMN_PHONETIC,phonetic);
        contentValues.put(COLUMN_NUMBER_PHONETIC,numberphonetic);
        contentValues.put(COLUMN_GROUP, group);
        if(db.update(WORD_TABLE_NAME, contentValues, "word = ? ", new String[] { word } )>0){
            kt=true;
        }
        return kt;
    }

    public Integer deteleWord (String word)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(WORD_TABLE_NAME,
                "word = ? ",
                new String[] { word });
    }
    public ArrayList<String > getWordByGroup(String group){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+WORD_TABLE_NAME+" where nhom=?", new String[]{group} );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_WORD)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllWord()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+WORD_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_WORD)));
            res.moveToNext();
        }
        return array_list;
    }
}
