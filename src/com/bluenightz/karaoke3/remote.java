package com.bluenightz.karaoke3;

import java.io.BufferedReader;
import com.terlici.*;
import com.terlici.dragndroplist.DragNDropListView;
import com.terlici.dragndroplist.DragNDropSimpleAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.bluenightz.karaoke3.R;
import com.bluenightz.karaoke3.result.AlbumListAdapter;
import com.bluenightz.karaoke3.result.Data;
import com.bluenightz.karaoke3.result.DataHandler;
import com.bluenightz.karaoke3.result.AlbumListAdapter.AlbumListWrapper;
//import com.bluenightz.karaoke3.HandleJSON;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.renderscript.Element;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.aig.karaoke.deleteDelay;
import com.aig.karaoke.karaokeTimeManage;
import com.aig.karaoke.playlist;
import com.aig.karaoke.playlist.song;

public class remote extends Activity{
	
	private ImageView search;
	
	private String ip;
	private String host;
	private static String urlcommand;
	private String statusJson;
	private String urlplaylist;
	private String serverpath;
	public ArrayList<String> sN;
	public ArrayList<String> sID;
	public ArrayList<String> sF;
 	public List<song> data;
 	//public List<status> statusData;
 	private Boolean mutestat;
 	private ImageView mutebtn;
 	private SeekBar volbar1;
 	private String selectedID;
 	private int selectedIndex = 0;
 	private SharedPreferences s;
 	private song tempsong1 = null;
 	private song tempsong2 = null;
 	private String tFirst = null;
 	private String idcurrent;
 	private TextView prevtext = null;
 	private View replayV;
 	private song tempforplayagain;
 	private View stopV;
 	private View playV;
 	private View prevV;
 	private View nextV;
 	private View pausebtn;
 	private String urlstatus;
 	private int __idtemp;
 	private String Time;
 	private TextView prevView;
 	public int s1 = -1;
	public int s2 = -1;
	private TextView TextTemp;
	private int tsong1 = 0;
	private int tsong2 = 0;
	private DragNDropSimpleAdapter _a = null;
 	
 	private TextView txtViewColor = null;

	private String idT = "";    
 	
 	private Button btndone;
 	private Handler mHandler = new Handler();
 	private List<Data> mv;
 	private String newplaylist;
 	
 	private ArrayList<Map<String, Object>> items;
 	private String webPath;
 	private int timeOut;
 	private String mainUrl;
 	
 	
 	public String currentplid;
    public String time;
    public String length;
    public String volume;
    public CountDownTimer  t;
    
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("remote.java","Start Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote);
        String _l = Lang.getLang();
        s = PreferenceManager.getDefaultSharedPreferences(this);
        ip = s.getString("ip", null);
        
        webPath = "/karaoke/";
        serverpath = "http://"+ip+"/karaoke/";
        mainUrl = serverpath+"playlist.php";
        urlstatus = serverpath+"playlist.php?MODE=status";
        statusJson = serverpath+"playlist.php?MODE=statusjson";
    	urlcommand = serverpath+"playlist.php?";
    	urlplaylist = serverpath+"playlist.php?MODE=playlist";
        newplaylist = serverpath+"playlist.php?MODE=playlist";
        
        
        //getJson
		final HandleJSON myObj;
		myObj = new HandleJSON(statusJson);
		myObj.fetchJSON();
		while(myObj.parsingComplete);
		
  		
        
        if(mutestat==null){
        	mutestat=false;
        }
//          mv = _parseXml(serverpath+"mv.php");
//    	playlist.mvArray = mv;
//    	playlist.mvtotal = mv.size();
           
        btndone = (Button) findViewById(R.id.btndone);
        btndone.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("remote.java","done");
				//sendnewplaylist();
				//swapmode.setstatus("no");
				//chgRemote("control");
			}
		});
		   
        pausebtn = (ImageView) findViewById(R.id.rpause);
        pausebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cmdurl = urlcommand+"MODE=pl_pause";
				
				Log.d("remote.java cmdurl = ",cmdurl);
		    	
		    	try{
		    		final InputStream is1 = new URL(cmdurl).openStream();
		    	}catch(Exception e){
		    		
		    	}
				
			}
		});
		
        search = (ImageView) findViewById(R.id.rsearch);
        search.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(remote.this, search.class);
				startActivity(i);
				t.cancel();
				finish();
			}
		});
        
        mutebtn = (ImageView) findViewById(R.id.rmute);
        mutebtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub   
				if(mutestat==true){
					cmd("nomute","");
					mutestat = false;
				}else{
					cmd("mute","");
					mutestat = true;
				}
			}
		});
        
        ImageView replaybtn = (ImageView) findViewById(R.id.rreplay);
        replayV = replaybtn;
        replaybtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Log.d("remote.java","replay");
				// TODO Auto-generated method stub
		    	String cmdurl = urlcommand+"MODE=seek&val=0";
		    	try{
		    		final InputStream is1 = new URL(cmdurl).openStream();
		    	}catch(Exception e){
		    		
		    	}
			}
		});  
        
