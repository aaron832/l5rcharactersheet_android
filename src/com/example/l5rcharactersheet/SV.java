package com.example.l5rcharactersheet;

import android.database.Cursor;
import android.provider.BaseColumns;
import android.view.View;

public final class SV implements BaseColumns {

	public static class SQLColumn {
    	String keyName;
    	int mRealNameResId;
    	String sqlDataType;
    	int dataType;
    	
    	public SQLColumn(String _keyName, int _realNameResId,
    			String _SQLDataType, int _dataType) {
    		keyName = new String(_keyName);
    		dataType = _dataType;
    		sqlDataType = _SQLDataType;
    		mRealNameResId = _realNameResId;
    	}
    	
    	public String GetCreateTableString() {
			return keyName + " " + sqlDataType;
		}
    }
	
	public static class KeyViewPair {
		public SQLColumn mKey;
		public View mView;
		public int mRealNameResId;
		
		KeyViewPair(SQLColumn _key, View _textView) {
			mKey = _key;
			mView = _textView;
		}
	}
	
	// This class cannot be instantiated
    private SV() {
    }
    
    // Database Version
    public static final int DATABASE_VERSION = 1;
    
    // Database Name
    public static final String DATABASE_NAME = "L5RCharacterSheet";

 	// L5RCharacters table name
    public static final String TABLE_L5R_CHARACTERS = "L5RCharacters";

    // Character Table Columns names
    public static final SQLColumn KEY_ID = new SQLColumn(_ID, -1, "INTEGER PRIMARY KEY", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_NAME = new SQLColumn("name", R.string.character_name, "TEXT", Cursor.FIELD_TYPE_STRING);
    public static final SQLColumn KEY_CLAN = new SQLColumn("clan", R.string.l5rclan, "TEXT", Cursor.FIELD_TYPE_STRING);;
    public static final SQLColumn KEY_FAMILY = new SQLColumn("family", R.string.l5rfamily, "TEXT", Cursor.FIELD_TYPE_STRING);;
    public static final SQLColumn KEY_SCHOOL = new SQLColumn("school", R.string.l5rschool, "TEXT", Cursor.FIELD_TYPE_STRING);;
    public static final SQLColumn KEY_XP = new SQLColumn("xp", R.string.l5rxp, "INT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_STAMINA = new SQLColumn("stamina", R.string.l5rstamina, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_WILLPOWER = new SQLColumn("willpower", R.string.l5rwillpower, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_STRENGTH = new SQLColumn("strength", R.string.l5rstrength, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_PERCEPTION = new SQLColumn("perception", R.string.l5rperception, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_REFLEXES = new SQLColumn("reflexes", R.string.l5rreflexes, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_AWARENESS = new SQLColumn("awareness", R.string.l5rawareness, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_AGILITY = new SQLColumn("agility", R.string.l5ragility, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_INTELLIGENCE = new SQLColumn("intelligence", R.string.l5rintelligence, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_VOID = new SQLColumn("voidring", R.string.l5rvoid, "TINYINT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_HONOR = new SQLColumn("honor", R.string.l5rhonor, "FLOAT", Cursor.FIELD_TYPE_FLOAT);
    public static final SQLColumn KEY_GLORY = new SQLColumn("glory", R.string.l5rglory, "FLOAT", Cursor.FIELD_TYPE_FLOAT);
    public static final SQLColumn KEY_STATUS = new SQLColumn("status", R.string.l5rstatus, "FLOAT", Cursor.FIELD_TYPE_FLOAT);
    public static final SQLColumn KEY_TAINT = new SQLColumn("taint", R.string.l5rtaint, "FLOAT", Cursor.FIELD_TYPE_FLOAT);
    
    // Calculated key values
    public static final SQLColumn KEY_EARTH = new SQLColumn("earthring", R.string.l5rearth, "FLOAT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_WATER = new SQLColumn("waterring", R.string.l5rwater, "FLOAT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_AIR = new SQLColumn("airring", R.string.l5rair, "FLOAT", Cursor.FIELD_TYPE_INTEGER);
    public static final SQLColumn KEY_FIRE = new SQLColumn("firering", R.string.l5rfire, "FLOAT", Cursor.FIELD_TYPE_INTEGER);

}
