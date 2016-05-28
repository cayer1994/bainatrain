package com.example.sample2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private static final int VERSION=1;
	
	private static final String DB_NAME="my.db";
	
	

	public DBHelper(Context context)
	{
		super(context,DB_NAME,null,VERSION);
	}
	


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table train(Tid integer primary key,Tname char(20),Tstartstation char(20),Tterminus char(20),Ttype char(20))");
		db.execSQL("create table station(Sid integer primary key autoincrement,Sname char(20),Spy char(10))");
		db.execSQL("create table relation(Rid integer primary key autoincrement,Tid integer,Sid integer,Rarrivetime char(20),Rstarttime char(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + "train");
		db.execSQL("DROP TABLE IF EXISTS " + "train");
		db.execSQL("DROP TABLE IF EXISTS " + "train");
		onCreate(db);
		
	}
	
	
	
	
	

}
