package com.bluenightz.karaoke3;

//import java.io.BufferedInputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
*/


import java.io.File;
//import java.lang.Object;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URISyntaxException;
import java.net.URL;
//import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.bluenightz.karaoke3.R;
//import com.bluenightz.karaoke3.remote.Data;
//import com.bluenightz.karaoke3.remote.DataHandler;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.*;
/*
import android.content.Context;
import android.util.*;
import android.os.Handler;
import android.os.Message;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
*/
import com.aig.karaoke.*;
public class Karaoke extends Activity{
    /** Called when the activity is first created. */
	/*
	private ViewSwitcher _viewswitcher;
	private TextView _menuTH;
	private Timer myTimer;
	private Activity _this = this;
	private Handler myHandler;
	private EditText _search_songtxt;
	private ImageView _home;
	private RelativeLayout _space1;
	private RelativeLayout _space2;
	private String _pagenow;
	private String _nowsong = "0";
	*/
	static String _url = "http://bluenightz/karaokeServer/update_playerstatus.php?idS=";
	   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);   
        
           
        SharedPreferences s = null;
        s = PreferenceManager.getDefaultSharedPreferences(this);
        String ip2 = "";
        ip2 = s.getString("ip", null);

    	  
        
    	boolean r = false;
    			r = exists("http://"+ip2+"/karaoke/index.php");

    	
    	
        if(r){
        	if(playlist.isplaylistnull()){
               	playlist.buildplaylist();
            } 
        	mvClass.setpath(ip2);
	        if(playlist.gettotal() == 0 && playlist.mvmode == false){
		           deleteDelay.delayaction(new Runnable(){
		
					public void run() {
						// TODO Auto-generated method stub
				           //mvClass.loadsong();
					}
		        	   
		           }, 1000);
	        }  
	        
        }else{ 
	        
	        
        }
        
        
        final TextView txt = (TextView) findViewById(R.id.text1);  
        ImageView menu1 = (ImageView) findViewById(R.id.menu1);
        ImageView menu2 = (ImageView) findViewById(R.id.menu2);
        ImageView menu3 = (ImageView) findViewById(R.id.menu3);
        ImageView menu4 = (ImageView) findViewById(R.id.menu4);
        ImageView menu5 = (ImageView) findViewById(R.id.menu5);
        
        final Button btnconfig = (Button) findViewById(R.id.btnconfig);
        
        Typeface font = Typeface.createFromAsset(getAssets(), "Helvetica.ttf");  
        
        txt.setTypeface(font); 
