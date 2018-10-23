package com.example.l5rcharactersheet;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.content.Intent;
import android.database.Cursor;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.support.v4.widget.SimpleCursorAdapter;

public class CharacterMenu extends ListActivity {

	private CharacterDatabaseHelper mCharacterDatabaseHelper;
	private SimpleCursorAdapter cursorAdapter;
	private Cursor cCharacterList;
	
	private static final String fields[] = { SV.KEY_NAME.keyName };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_menu);
        registerForContextMenu(getListView());
        
        mCharacterDatabaseHelper = new CharacterDatabaseHelper(getApplicationContext());
        //TODO: Test by adding content
        mCharacterDatabaseHelper.Testing_ReCreateTable();
        
        UpdateCharacterList();
    }
    
    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
    	super.onListItemClick(l, view, position, id);
    	
    	Intent newIntent = new Intent(CharacterMenu.this, L5RCharacterSheetListActivity.class);
    	newIntent.putExtra(SV._ID, id);
    	startActivity(newIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_character_menu, menu);
        return true;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.activity_character_context_menu, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //respond to menu item selection
    	switch (item.getItemId()) {
        	case R.id.new_character:
        		startActivityForResult(new Intent(this, L5RNewCharacterActivity.class), 1);
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	switch (item.getItemId()) {
    		case R.id.delete_character:
    			mCharacterDatabaseHelper.DeleteCharacter(info.id);
    			UpdateCharacterList();
    			return true;
    		default:
    			return super.onContextItemSelected(item);
    	}
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == 1) {
    		if(resultCode == RESULT_OK) {
    	      UpdateCharacterList(); 
    		}
    	}
    	if (resultCode == RESULT_CANCELED) {
    	     //Write your code on no result return 
    	}
    }
    
    protected void UpdateCharacterList() {
    	cCharacterList = mCharacterDatabaseHelper.getL5RCharacterNameCursor();
    	cursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_character_menu, 
        		cCharacterList, fields, new int[] { R.id.characterName }, 0);
        setListAdapter(cursorAdapter);
    }
}
