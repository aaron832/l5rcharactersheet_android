package com.example.l5rcharactersheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class L5RCharacterSheetListActivity extends FragmentActivity
        implements L5RCharacterSheetListFragment.Callbacks {

    private boolean mTwoPane;
    private long mSelectedCharacterID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l5rcharactersheet_list);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	mSelectedCharacterID = extras.getLong(SV._ID);
        }

        if (findViewById(R.id.l5rcharactersheet_detail_container) != null) {
            mTwoPane = true;
            ((L5RCharacterSheetListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.l5rcharactersheet_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(L5RCharacterSheetDetailFragment.ARG_ITEM_ID, id);
            arguments.putLong(SV._ID, mSelectedCharacterID);
            //TODO HERE IS WHERE I CHOOSE FRAGMENT AND LAYOUT 
            L5RCharacterSheetDetailFragment fragment = 
            		new L5RCharacterSheetDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.l5rcharactersheet_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, L5RCharacterSheetDetailActivity.class);
            detailIntent.putExtra(L5RCharacterSheetDetailFragment.ARG_ITEM_ID, id);
            detailIntent.putExtra(SV._ID, mSelectedCharacterID);
            startActivity(detailIntent);
        }
    }
}
