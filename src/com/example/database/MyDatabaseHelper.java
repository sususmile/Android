package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	public static final String CREATE_BOOK = "create table User("+"id integer primary key autoincrement,"
											+ "username text,"
											+ "password text,"
											+ "imagePath text)";
	public static final String CREATE_order = "create table OrderStor("+"id integer primary key autoincrement,"
			+ "time text,"
			+ "name text,"
			+ "money text,"
			+ "username text)";
	private Context mContext;

	public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_BOOK);
		db.execSQL(CREATE_order);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exits User");
		db.execSQL("drop table if exits OrderStor");
		onCreate(db);
	}
	 public boolean deleteDatabase(Context context) {
	        return context.deleteDatabase("UserStor.db");
	    }
}
