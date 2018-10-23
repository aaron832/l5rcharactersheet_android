package com.example.l5rcharactersheet;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class L5RNewCharacterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l5r_new_character);
        
        final Button button = (Button) findViewById(R.id.new_character_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	CheckAndAddCharacter();
            }
        });
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
    
    private void CheckAndAddCharacter() {
    	EditText etName = (EditText)findViewById(R.id.character_name_editText);
    	EditText etClan = (EditText)findViewById(R.id.clan_editText);
    	EditText etFamily = (EditText)findViewById(R.id.family_editText);
    	EditText etSchool = (EditText)findViewById(R.id.school_editText);
    	
    	if(etName.getText().toString().length() == 0) {
    		Toast.makeText(this, R.string.create_charater_no_name, Toast.LENGTH_SHORT).show();
    	}
    	else {
    		L5RCharacter newCharacter = new L5RCharacter(
    				getApplicationContext(), etName.getText().toString(),
    				etClan.getText().toString(), etFamily.getText().toString(),
    				etSchool.getText().toString());
    		Log.w("DetailFrame", newCharacter.getName() + " Character created..." );
    		
    		Intent returnIntent = new Intent();
    		setResult(RESULT_OK, returnIntent);        
    		finish();
    	}
    }
}
