package com.example.georunsample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "POSTS";
	public static final String FIELD_ID = "id";
	public static final String FIELD_TITLE = "title";
	public static final String FIELD_BODY = "body";

	public MyDatabaseHelper(Context context) {
		super(context, "mydatabase.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + FIELD_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_TITLE + " TEXT NOT NULL, "
				+ FIELD_BODY + " TEXT NOT NULL );");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public ArrayList<Post> retrieveData() {
		ArrayList<Post> list = new ArrayList<Post>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, new String[] { FIELD_ID, FIELD_TITLE, FIELD_BODY },
				null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Post post = new Post(new Integer(cursor.getString(0)), cursor.getString(1),
						cursor.getString(2));
				list.add(post);
			} while (cursor.moveToNext());
		}
		db.close();
		return list;
	}

	public void addBasicData() {
		SQLiteDatabase db = getWritableDatabase();
		String insert = "REPLACE INTO " + TABLE_NAME + " ( " + FIELD_ID + ", " + FIELD_TITLE + ", "
				+ FIELD_BODY + ") VALUES (?, ?, ?)";
		SQLiteStatement insertStatement = db.compileStatement(insert);
		insertStatement.bindString(1, "1");
		insertStatement.bindString(2, "tytul 1");
		insertStatement.bindString(3, "tekst sdfsdf");
		insertStatement.executeInsert();

		insertStatement = db.compileStatement(insert);
		insertStatement.bindString(1, "2");
		insertStatement.bindString(2, "TYTULOS");
		insertStatement.bindString(3, "cialo tekstu");
		insertStatement.executeInsert();
		db.close();
	}

	public void insertJSON(JSONArray postArray) {
		SQLiteDatabase db = getWritableDatabase();
		String replace = "REPLACE INTO " + TABLE_NAME + " ( " + FIELD_ID + ", " + FIELD_TITLE
				+ ", " + FIELD_BODY + ") VALUES (?, ?, ?)";
		SQLiteStatement replaceStatement;
		try {
			for (int i = 0; i < postArray.length(); i++) {
				JSONObject jsonPost = postArray.getJSONObject(i);
				replaceStatement = db.compileStatement(replace);
				replaceStatement.bindString(1, jsonPost.getString(FIELD_ID));
				replaceStatement.bindString(2, jsonPost.getString(FIELD_TITLE));
				replaceStatement.bindString(3, jsonPost.getString(FIELD_BODY));
				replaceStatement.executeInsert();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		db.close();
	}

	public void cropTable() {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_NAME, FIELD_ID + " > 2", null);
		db.close();
	}

}
