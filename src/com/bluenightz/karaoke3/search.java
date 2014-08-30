package com.bluenightz.karaoke3;
   
//import java.io.BufferedInputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/*
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.os.Message;
import android.graphics.*;
import android.os.Handler;
*/

import com.bluenightz.karaoke3.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.*;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.aig.karaoke.*;
public class search extends Activity{
    /** Called when the activity is first created. */
	/*
	private ViewSwitcher _viewswitcher;
	private TextView _menuTH;
	private Timer myTimer;
	private Activity _this = this;
	private Handler myHandler;
	private ImageView _home;
	private RelativeLayout _space1;
	private RelativeLayout _space2;
	private String _pagenow;
	*/   
	private String ip;
	private EditText _search_songtxt;
	private String _nowsong = "0";
	private String mode = "song";
	private InputMethodManager inputMethodManager;
	static String _url = "http://bluenightz/karaokeServer/update_playerstatus.php?idS=";
	public ArrayList<String> sN;
	public ArrayList<String> sID;
	public ArrayList<String> sF;
	private SharedPreferences s;
	private boolean windowB;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        	String dddd = Lang.getLang();
//        	Log.e("Lang show ", dddd);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        	
        Log.e("yess","sdfsdfsdf");
        
           s = PreferenceManager.getDefaultSharedPreferences(this);
           ip = s.getString("ip", null);
           String ip2 = s.getString("ip", null);
           ip = ip+":8080";
           
           pathGlobal.playlist = "http://"+ip2+"/karaoke/control.php";
             
           
           //List<com.aig.karaoke.mvClass.Data> allmv = mvClass.getAllMV();
           
//           if(playlist.isplaylistnull()){
//           	playlist.buildplaylist();
//           }    
                
//           mvClass.setpath(ip2);
//           if(playlist.gettotal()==0 && playlist.mvmode == false){
//	           deleteDelay.delayaction(new Runnable(){
//	
//				public void run() {
//					// TODO Auto-generated method stub
//			           mvClass.loadsong();
//				}
//	        	   
//	           }, 10000);
//           }
           
           
           karaokeTimeManage.setbasicpath(ip);
           
           Log.e("IP",ip);
           
           inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
           
           ImageView _home = (ImageView) findViewById(R.id.home);
		
	       _home.setOnClickListener(new View.OnClickListener() {
			
				public void onClick(View v) {

					//Lang.setlang("JP");
					Intent i = new Intent(search.this, Karaoke.class);
					i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(i);
				}
			});
	       
