package com.example.l5rcharactersheet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class L5RCharacter extends SQLiteOpenHelper {

	//private variables
	long _id;
	SQLiteDatabase _db;
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	// new character constructor
	public L5RCharacter(Context context, String _name,
			String _clan, String _family, String _school) {
		super(context, SV.DATABASE_NAME, null, SV.DATABASE_VERSION);
		
		_db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(SV.KEY_NAME.keyName, _name);
		values.put(SV.KEY_CLAN.keyName, _clan);
		values.put(SV.KEY_FAMILY.keyName, _family);
		values.put(SV.KEY_SCHOOL.keyName, _school);
		values.put(SV.KEY_XP.keyName, 40);
		values.put(SV.KEY_STAMINA.keyName, 2);
		values.put(SV.KEY_WILLPOWER.keyName, 2);
		values.put(SV.KEY_STRENGTH.keyName, 2);
		values.put(SV.KEY_PERCEPTION.keyName, 2);
		values.put(SV.KEY_REFLEXES.keyName, 2);
		values.put(SV.KEY_AWARENESS.keyName, 2);
		values.put(SV.KEY_AGILITY.keyName, 2);
		values.put(SV.KEY_INTELLIGENCE.keyName, 2);
		values.put(SV.KEY_VOID.keyName, 2);
		values.put(SV.KEY_HONOR.keyName, 0.0);
		values.put(SV.KEY_GLORY.keyName, 1.0);
		values.put(SV.KEY_STATUS.keyName, 1.0);
		values.put(SV.KEY_TAINT.keyName, 0.0);

		// Inserting Row
		_id = _db.insert(SV.TABLE_L5R_CHARACTERS, null, values);
	}
	
	// constructor for retrieving a character
	public L5RCharacter(Context context, long id){
		super(context, SV.DATABASE_NAME, null, SV.DATABASE_VERSION);
		_id = id;
		_db = this.getWritableDatabase();
	}
	
	// getting ID
	public long getID(){
		return _id;
	}
	
	// setting id
	public void setID(int id){
		_id = id;
	}
	
	// getting name
	public String getName(){
		Cursor cursor = _db.query(SV.TABLE_L5R_CHARACTERS, new String[] { //KEY_ID,
				SV.KEY_NAME.keyName }, SV.KEY_ID.keyName + "=?",
				new String[] { String.valueOf(_id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		return cursor.getString(0);
	}
	
	// setting name
	public void setName(String name){
		ContentValues values = new ContentValues();
		values.put(SV.KEY_NAME.keyName, name);

		// updating row
		//int success = 
				_db.update(SV.TABLE_L5R_CHARACTERS, values, SV.KEY_ID.keyName + " = ?", new String[] { String.valueOf(_id) });
	}
	
	//************************
	// ATTRIBUTE CALCULATIONS
	public String GetCalculatedAttribute(SV.SQLColumn attributeKey) {
		
		//First check if the key is a generated value
		String generatedAttribute = GetGeneratedAttribute(attributeKey);
		if(generatedAttribute != null)
			return generatedAttribute;
		
		Cursor cursor = _db.query(SV.TABLE_L5R_CHARACTERS, new String[] { attributeKey.keyName }, 
				SV.KEY_ID.keyName + "=?", new String[] { String.valueOf(_id) }, null, null, null, null);
		if (cursor != null)
		{
			cursor.moveToFirst();
			
			if(attributeKey.dataType == Cursor.FIELD_TYPE_INTEGER)
			{
				Integer calculatedValue = Integer.valueOf(cursor.getInt(0));
				return calculatedValue.toString();
			}
			else if(attributeKey.dataType == Cursor.FIELD_TYPE_FLOAT)
			{
				Float calculatedValue = Float.valueOf(cursor.getFloat(0));
				return calculatedValue.toString();
			}
			else if(attributeKey.dataType == Cursor.FIELD_TYPE_STRING)
			{	
				return cursor.getString(0);
			}
		}
		
		Log.w("SQL", "GetCalculatedAttribute failed to get sql value for id = " + String.valueOf(_id) +
			         " and attributeKey " + attributeKey);
		
		//Default return
		return null;
	}
	
	public Integer GetAttributeUpgradeCost(SV.SQLColumn attributeKey) {
		Integer currentValue = Integer.valueOf(GetCalculatedAttribute(attributeKey));
		return (currentValue + 1) * 4;
	}
	
	private String GetGeneratedAttribute(SV.SQLColumn attributeKey) {
		if(attributeKey.equals(SV.KEY_EARTH))
		{
			Cursor cursor = _db.query(SV.TABLE_L5R_CHARACTERS, new String[] { SV.KEY_STAMINA.keyName, SV.KEY_WILLPOWER.keyName }, 
					SV.KEY_ID.keyName + "=?", new String[] { String.valueOf(_id) }, null, null, null, null);
			
			if (cursor != null)
			{
				cursor.moveToFirst();
				
				Integer calculatedValue = Integer.valueOf(Math.min(cursor.getInt(0),cursor.getInt(1))); 
				return calculatedValue.toString();
			}
		}
		else if(attributeKey.equals(SV.KEY_AIR))
		{
			Cursor cursor = _db.query(SV.TABLE_L5R_CHARACTERS, new String[] { SV.KEY_REFLEXES.keyName, SV.KEY_AWARENESS.keyName }, 
					SV.KEY_ID.keyName + "=?", new String[] { String.valueOf(_id) }, null, null, null, null);
			
			if (cursor != null)
			{
				cursor.moveToFirst();
				
				Integer calculatedValue = Integer.valueOf(Math.min(cursor.getInt(0),cursor.getInt(1))); 
				return calculatedValue.toString();
			}
		}
		else if(attributeKey.equals(SV.KEY_WATER))
		{
			Cursor cursor = _db.query(SV.TABLE_L5R_CHARACTERS, new String[] { SV.KEY_STRENGTH.keyName, SV.KEY_PERCEPTION.keyName }, 
					SV.KEY_ID.keyName + "=?", new String[] { String.valueOf(_id) }, null, null, null, null);
			
			if (cursor != null)
			{
				cursor.moveToFirst();
				
				Integer calculatedValue = Integer.valueOf(Math.min(cursor.getInt(0),cursor.getInt(1))); 
				return calculatedValue.toString();
			}
		}
		else if(attributeKey.equals(SV.KEY_FIRE))
		{
			Cursor cursor = _db.query(SV.TABLE_L5R_CHARACTERS, new String[] { SV.KEY_AGILITY.keyName, SV.KEY_INTELLIGENCE.keyName }, 
					SV.KEY_ID.keyName + "=?", new String[] { String.valueOf(_id) }, null, null, null, null);
			
			if (cursor != null)
			{
				cursor.moveToFirst();
				
				Integer calculatedValue = Integer.valueOf(Math.min(cursor.getInt(0),cursor.getInt(1))); 
				return calculatedValue.toString();
			}
		}
		
		return null;
	}
}
