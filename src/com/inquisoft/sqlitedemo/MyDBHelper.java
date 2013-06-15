package com.inquisoft.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	public final static String CREATE_TABLE = "CREATE TABLE Comments( _id INTEGER PRIMARY KEY AUTOINCREMENT, date DATE, who TEXT, comment TEXT )";
	
	public static final String DATABASE_NAME = "SqliteDemoDB";
	public static final int version = 2;
	
	
	
	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Comments");
		db.execSQL(CREATE_TABLE);
	}

}