	       ImageView _remote = (ImageView) findViewById(R.id.remote);
	       TextView _watchsong = (TextView) findViewById(R.id.watchsong);
	       TextView _watchartist = (TextView) findViewById(R.id.watchartist);
	       TextView _watchqueue = (TextView) findViewById(R.id.watchqueue);
	       TextView _watchentertainment = (TextView) findViewById(R.id.watchentertainment);
	       TextView _watchallsong = (TextView) findViewById(R.id.lookallsong);
	       TextView _watchtop20 = (TextView) findViewById(R.id.top20);
	       TextView _watchmodern = (TextView) findViewById(R.id.watchmodern);
	       TextView _watchfolk = (TextView) findViewById(R.id.watchfolk);
	       TextView _watchlife = (TextView) findViewById(R.id.watchlife);
	       TextView _newentry = (TextView) findViewById(R.id.newentry);
	       TextView _search_maintxt = (TextView) findViewById(R.id.search_maintxt);
	       ImageView _mvbtn = (ImageView) findViewById(R.id.mvbtn);
	       
	       
	       
	       

	       
	       
	   
	       
	       
	       _remote.setOnClickListener(new View.OnClickListener() {
			
				 
				public void onClick(View v) {

					//if(playlist.gettotal()!=0){
					int total = playlist.pl.size();
					if(true){
					//if(total != 0){
						Intent i = new Intent(search.this, remote.class);
						startActivity(i);
					}else{
						Context context = getApplicationContext();
						CharSequence text = "Can't Show Playlist, Please add song before try playlist mode.";
						int duration = Toast.LENGTH_SHORT;
//						
						Toast toast = Toast.makeText(context, text, duration);
//						
						toast.show();
					}
				}
			});
//	       
	       OnClickListener _clicklist = null;
	       
	        
	       _clicklist = new View.OnClickListener(){

			 
			public void onClick(View v) {

				
				switch(v.getId()){
				case R.id.watchentertainment:
					
					break;
				case R.id.watchsong:
					_search_songtxt.performClick();
					
                    mode = "song";
				    if (inputMethodManager != null) {
				    	close_open();
				    } 
					break;
				case R.id.newentry:
					
					break;
				case R.id.watchqueue:
					
					
					
					Bundle extras = getIntent().getExtras();
			        if(extras != null) {
				        if(extras.getStringArrayList("sN")!=null && extras.getStringArrayList("sN").size()>0){

				        	Intent i = new Intent(search.this, remote.class);
							i.putStringArrayListExtra("sN", sN);
							i.putStringArrayListExtra("sID", sID);
							i.putStringArrayListExtra("sF", sF);
							startActivity(i);
				        }else{

							Context context = getApplicationContext();
							CharSequence text = "test 1";
							int duration = Toast.LENGTH_SHORT;
							
							Toast toast = Toast.makeText(context, text, duration);
							
							toast.show();
				        }
			        }else{

						Context context = getApplicationContext();
						CharSequence text = "test 2";
						int duration = Toast.LENGTH_SHORT;
						
						Toast toast = Toast.makeText(context, text, duration);
						
						toast.show();
			        }
					
					
					break;
				case R.id.watchartist:
					_search_songtxt.performClick();

                    mode = "artist";
				    if (inputMethodManager != null) {
				    	close_open();
				    }
					break;
				case R.id.lookallsong:
					Intent i2 = new Intent(search.this, result.class);
                    i2.putExtra("searchstr","" );
                    i2.putExtra("modestr","all" );

					i2.putStringArrayListExtra("sN", sN);
					i2.putStringArrayListExtra("sID", sID);
					i2.putStringArrayListExtra("sF", sF);
					
					startActivity(i2);
					break;
				case R.id.top20:
					Intent i3 = new Intent(search.this, result.class);
                    i3.putExtra("searchstr","" );
                    i3.putExtra("modestr","top20" );

					i3.putStringArrayListExtra("sN", sN);
					i3.putStringArrayListExtra("sID", sID);
					i3.putStringArrayListExtra("sF", sF);
					
					startActivity(i3);
					break;
				case R.id.watchmodern:
					Intent i4 = new Intent(search.this, result.class);
                    i4.putExtra("searchstr","" );
                    i4.putExtra("modestr","modern" );

					i4.putStringArrayListExtra("sN", sN);
					i4.putStringArrayListExtra("sID", sID);
					i4.putStringArrayListExtra("sF", sF);
                    
					startActivity(i4);
					break; 
				case R.id.watchfolk:
					Intent i5 = new Intent(search.this, result.class);
                    i5.putExtra("searchstr","" );
                    i5.putExtra("modestr","folk" );

					i5.putStringArrayListExtra("sN", sN);
					i5.putStringArrayListExtra("sID", sID);
					i5.putStringArrayListExtra("sF", sF);
                    
					startActivity(i5);
					break;
				case R.id.watchlife:
					Intent i6 = new Intent(search.this, result.class);
                    i6.putExtra("searchstr","" );
                    i6.putExtra("modestr","life" );

					i6.putStringArrayListExtra("sN", sN);
					i6.putStringArrayListExtra("sID", sID);
					i6.putStringArrayListExtra("sF", sF);
					
					startActivity(i6);
					break;
				case R.id.mvbtn:
					Intent i7 = new Intent(search.this, result.class);
                    i7.putExtra("searchstr","" );
                    i7.putExtra("modestr","mv" );

					i7.putStringArrayListExtra("sN", sN);
					i7.putStringArrayListExtra("sID", sID);
					i7.putStringArrayListExtra("sF", sF);
					
					startActivity(i7);
					break;
				default :
					//_changepage("list");
					//Intent i = new Intent(search.this, result.class);
					//startActivity(i);
					break;
					
				}


				
			}
	    	   
	       };
	       
	       

