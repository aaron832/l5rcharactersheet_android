package com.example.l5rcharactersheet;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class L5RCS_XP_TraitsActivity extends Activity {
	
	L5RCharacter mSelectedCharacter;
	Vector<SV.KeyViewPair> vAttributeAddButtons;
	Integer currentXP;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l5rcs_xp_traits);
        
        long selectedCharacterID;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	selectedCharacterID = extras.getLong(SV._ID);
        	mSelectedCharacter = new L5RCharacter(getApplicationContext(), selectedCharacterID);
        }
        
        vAttributeAddButtons = new Vector<SV.KeyViewPair>();
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_STAMINA,
        		(Button)findViewById(R.id.xp_add_stamina_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_WILLPOWER,
        		(Button)findViewById(R.id.xp_add_willpower_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_STRENGTH,
        		(Button)findViewById(R.id.xp_add_strength_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_PERCEPTION,
        		(Button)findViewById(R.id.xp_add_perception_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_REFLEXES,
        		(Button)findViewById(R.id.xp_add_reflexes_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_AWARENESS,
        		(Button)findViewById(R.id.xp_add_awareness_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_AGILITY,
        		(Button)findViewById(R.id.xp_add_agility_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_INTELLIGENCE,
        		(Button)findViewById(R.id.xp_add_intelligence_button)));
        vAttributeAddButtons.add(new SV.KeyViewPair(SV.KEY_VOID,
        		(Button)findViewById(R.id.xp_add_void_button)));
        
        for(SV.KeyViewPair ktvPair : vAttributeAddButtons) {
    		if(ktvPair.mView != null) {
    			((Button)ktvPair.mView).setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						SV.SQLColumn buttonColumn = null;
						for(SV.KeyViewPair ktvPair : vAttributeAddButtons) {
							if(ktvPair.mView.getId() == v.getId()) {
								buttonColumn = ktvPair.mKey;
								break;
							}
						}
						
						L5RCS_XP_TraitsActivity.this.currentXP = Integer.valueOf(L5RCS_XP_TraitsActivity.this.mSelectedCharacter.
								GetCalculatedAttribute(SV.KEY_XP));
						Integer defaultXPCost = L5RCS_XP_TraitsActivity.this.mSelectedCharacter.
								GetAttributeUpgradeCost(buttonColumn);
						Integer currentAttributeValue = Integer.valueOf(L5RCS_XP_TraitsActivity.this.mSelectedCharacter.
								GetCalculatedAttribute(buttonColumn));
						
						// TODO Auto-generated method stub
						AlertDialog.Builder alert = new AlertDialog.Builder(L5RCS_XP_TraitsActivity.this);

						if(buttonColumn != null) {
							alert.setTitle("Upgrade " + getString(buttonColumn.mRealNameResId) +
									" from " + currentAttributeValue.toString() + 
									" to " + Integer.toString(currentAttributeValue+1) + "?");
							alert.setMessage("Spend the following XP for trait upgrade?");
	
							// Set an EditText view to get user input 
							final EditText input = new EditText(L5RCS_XP_TraitsActivity.this);
							input.setInputType(InputType.TYPE_CLASS_NUMBER);
							input.setText(defaultXPCost.toString(), TextView.BufferType.EDITABLE);
							alert.setView(input);
	
							alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									Editable value = input.getText();
									// Do something with value!
									if( Integer.valueOf(value.toString()).intValue() > L5RCS_XP_TraitsActivity.this.currentXP.intValue() ) {
										Toast.makeText(L5RCS_XP_TraitsActivity.this, R.string.not_enough_xp_upgrade, Toast.LENGTH_SHORT).show(); 
									}
								}
							});
	
							alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							  public void onClick(DialogInterface dialog, int whichButton) {
							    // Canceled.
							  }
							});
	
							alert.show();
						}
					}
				});
    		}
        }
        
        if(mSelectedCharacter != null) {
        	UpdateDisplay();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_l5rcs_xp_traits, menu);
        return true;
    }
    
    public void UpdateDisplay() {
    	Vector<SV.KeyViewPair> vUpdateFields = new Vector<SV.KeyViewPair>();
    	Log.w("DetailFrame", "Adding to vector...");
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_XP, 
    			findViewById(R.id.xp_traits_xp_value)));
    	
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_STAMINA, 
    			findViewById(R.id.xp_traits_stamina_value))); 
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_WILLPOWER, 
    			findViewById(R.id.xp_traits_willpower_value)));
    	
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_STRENGTH, 
    			findViewById(R.id.xp_traits_strength_value))); 
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_PERCEPTION, 
    			findViewById(R.id.xp_traits_perception_value)));
    	
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_REFLEXES, 
    			findViewById(R.id.xp_traits_reflexes_value))); 
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_AWARENESS, 
    			findViewById(R.id.xp_traits_awareness_value)));
    	
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_AGILITY, 
    			findViewById(R.id.xp_traits_agility_value))); 
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_INTELLIGENCE, 
    			findViewById(R.id.xp_traits_intelligence_value)));
    	
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_VOID, 
    			findViewById(R.id.xp_traits_void_value)));
    	
    	for(SV.KeyViewPair ktvPair : vUpdateFields) {
    		if(ktvPair.mView != null) {
    			Log.w("DetailFrame", "Updating field = " + ktvPair.mKey.keyName);
    			((TextView)ktvPair.mView).setText(mSelectedCharacter.GetCalculatedAttribute(ktvPair.mKey));
    		}
    	}
    }

}
