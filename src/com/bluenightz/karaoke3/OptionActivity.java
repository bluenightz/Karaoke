package com.bluenightz.karaoke3;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.KeyEvent;

public class OptionActivity extends PreferenceActivity {
	public String ip = "192.168.1.1";

	@Override
	public void onCreate(Bundle s){
		super.onCreate(s);
		addPreferencesFromResource(R.layout.optionactivity);
		
		
	}
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
           //Things to Do

			Intent i = new Intent(OptionActivity.this, Karaoke.class);
			startActivity(i);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	
	  
	//@Override
	//public 
}
