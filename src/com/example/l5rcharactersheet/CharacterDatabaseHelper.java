package com.example.l5rcharactersheet;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CharacterDatabaseHelper extends SQLiteOpenHelper {

	public CharacterDatabaseHelper(Context context) {
		super(context, SV.DATABASE_NAME, null, SV.DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + SV.TABLE_L5R_CHARACTERS + "(" + 
				SV.KEY_ID.GetCreateTableString() + "," +
				SV.KEY_NAME.GetCreateTableString() + "," +
				SV.KEY_CLAN.GetCreateTableString() + "," +
				SV.KEY_FAMILY.GetCreateTableString() + "," +
				SV.KEY_SCHOOL.GetCreateTableString() + "," +
				SV.KEY_XP.GetCreateTableString() + "," +
				SV.KEY_STAMINA.GetCreateTableString() + "," +
				SV.KEY_WILLPOWER.GetCreateTableString() + "," +
				SV.KEY_STRENGTH.GetCreateTableString() + "," +
				SV.KEY_PERCEPTION.GetCreateTableString() + "," +
				SV.KEY_REFLEXES.GetCreateTableString() + "," +
				SV.KEY_AWARENESS.GetCreateTableString() + "," +
				SV.KEY_AGILITY.GetCreateTableString() + "," +
				SV.KEY_INTELLIGENCE.GetCreateTableString() + "," +
				SV.KEY_VOID.GetCreateTableString() + "," +
				SV.KEY_HONOR.GetCreateTableString() + "," +
				SV.KEY_GLORY.GetCreateTableString() + "," +
				SV.KEY_STATUS.GetCreateTableString() + "," +
				SV.KEY_TAINT.GetCreateTableString() +
				")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + SV.TABLE_L5R_CHARACTERS);

		// Create tables again
		onCreate(db);
	}
	
	// Testing method to drop and create the table
	public void Testing_ReCreateTable()
	{	
		SQLiteDatabase db = this.getWritableDatabase();
		
		//TODO test code to clear the table each run
		db.execSQL("DROP TABLE IF EXISTS " + SV.TABLE_L5R_CHARACTERS);
		
		// Create tables again
		onCreate(db);
		
		//TODO TEST CODE TO ADD SAMPLES
		ContentValues values = new ContentValues();
		values.put(SV.KEY_NAME.keyName, "BOB");
		values.put(SV.KEY_STAMINA.keyName, 1);
		db.insert(SV.TABLE_L5R_CHARACTERS, null, values);
		values = new ContentValues();
		values.put(SV.KEY_NAME.keyName, "Billy");
		values.put(SV.KEY_STAMINA.keyName, 2);
		values.put(SV.KEY_WILLPOWER.keyName, 3);
		values.put(SV.KEY_STRENGTH.keyName, 4);
		values.put(SV.KEY_PERCEPTION.keyName, 5);
		values.put(SV.KEY_REFLEXES.keyName, 6);
		values.put(SV.KEY_AWARENESS.keyName, 7);
		values.put(SV.KEY_AGILITY.keyName, 8);
		values.put(SV.KEY_INTELLIGENCE.keyName, 9);
		values.put(SV.KEY_VOID.keyName, 3);
		values.put(SV.KEY_HONOR.keyName, 1.1);
		values.put(SV.KEY_GLORY.keyName, 2.2);
		values.put(SV.KEY_STATUS.keyName, 3.3);
		values.put(SV.KEY_TAINT.keyName, 4.4);
		db.insert(SV.TABLE_L5R_CHARACTERS, null, values);
	}
	
	// Delete character at id
	public void DeleteCharacter(long _id) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.delete(SV.TABLE_L5R_CHARACTERS, SV.KEY_ID.keyName + "=?", new String[] {String.valueOf(_id)});
	}
	
	// Getting All Character Names
	public List<String> getL5RCharacterNameList() {
		List<String> characterNameList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT " + SV.KEY_NAME.keyName + " FROM " + SV.TABLE_L5R_CHARACTERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				// Adding name to list
				characterNameList.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		// return contact list
		return characterNameList;
	}
	
	// Getting All Character Names
	public Cursor getL5RCharacterNameCursor() {
		// Select All Query
		String selectQuery = "SELECT " + SV.KEY_ID.keyName + ", " + SV.KEY_NAME.keyName + " FROM " + SV.TABLE_L5R_CHARACTERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// return contact list
		return cursor;
	}

	// Getting character Count
	public int getCharacterCount() {
		String countQuery = "SELECT " + SV.KEY_ID.keyName + " FROM " + SV.TABLE_L5R_CHARACTERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}

