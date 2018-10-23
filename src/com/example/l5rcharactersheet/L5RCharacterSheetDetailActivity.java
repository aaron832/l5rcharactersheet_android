package com.example.l5rcharactersheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class L5RCharacterSheetDetailActivity extends FragmentActivity {

	private long mSelectedCharacterID;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l5rcharactersheet_detail);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	mSelectedCharacterID = extras.getLong(SV._ID);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(L5RCharacterSheetDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(L5RCharacterSheetDetailFragment.ARG_ITEM_ID));
            arguments.putLong(SV._ID, mSelectedCharacterID);
            L5RCharacterSheetDetailFragment fragment = 
            		new L5RCharacterSheetDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.l5rcharactersheet_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, L5RCharacterSheetListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