//        menu1.setTypeface(font); 
//        menu2.setTypeface(font); 
//        menu3.setTypeface(font); 
//        menu4.setTypeface(font); 
//        menu5.setTypeface(font); 
        
        menu1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lang.setlang("TH");
				Lang.buildlang();
				Intent i2 = new Intent(Karaoke.this,search.class);
				i2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i2);
				
			}
		});
        menu2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lang.setlang("EN");
				Lang.buildlang();
				Intent i2 = new Intent(Karaoke.this,search.class);
				i2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i2);
				
				
				
				
				
				
			}
		});
        menu3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lang.setlang("CH");
				Lang.buildlang();
				Intent i2 = new Intent(Karaoke.this,search.class);
				i2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i2);
				
			}
		});
        menu4.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lang.setlang("JP");
				Lang.buildlang();
				Intent i2 = new Intent(Karaoke.this,search.class);
				i2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i2);
				
			}
		});
        menu5.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lang.setlang("KR");
				Lang.buildlang();
				Intent i2 = new Intent(Karaoke.this,search.class);
				i2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i2);
				
			}
		});
        
        
        btnconfig.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Karaoke.this, config.class);
				startActivity(i);
			}
		});
        
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
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
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    		case R.id.settings:
    			Intent i = new Intent(this,OptionActivity.class);
    			startActivity(i);
    			
    			return true;
    		case R.id.update:
    			updateSYS();
    			return true;
    	}
		return false;
    }
        
    public void updateSYS(){
		
		Context context = getApplicationContext();
		CharSequence text = "������������������������������������������...���������������������������������������������������������������������������������������������������";  
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text, duration);   
		
		toast.show();
		
    	try {   
    		  
    		//URL url = new URL("http://aig.avc-holding.com/karaoke.apk");
    		URL url = new URL("http://dton.avc-holding.com/karaoke/karaoke.apk");
            //URL url = new URL("http://10.10.3.40/karaoke3.apk");
            //URL url = new URL("http://aig.avc-holding.com/karaoke3.apk");
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH = Environment.getExternalStorageDirectory().toString()+"/Download/";
            Log.e("ttttt",String.valueOf(Environment.getExternalStorageDirectory().toString()+"/Download/"));
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, "update.apk");
            if(outputFile.exists()){
                outputFile.delete();   
            }
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory().toString()+"/Download/update.apk")), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            startActivity(intent);


        } catch (Exception e) {
            Log.e("UpdateAPP", "Update error! " + e.getMessage());
        }
    }

    public List<Data> _parseXml(String u) { 
		  List<Data> data = null; 
		  URL url ;
		  // sax stuff 
		  try { 
			url = new URL(u);
		    SAXParserFactory spf = SAXParserFactory.newInstance(); 
		    SAXParser sp = spf.newSAXParser(); 
		 
		    XMLReader xr = sp.getXMLReader(); 
		 
		    DataHandler dataHandler = new DataHandler(); 
		    xr.setContentHandler(dataHandler); 
		    //InputStream ins = getResources().openRawResource(R.raw.data);
		    //xr.parse(new InputSource(ins)); 
		    xr.parse(new InputSource(url.openStream()));
		     
		    data = dataHandler.getData();
		    
		    
		 
		  } catch(ParserConfigurationException pce) { 
		    Log.e("SAX XML", "sax parse error", pce); 
		  } catch(SAXException se) { 
		    Log.e("SAX XML", "sax error", se); 
		  } catch(IOException ioe) { 
		    Log.e("SAX XML", "sax parse io error", ioe); 
		  } 
		 
		  return data; 
	} 
    
    public class DataHandler extends DefaultHandler { 
		 
		  // booleans that check whether it's in a specific tag or not 
		  private boolean _inleaf; 
		  private boolean _time;
		  
		  // this holds the data 
		  private Data _data; 
		  private List<Data> Datas; 
		  
		 
		  /** 
		   * Returns the data object 
		   * 
		   * @return 
		   */ 
		  public List<Data> getData() { 
		    return Datas; 
		  } 
		 
		  /** 
		   * This gets called when the xml document is first opened 
		   * 
		   * @throws SAXException 
		   */ 
		  @Override 
		  public void startDocument() throws SAXException { 
		    Datas = new ArrayList<Data>(); 
		  } 
		 
		  /** 
		   * Called when it's finished handling the document 
		   * 
		   * @throws SAXException 
		   */ 
		  @Override 
		  public void endDocument() throws SAXException { 
		 
		  } 
		 
		  /** 
		   * This gets called at the start of an element. Here we're also setting the booleans to true if it's at that specific tag. (so we 
		   * know where we are) 
		   * 
		   * @param namespaceURI 
		   * @param localName 
		   * @param qName 
		   * @param atts 
		   * @throws SAXException 
		   */ 
		  @Override
		  public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException { 
			  
			if(localName.equals("leaf")){
			  _data = new Data();
		      Datas.add(_data);
			  _data.id = atts.getValue("id");
			  _data.current = atts.getValue("current");
			  _data.time = atts.getValue("duration");
			  _inleaf = true;
			}
			if(localName.equals("time")){
				_time = true;
			}
		  } 
		 
		  /** 
		   * Called at the end of the element. Setting the booleans to false, so we know that we've just left that tag. 
		   * 
		   * @param namespaceURI 
		   * @param localName 
		   * @param qName 
		   * @throws SAXException 
		   */ 
		  @Override 
		  public void endElement(String namespaceURI, String localName, String qName) throws SAXException { 
		    Log.v("endElement", localName); 
		    if(localName.equals("time")){
		    	_time = false;
		    }
		  
		  } 
		 
		  /** 
		   * Calling when we're within an element. Here we're checking to see if there is any content in the tags that we're interested in 
		   * and populating it in the Config object. 
		   * 
		   * @param ch 
		   * @param start 
		   * @param length 
		   */ 
		  @Override 
		  public void characters(char ch[], int start, int length) { 
		    String chars = new String(ch, start, length); 
		    chars = chars.trim(); 
		    
		    if(_time){
				  _data = new Data();
				  Datas.add(_data);
				  _data.time = chars.toString();
		    }

		  } 
		  
		
		  
		}
	
	public class Data { 
		  // I know this could be an int, but this is just to show you how it works 
		  
		  public String id="";
		  public String current="";
		  public String time="";
		 
		  public Data() { 
		 
		  }
	}

	 public static boolean exists(String URLName){
	        try {
	          HttpURLConnection.setFollowRedirects(false);
	          // note : you may also need
	          HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
	          con.setInstanceFollowRedirects(false);
	          
	          return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	        }
	        catch (Exception e) {
	           e.printStackTrace();
	           return false;
	        }
	}
    
}


