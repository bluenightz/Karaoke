package com.bluenightz.karaoke3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import com.bluenightz.karaoke3.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class config extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        
        Button btnfolder = (Button) findViewById(R.id.btnfolder);
        final EditText txtfolder = (EditText) findViewById(R.id.btnfolder);
        
        
	
	}
}