//        ImageView mv = (ImageView) findViewById(R.id.btnmv);
//        //replayV = replaybtn;
//        mv.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//		    	
//				showmv();
//				
//			}
//		}); 
        
        
        ImageView stopbtn = (ImageView) findViewById(R.id.rstop);
        stopV = stopbtn;
        stopbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("remote.java","Stop Clicked");
				if(data.size() > 0){ 
					String currentid = data.get(0).id;
					Log.d("currentid",currentid);
					cmd("delete", currentid);
					data.remove(0);
					items.remove(0);
					if(checkServer(mainUrl) == 200){
						_a.notifyDataSetChanged();
					}
					
					if((data.size() == 1)||(data.size() > 1)){
						String newId = data.get(0).id; // add by ton
						playlist.currentID = newId;
						String cmdurl = urlcommand+"MODE=pl_play&id="+playlist.currentID;
						checkServer(cmdurl);
					}
					
				}
			}
		});
        
        ImageView playbtn = (ImageView) findViewById(R.id.rplay);
        playV = playbtn;
        playbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("remote.java","playbtn Clicked");
				if(data.size() > 0){
					String currentid = data.get(0).id; // add by ton
					playlist.currentID = currentid; // add by ton
					if(checkServer(mainUrl) == 200){
						Log.d("remote getCurrent ID","");
						String cmdurl = urlcommand+"MODE=pl_play&id="+playlist.currentID;
						try{
				    		final InputStream is1 = new URL(cmdurl).openStream();
				    	}catch(Exception e){
				    		
				    	}
					}
				}
					
			}   
		});
        
        ImageView prevbtn = (ImageView) findViewById(R.id.rprev);
        prevV = prevbtn;
        prevbtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String cmdurl = urlcommand+"MODE=seek&val=-10%25";
				checkServer(cmdurl);
				/*
				if(playlist._index != 0){
					String cmdurl = urlcommand+"MODE=pl_previous";  
					Log.d("remote.java","prev Clicked");
					Log.d("remote.java cmdurl = ",cmdurl);
					int v1 = Integer.valueOf(playlist._index)-1;
					String v2 = data.get(v1).id;
			    	playlist.currentID = v2;
			    	playlist._index = v1;
			    	try{
			    		final InputStream is5 = new URL(cmdurl).openStream();
			    	}catch(Exception e){
			    		
			    	}   
				}*/
				
			}
		});
        
        ImageView nextbtn = (ImageView) findViewById(R.id.rnext);
        nextV = nextbtn;
        nextbtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cmdurl = urlcommand+"MODE=seek&val=%2B10%25";
				checkServer(cmdurl);
				 //(playlist._index != data.size()-1 &&  data.size() > 0)
				/*
				playlist._index = 0;
				Log.d("playlist._index = ",Integer.toString(playlist._index));
				if(playlist._index != data.size()-1 && data.size() > 0){
					String cmdurl = urlcommand+"MODE=pl_next";
					Log.d("remote.java","next Clicked");
					int v1 = Integer.valueOf(playlist._index)+1;
					String v2 = data.get(v1).id;
			    	//playlist.currentID = v2;
					//playlist.currentID = data.get(0).id;
			    	playlist._index = v1;
		    		data.remove(0);
		    		items.remove(0);
			    	
		    		if(checkServer(cmdurl) == 200){
			    		_a.notifyDataSetChanged();
			    	}
			    	
				}
				*/
				
			}
		});
        
         ImageView btnSwitchSound = (ImageView) findViewById(R.id.btnSwitchSound);
        	btnSwitchSound.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("remote.java btnSwitchSound","clicked");
					String cmdurl = null;
					int soundC = (playlist.soundChannel<2) ? ++playlist.soundChannel : (playlist.soundChannel=0);
					if(soundC==0){
						//100%,100%
						//cmdurl = "http://"+serverpath+"sound.php?R=100&L=100";
						cmdurl = serverpath+"sound.php?R=100&L=100";
						Log.d("remote.java cmdurl = ",cmdurl);
					}else if(soundC==1){
						//0%,100%
						//cmdurl = "http://"+serverpath+"sound.php?R=0&L=100";
						cmdurl = serverpath+"sound.php?R=0&L=100";
						Log.d("remote.java cmdurl = ",cmdurl);
					}else if(soundC==2){
						//100%,0%
						//cmdurl = "http://"+serverpath+"sound.php?R=100&L=0";
						cmdurl = serverpath+"sound.php?R=100&L=0";
						Log.d("remote.java cmdurl = ",cmdurl);
					}
					//Log.e("press",cmd);   
			    	
			    	try{
			    		final InputStream is5 = new URL(cmdurl).openStream();
			    	}catch(Exception e){
			    		
			    	}
				}
			});
         
        	volbar1 = (SeekBar) findViewById(R.id.volbar);
        	//setup first Volume level
        	String startVol = myObj.getvolume();
    		final int IntstartVol = Integer.parseInt(startVol);
    		
        	int volStart = (int) Math.ceil((IntstartVol*100)/255);
            volbar1.setProgress(volStart);
            Log.d("remote.java","Setup Seek Bar");
            volbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            
        
            int num = 255;
            int vol = 0;
                 
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				// TODO Auto-generated method stub
    				String cmdurl = urlcommand+"MODE=volume&val="+vol;
    				Log.d("remote.java Seekbar","onStopTrackingTouch");
    		    	try{
    		    		final InputStream is = new URL(cmdurl).openStream();
    		    	}catch(Exception e){
    		    		
    		    	}
    			}

    			public void onStartTrackingTouch(SeekBar seekBar) {
    				// TODO Auto-generated method stub
    				Log.d("remote.java Seekbar","onStartTrackingTouch");
    			}
    			
    			public void onProgressChanged(SeekBar seekBar, int progress,
    					boolean fromUser) {
    				// TODO Auto-generated method stub
    				Log.d("remote.java Seekbar","onProgressChanged");
    				//num = 500; comment by ton
    				num = 255;
    				vol = (int) Math.ceil((progress*255)/100);
    		    	Log.d("remote.java vol = ", Integer.toString(vol));
    			}
    		
    			
            });
        	
        
        	// first Create play list 
        	final DragNDropListView l = (DragNDropListView) findViewById(R.id.listView1); //comment by ton
        	_a = getPlayList(urlplaylist+"&clrcache="+Math.random());
	  		l.setDragNDropAdapter(_a);
	  		
	  		
	  		
          l.setOnItemClickListener(new OnItemClickListener(){ 	
			public void onItemClick(final AdapterView<?> arg0, final View arg1, final int arg2,
					long arg3) {
					selectedIndex = 0;
					selectedIndex = arg2;
					Log.d("selectedIndex = ",Integer.toString(selectedIndex));
					if(selectedIndex != 0){
							final CharSequence[] c = {"Play","Delete","Cancel"}; 
							AlertDialog.Builder alertB = new AlertDialog.Builder(remote.this);
							alertB.setTitle("Choose Action");
							alertB.setItems(c,new DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									String cmdurl = null;
									switch(which){
										case 0 : {
													cmdurl = urlcommand+"MODE=pl_play&id="+data.get(selectedIndex).id;
													tempforplayagain = data.get(selectedIndex);
													
													String currentid = tempforplayagain.id;
													final String deleteid = playlist.currentID;
													playlist._index = selectedIndex;
													Log.d("remote.java playlist._index = ",Integer.toString(playlist._index));
													playlist.currentID = currentid;
													Log.d("remote.java playlist.currentID = ",playlist.currentID);
													try{
														checkServer(cmdurl);
														_a.notifyDataSetInvalidated();
														Log.d("remote.java","####UPDATE#####");
											    	}catch(Exception e){
											    		
											    	}
													}break;
										case 1 : {
													Log.d("remote.java","Delete");
											   		cmdurl = urlcommand+"MODE=pl_delete&id="+data.get(selectedIndex).id;
											   		Log.d("remote.java cmdurl = ",cmdurl);
													String cmdurlplay = urlcommand+"MODE=pl_next";
													Log.d("remote.java cmdurlplay = ",cmdurlplay);
													data.remove(selectedIndex); 
													items.remove(selectedIndex);
													
													try{
											    		//final InputStream is = new URL(cmdurl).openStream();
											    		if(checkServer(cmdurl) == 200){
															_a.notifyDataSetChanged();
														}
											    	}catch(Exception e){
											    		
											    	}
													}break;
										case 2 : {
													Log.d("remote.java","Cancel");
													int testnum = arg2;
													Log.e("testnum", String.valueOf(testnum));
													if(checkServer(mainUrl) == 200){
														_a.notifyDataSetChanged();
													}
													}break;
									}
									
								}
								
							});
							AlertDialog alert = alertB.create();
							alert.show();
					
					}
				}
          });
         
          
          
          	
         
          
          t = new CountDownTimer( Long.MAX_VALUE , 5000) {
        	  String startID =  myObj.getcurrentplid();
	          public void onTick(long millisUntilFinished) {
	              	
	              	if("statusJson" != ""){
	              		Log.d("remote","##################Timer tick#######################");
	              		HandleJSON myObj;
		        		myObj = new HandleJSON(statusJson);
		        		myObj.fetchJSON();
		        		while(myObj.parsingComplete);
		        		String NowID =  myObj.getcurrentplid();
		        		
		        		//startID
		        		if(Integer.parseInt(NowID) != Integer.parseInt(startID)){
		        			Log.d("remote","################## UPDATE LIST #######################");
		        			_a = getPlayList(urlplaylist+"&clrcache="+Math.random());
		        	  		l.setDragNDropAdapter(_a);
		        	  		startID = NowID;
		        		}
	              	}else{
	              		Log.d("remote","##################No Timmer#######################");
	              	}
	              	
	              
	          }
	
	          public void onFinish() {
	              Log.d("remote","Timer last tick");            
	              start();
	          }
              
              
           }.start();
           
    }
    
    
    public void showmv(){
    	karaokeTimeManage.clear();
    	playlist.mvmode = true;
    	int total = playlist.total;
    	for(int i=0 ;i<total; ++i){
    		cmd("delete",playlist.getsong(i).id);
    		//playlist.deletesong(i);
    	}
    	for(int i=0 ;i<total; ++i){
    		//cmd("delete",playlist.getsong(i).id);
    		playlist.deletesong(0);
    	}
    	
    	Collections.shuffle(mv);
    	addmv(mv);
    	
    }
    
    public void addmv(List<Data> mv){
    	Log.d("remote.java","addmv");
    	for(int i=0 ;i<mv.size(); ++i){
    		//cmd("addnopath",mv.get(i).path);
    	}
    	//data = mv;
    	cmd("play2","");
    }
    
    
       
    private void updatecolorTxt(int a2,View a1, AdapterView a0, AlbumListAdapter aa){
    	
    	
    	txtViewColor.setTextColor(Color.parseColor("#FFFFFF"));
    	
    	View adap = aa.getView(a2, a1, a0);
		TextView _txtview =(TextView) adap.findViewById(R.id.txtsong);
		_txtview.setTextColor(Color.parseColor("#FF9900"));
		
		txtViewColor = _txtview;
    }
    
    private void chgRemote(String str){
    	if(str == "control"){
    		View vr = (View) findViewById(R.id.remotepanel);
    		vr.setVisibility(0);
		
    		View vs = (View) findViewById(R.id.remoteswap);
    		vs.setVisibility(4);
    	}else if(str == "swap"){
    		
    		View vr = (View) findViewById(R.id.remotepanel);
    		vr.setVisibility(4);
    		
    		View vs = (View) findViewById(R.id.remoteswap);
    		vs.setVisibility(0);
    		
    	}
    }

   
    private void cmd(String method, String url){
    	String cmdurl = null;
    
    	if(method == "mute"){
    		cmdurl = urlcommand+"MODE=volume&val=0";
    		volbar1.setProgress(0);
    	}
    	if(method == "nomute"){
    		cmdurl = urlcommand+"MODE=volume&val=250";
    		volbar1.setProgress(50);
    	} 
    	
    	if(method == "add"){
    		//cmdurl = urlcommand+"command=in_play&input="+pathtovideo+url;
    		cmdurl = urlcommand+"MODE=in_enqueue&id="+url;
    		
    	}
    	if(method == "addnopath"){
    		cmdurl = urlcommand+"MODE=in_enqueue&id="+url;
    	}
    	if(method == "revsort"){
    		cmdurl = urlcommand+"MODE=in_enqueue&id="+url;
    	}
    	if(method == "play"){
    		cmdurl = urlcommand+"MODE=pl_play&id="+data.get(Integer.valueOf(url)).id; 
    		playlist._index = Integer.valueOf(url);
    	}
    	if(method == "play2"){
    		cmdurl = urlcommand+"MODE=pl_play";
    	}
    	if(method == "run"){
    		cmdurl = urlcommand+"MODE=seek&val="+url;
    	}
    	if(method == "delete"){
    		cmdurl = urlcommand+"MODE=pl_delete&id="+url;
    	}
    	if(method == "seekfw"){
    		cmdurl = urlcommand+"MODE=seek&val=+10%";
    	}
    	if(method == "seekrw"){
    		cmdurl = urlcommand+"MODE=seek&val=-10%";
    	}
    	
    	try{
    		final InputStream is = new URL(cmdurl).openStream();
    	}catch(Exception e){
    		
    	}
    }
    
   
    public List<song> _parseXml(String u) {
    	 Log.d("remote.java","create playlist data");
		  //List<song> data = null; //comment by ton
		  URL url ;
		  try {
			url = new URL(u);
		    SAXParserFactory spf = SAXParserFactory.newInstance(); 
		    SAXParser sp = spf.newSAXParser(); 
		    XMLReader xr = sp.getXMLReader(); 
		    DataHandler dataHandler = new DataHandler(); 
		    xr.setContentHandler(dataHandler);
		    xr.parse(new InputSource(url.openStream()));
		    if(checkServer(mainUrl) == 200){
		    	data = dataHandler.getData();
		    }
		    
		  } catch(ParserConfigurationException pce) { 
		    Log.e("SAX XML", "sax parse error", pce); 
		  } catch(SAXException se) { 
		    data = new ArrayList<song>();
		  } catch(IOException ioe) { 
		    Log.e("SAX XML", "sax parse io error", ioe); 
		  } 
		  
		  //String checkData = data.toString();
		  //Log.d("remote.java checkData = ",checkData);
		  
		  return data; 
	} 
	
	public class AlbumListAdapter extends ArrayAdapter<Data> {
			
			AlbumListAdapter(Context c, int r, List l) {
				
				
				super(c,r,l);
				
			}
			
			@Override
	        public int getItemViewType(int position) {
	            return (position!=data.size()-1) ? 0 : 1;
	        }
			
			public View getView(int position, View convertView,ViewGroup parent) {
				View row=convertView;
				AlbumListWrapper wrapper=null;
				int type = getItemViewType(position);
				if (row==null) {													
					LayoutInflater inflater=getLayoutInflater();
					
					
						row=inflater.inflate(R.layout.row_moreresult, null);

					wrapper=new AlbumListWrapper(row);
					row.setTag(wrapper);
				}
				else {
					wrapper=(AlbumListWrapper)row.getTag();
				}
				
				TextView _t_title = (TextView)row.findViewById(R.id.txtsong);
				TextView _t_album = (TextView)row.findViewById(R.id.txtalbum);
				TextView _t_artist = (TextView)row.findViewById(R.id.txtartist);
				ImageView _img = (ImageView)row.findViewById(R.id.image);
				
				if(_t_title!=null){
					//Log.e("txt",""+position);
					Log.e("List######name",data.get(position).name);
					_t_title.setText(data.get(position).name);
				}
				if(_t_album!=null){
					//_t_album.setText(data.get(position).album);
				}
				if(_t_artist!=null){
					//_t_artist.setText(data.get(position).artist);
				}
			
				
				return(row);
			}
			class AlbumListWrapper {
				
				private TextView albumName =null;
				private View row=null;
				
				
				AlbumListWrapper(View row) {
					this.row=row;
				}
				
				void populateFrom(final Data albumList, int pos){
					
				}
			}
	}
	
	

	 public static Bitmap getBitmapFromURL(String src) {
	        try {
	            Log.e("src",src);
	            URL url = new URL(src);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoInput(true);
	            connection.connect();
	            InputStream input = connection.getInputStream();
	            Bitmap myBitmap = BitmapFactory.decodeStream(input);
	            Log.e("Bitmap","returned");
	            return myBitmap;
	        } catch (IOException e) {
	            e.printStackTrace();
	            Log.e("Exception",e.getMessage());
	            return null;
	        }
	 }
	 
	
	public class DataHandler extends DefaultHandler { 
		 
		  // booleans that check whether it's in a specific tag or not 
		  private boolean _inName, _inTime, _inUrl, _inId, _insong; 
		  
		  // this holds the data 
		  private song _data; 
		  private List<song> Datas; 
		  
		 
		  /** 
		   * Returns the data object 
		   * 
		   * @return 
		   */ 
		  public List<song> getData() { 
		    return Datas; 
		  } 
		 
		  /** 
		   * This gets called when the xml document is first opened 
		   * 
		   * @throws SAXException 
		   */ 
		  @Override 
		  public void startDocument() throws SAXException { 
		    Datas = new ArrayList<song>(); 
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
			  
			
			if(localName.equals("onesong")){
				_data = new song();
				String s = atts.getValue("id");
				
				_data.id = s;
				Log.d("remote.java onesong  _data.id = ",_data.id);
			    Datas.add(_data);
			    
				//_insong = true;
			}else if(localName.equals("id")){
				//Log.e("#### We're in ID node","Yes, we are.");
				_inId = true;
			}else if(localName.equals("name")){
				//Log.e("#### We're in name node","Yes, we are.");
				_inName = true;
			}else if(localName.equals("url")){
				//Log.e("#### We're in url node","Yes, we are.");
				_inUrl = true;
			}else if(localName.equals("playtime")){
				//Log.e("#### We're in playtime node","Yes, we are.");
				_inTime = true;
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
		    //Log.v("endElement", localName); 
		    if(localName.equals("onesong")){
				//_insong = false;
			}else if(localName.equals("id")){
				_inId = false;
			}else if(localName.equals("name")){
				_inName = false;
			}else if(localName.equals("url")){
				_inUrl = false;
			}else if(localName.equals("playtime")){
				_inTime = false;
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
		    
		    if(_insong){
		    	
			}else if(_inId){
				_data.id = chars.toString();
				//Log.e("##show all string id",_data.id);
			}else if(_inName){
				_data.name = chars.toString();
			}
 
		  } 
		  
		
		  
		}
	
	public class song { 
		  // I know this could be an int, but this is just to show you how it works 
		  
		  public String id="";
		  public int current= 0;
		  public String name = "";
		  //public String time="";
		  //public String path="";
		  
		 
		  public song() { 
		 
		  }
	}
	
	
    
	
	
   private void runcmd(String t){

    	String cmdurl = urlcommand+"MODE=seek&val="+t;
    	try{
    		final InputStream is = new URL(cmdurl).openStream();
    	}catch(Exception e){
    		
    	}
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
    
   //get playlist 
    
   
    
   
   
   // checkserver Status 
   public int checkServer(String getUrl){
	   int promt = 0;
	   try{
		   URL url = new URL(getUrl);
		   HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
		   urlcon.connect();
		   InputStream in = new BufferedInputStream(urlcon.getInputStream());
		   promt = urlcon.getResponseCode() ;
		   Log.d("remote.java promt = ",Integer.toString(promt));

	   }catch (MalformedURLException e1) {
		   
		   e1.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	   return promt;
   }
   
   public static void autoPlay(){
	  
			Log.d("remote getCurrent ID","");
			
			String cmdurl = urlcommand+"MODE=pl_play";
			
			try{
	    		final InputStream is1 = new URL(cmdurl).openStream();
	    	}catch(Exception e){
	    		
	    	}
		
   }
   
   // create playlist by ton
   public DragNDropSimpleAdapter getPlayList(String cmd){
	    // create playlist in remote
   		data = _parseXml(urlplaylist+"&clrcache="+Math.random());
 		items = new ArrayList<Map<String, Object>>();
 		for(int i = 0; i < data.size(); ++i) {
 			HashMap<String, Object> item = new HashMap<String, Object>();
 			item.put("name", data.get(i).name );
 			item.put("_id", data.get(i).id );
 			item.put("_current", data.get(i).current );
 			items.add(item);
 		}  
 		_a = new DragNDropSimpleAdapter(this.getApplicationContext(),
 				items, 
 				R.layout.row_remote,
 				new String[]{"name"},
 				new int[]{R.id.songname},
 				R.id.icondrag);
 		
	   return _a;
   }
   
   
   
   //check player status
    
 
 public class HandleJSON {
	public String currentplid = "currentplid";
	   public String time = "time";
	   public String length = "length";
	   public String volume = "volume";
	   public String urlString = null;
	   public volatile boolean parsingComplete = true;
	   public HandleJSON(String url){
		      this.urlString = url;
	   }
	   public String getcurrentplid(){
	      return currentplid;
	   }
	   public String gettime(){
		      return time;
		   }
	   public String getlength(){
		      return length;
		   }
	   public String getvolume(){
		      return volume;
		   }
	   
	   @SuppressLint("NewApi")
	   public void readAndParseJSON(String in) {
		   try {
		         JSONObject reader = new JSONObject(in);
		         currentplid = reader.getString("currentplid");
		         time = reader.getString("time");
		         length = reader.getString("length");
		         volume = reader.getString("volume");
		         parsingComplete = false;

		        } catch (Exception e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		        }
	   }
	   public void fetchJSON(){
		   Thread thread = new Thread(new Runnable(){
			  
			   @Override
		         public void run() {
		         try {
		            URL url = new URL(urlString);
		            Log.d("JSON URL = ",url.toString());
		            
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		           	//conn.setReadTimeout(10000 );//milliseconds
		            //conn.setConnectTimeout(15000);//milliseconds
		            //conn.setRequestMethod("GET");
		            //conn.setDoInput(true);
		            // Starts the query
		            conn.connect();
		         InputStream stream = conn.getInputStream();

		      String data = convertStreamToString(stream);

		      readAndParseJSON(data);
		         stream.close();

		         } catch (Exception e) {
		        	 e.printStackTrace();
		         	}
		         }
		   });
		   
		   thread.start(); 	
	   }
	   String convertStreamToString(java.io.InputStream is) {
		      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		      return s.hasNext() ? s.next() : "";
		   }
	   
   }
   
   
   
   
   
}