	       _watchsong.setOnClickListener(_clicklist);
	       _watchartist.setOnClickListener(_clicklist);
	       _watchqueue.setOnClickListener(_clicklist);
	       _watchentertainment.setOnClickListener(_clicklist);
	       _watchallsong.setOnClickListener(_clicklist);
	       _watchtop20.setOnClickListener(_clicklist);
	       _watchmodern.setOnClickListener(_clicklist);
	       _watchfolk.setOnClickListener(_clicklist);
	       _watchlife.setOnClickListener(_clicklist);
	       _mvbtn.setOnClickListener(_clicklist);
	       _newentry.setOnClickListener(_clicklist);
	       
	       
	       _search_songtxt = (EditText) findViewById(R.id.search_songtxt);
	       windowB = inputMethodManager.hideSoftInputFromWindow(_search_songtxt.getApplicationWindowToken(), 0);
	       _search_songtxt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				String valuetxt = String.valueOf(_search_songtxt.getText().toString());
				String Search = "Search";
				if(valuetxt.equals(Search)){
					_search_songtxt.setText("");
					Log.e("yes",valuetxt);
				}
			}
	       });
	       
	       _search_songtxt.setOnKeyListener(new View.OnKeyListener() {
	    	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	    	        // If the event is a key-down event on the "enter" button
	    	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
	    	            (keyCode == KeyEvent.KEYCODE_ENTER)) {
	    	          // Perform action on key press
	    	          //Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
	    	        	
	    	        	
	    	        	windowB = inputMethodManager.showSoftInput(_search_songtxt, 0);
	                    
	    	        	//_changepage("result");
	                    Intent i = new Intent(search.this, result.class);
	                    String s = _search_songtxt.getText().toString();
	                    i.putExtra("searchstr",s );
	                    //i.putExtra("searchstr","‡∏ù‡∏ô" );
	                    i.putExtra("modestr",mode );
	    				i.putStringArrayListExtra("sN", sN);
	    				i.putStringArrayListExtra("sID", sID);
	    				i.putStringArrayListExtra("sF", sF);
	                    _search_songtxt.setText("");
						startActivity(i);
	    	          return true;
	    	        }
	    	        return false;
	    	    }
	       });
        
        

			Lang.buildlang();
			
			
			
			String Test = Lang.getMenu(0);
			String LandMode = Lang.getLang();
			
	       _search_maintxt.setText(Lang.getMenu(0));
	       _watchsong.setText(Lang.getMenu(1));
	       _watchartist.setText(Lang.getMenu(2));
	       _watchqueue.setText(Lang.getMenu(3));
	       _watchentertainment.setText(Lang.getMenu(4));

	       _watchallsong.setText(Lang.getMenu(10));
	       _watchtop20.setText(Lang.getMenu(6));
	       _watchmodern.setText(Lang.getMenu(7));
	       _watchfolk.setText(Lang.getMenu(8));
	       _watchlife.setText(Lang.getMenu(9));
	       _newentry.setText(Lang.getMenu(5));
	       
    }

    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
           //Things to Do
        	Log.e("this is back button","");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private void close_open(){
    	//boolean b = inputMethodManager.isActive();
    	if(windowB){
    		windowB = inputMethodManager.showSoftInput(_search_songtxt, 0);
    	}else{
    		windowB = inputMethodManager.hideSoftInputFromWindow(_search_songtxt.getApplicationWindowToken(), 0);
    	}
    	
    }
    
    
    private String postData(String playerstat)throws MalformedURLException, IOException {
       
    
 	   String newFeed = _url+playerstat+"&S="+_nowsong;
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


