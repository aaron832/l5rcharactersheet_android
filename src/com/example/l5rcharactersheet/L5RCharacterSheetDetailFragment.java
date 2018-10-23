package com.example.l5rcharactersheet;

import java.util.Vector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class L5RCharacterSheetDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    DummyContent.DummyItem mItem;
    View rootView;
    L5RCharacter mSelectedCharacter;
    
    public L5RCharacterSheetDetailFragment() {
    	
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            mSelectedCharacter = new L5RCharacter(
            		getActivity().getApplicationContext(), getArguments().getLong(SV._ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	//This prevents a new layout overlaying an existing one if the user clicks again
    	container.removeAllViews();
    	
        if (mItem != null) {
        	rootView = inflater.inflate(mItem.layout, container, false);
        }
        
        return rootView;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
    	
    	if (mItem != null) {
        	switch(Integer.valueOf(mItem.id)) {
        		case 1:
        			ViewCreated_Status();
        			break;
        		case 2:
        			break;
        		case 3:
        			break;
        		case 4:
        			break;
        		case 5:
        			break;
        		case 6:
        			break;
        		case 7:
        			break;
        		case 8:
        			ViewCreated_XP();
        			break;
        		default:
        			break;
        	}
        }
    }
    
    public void ViewCreated_Status() {
    	//Update fields
    	Vector<SV.KeyViewPair> vUpdateFields = new Vector<SV.KeyViewPair>();
    	Log.w("DetailFrame", "Adding to vector...");
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_STAMINA, (TextView) rootView.findViewById(R.id.stamina_value))); 
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_WILLPOWER, (TextView) rootView.findViewById(R.id.willpower_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_STRENGTH, (TextView) rootView.findViewById(R.id.strength_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_PERCEPTION, (TextView) rootView.findViewById(R.id.perception_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_REFLEXES, (TextView) rootView.findViewById(R.id.reflexes_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_AWARENESS, (TextView) rootView.findViewById(R.id.awareness_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_AGILITY, (TextView) rootView.findViewById(R.id.agility_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_INTELLIGENCE, (TextView) rootView.findViewById(R.id.intelligence_value)));
    	
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_EARTH, (TextView) rootView.findViewById(R.id.earth_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_AIR, (TextView) rootView.findViewById(R.id.air_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_WATER, (TextView) rootView.findViewById(R.id.water_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_FIRE, (TextView) rootView.findViewById(R.id.fire_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_VOID, (TextView) rootView.findViewById(R.id.void_value)));
    	
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_HONOR, (TextView) rootView.findViewById(R.id.honor_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_GLORY, (TextView) rootView.findViewById(R.id.glory_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_STATUS, (TextView) rootView.findViewById(R.id.status_value)));
    	vUpdateFields.add(new SV.KeyViewPair(SV.KEY_TAINT, (TextView) rootView.findViewById(R.id.taint_value)));
    	
    	for(SV.KeyViewPair ktvPair : vUpdateFields) {
    		if(ktvPair.mView != null) {
    			Log.w("DetailFrame", "Updating field = " + ktvPair.mKey.keyName);
    			((TextView)ktvPair.mView).setText(mSelectedCharacter.GetCalculatedAttribute(ktvPair.mKey));
    		}
    	}
    	
    	//Create void spent radio buttons
    	Integer voidRingValue = Integer.valueOf(mSelectedCharacter.GetCalculatedAttribute(SV.KEY_VOID));
    	//String[] radioButtonIDs = new String[] { "void_spent_radioButton",
    	int[] voidUsedRadioButtonIDs = new int[] { R.id.void_spent_radioButton1, R.id.void_spent_radioButton2,
    											   R.id.void_spent_radioButton3, R.id.void_spent_radioButton4,
    											   R.id.void_spent_radioButton5, R.id.void_spent_radioButton6,
    											   R.id.void_spent_radioButton7, R.id.void_spent_radioButton8,
    											   R.id.void_spent_radioButton9 };

    	RelativeLayout voidRadioLayout = (RelativeLayout) getActivity().findViewById(R.id.void_spent_radio_layout);
    	for(int i=0; i < voidRingValue; i++) {
    		RadioButton radioButton = new RadioButton(getActivity().getApplicationContext());
    		radioButton.setId(voidUsedRadioButtonIDs[i]);
    		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
    		if(i != 0) {
    			params.addRule(RelativeLayout.RIGHT_OF, voidUsedRadioButtonIDs[i-1]);
    		}
    		else {
    			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    		}
    		radioButton.setLayoutParams(params);
    		radioButton.setPadding(5, 0, 5, 0);
    		voidRadioLayout.addView(radioButton);
    	}
    }
    
    public void ViewCreated_XP() {
    	Button button = (Button) getActivity().findViewById(R.id.xp_traits_button);
    	 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			  Intent browserIntent = 
                            new Intent(getActivity().getApplicationContext(), L5RCS_XP_TraitsActivity.class);
			  browserIntent.putExtra(SV._ID, mSelectedCharacter._id);
			  startActivity(browserIntent);
 
			}
 
		});
    }
}
