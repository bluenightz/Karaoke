package com.bluenightz.karaoke3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import com.bluenightz.karaoke3.R;


import android.app.Activity;
import android.content.Intent;
import android.util.*;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;

      
public class list extends Activity{
    /** Called when the activity is first created. */

	
	private String _nowsong = "0";
	static String _url = "http://bluenightz/karaokeServer/update_playerstatus.php?idS=";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Log.d("list.java","Start Activity");
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
	        String value = extras.getString("songid");
	        Log.e("txt",value);
        }
        
        
       ImageView _close = (ImageView) findViewById(R.id.close);
	   ImageView _play = (ImageView) findViewById(R.id.playbtn);
	   ImageView _stop = (ImageView) findViewById(R.id.stopbtn);
	   ImageView _replay = (ImageView) findViewById(R.id.replay);
	   ImageView _pause = (ImageView) findViewById(R.id.btnpause);
	   
		TextView _textview1 = (TextView) findViewById(R.id.song111);
		TextView _textview2 = (TextView) findViewById(R.id.song222);
		//_textview1.setVisibility(2);
		
		_close.setOnClickListener(new View.OnClickListener() {
			

			public void onClick(View v) {
				// TODO Auto-generated method stub
					//_changepage("result");
				finish();
			}
		});
		
		_textview1.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					_nowsong = "0";
		        	postData("1");
		    	}catch(Exception e){
		    		Log.v("Exception google search","Exception:"+e.getMessage());
		    	}
			}
		});
		_textview2.setOnClickListener(new View.OnClickListener() {
			
		
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					_nowsong = "1";
		        	postData("1");
		    	}catch(Exception e){
		    		Log.v("Exception google search","Exception:"+e.getMessage());
		    	}
			}
		});
		
		_play.setOnClickListener(new View.OnClickListener() {
			
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try{
	        	postData("1");
	    	}catch(Exception e){
	    		Log.v("Exception google search","Exception:"+e.getMessage());
	    	}
		}
		});
       _stop.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
		        	postData("0");
		    	}catch(Exception e){
		    		Log.v("Exception google search","Exception:"+e.getMessage());
		    	}
			}
		});
       _replay.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
		        	postData("0");
		        	postData("1");
		    	}catch(Exception e){
		    		Log.v("Exception google search","Exception:"+e.getMessage());
		    	}
			}
		});
       _pause.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
		        	postData("2");
		    	}catch(Exception e){
		    		Log.v("Exception google search","Exception:"+e.getMessage());
		    	}
			}
		});
       
       
       
       

       ImageView _volmain = (ImageView) findViewById(R.id.volmain);
       _volmain.setOnClickListener(new View.OnClickListener() {
		

		public void onClick(View v) {
			// TODO Auto-generated method stub
			RelativeLayout _r = (RelativeLayout) findViewById(R.id.volcontrol);
			
			if(_r.getVisibility()==View.VISIBLE){
					_r.setVisibility(View.INVISIBLE);
			}else{
					_r.setVisibility(View.VISIBLE);
			}
			
		}
       });
       
       ImageView _vrbtn = (ImageView) findViewById(R.id.btnRedu);
       _vrbtn.setOnClickListener(new View.OnClickListener() {
		
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
       });
	   ImageView _vibtn = (ImageView) findViewById(R.id.btnIncr);
	   _vibtn.setOnClickListener(new View.OnClickListener() {
		
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
       });
	
       
    }
    
    private String postData(String playerstat)throws MalformedURLException, IOException {

  	   String newFeed = _url+playerstat+"&S="+_nowsong;
    	
    	/*
       TextView t = (TextView) findViewById(R.id.showt);
 	   t.setText(newFeed);
 	   */
 	   
 	   StringBuilder response = new StringBuilder();
 	   Log.v("gsearch","gsearch url:"+newFeed);
 	   URL url = new URL(newFeed);
 	   
 	   HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
 	   
 	   if(httpconn.getResponseCode() == HttpURLConnection.HTTP_OK){
 		   BufferedReader input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()),8610);
 		   String strLine = null;
 		   while((strLine = input.readLine())!=null){
 			   response.append(strLine);
 		   }
 		   input.close();
 	   }
 	   return response.toString();
    } 

}


