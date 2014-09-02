package com.bluenightz.karaoke3;

import java.io.BufferedReader;
import com.terlici.*;
import com.terlici.dragndroplist.DragNDropListView;
import com.terlici.dragndroplist.DragNDropSimpleAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
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
import android.os.Handler;
import android.preference.PreferenceManager;
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
	//private String ip = "first-pc:8080";
	private String ip;
	private String host;
	//private String pathtovideo = "%2Fvar%2Fwww%2Fhtml%2Fsong%2F";
	//private String pathtovideo = "%2FhddExt%2FKARAOKESONG%2F";
	private String pathtovideo = "%2Fvar%2Fwww%2Fhtml%2FtoExternal%2FKARAOKESONG%2F";
	//private String pathtovideo ="file%3A%2F%2F%2FC%3A%2Fxampp%2Fhtdocs%2Fkaraoke%2Fsong%2F";
	//private String pathtovideo = "file%3A%2F%2F%2FC%3A%2Fxampp%2Fhtdocs%2Fkaraoke%2Fsong%2F";
	//private String pathtovideo = "file%3A%2F%2F%2FC%3A%2Fsong%2F"; //window c:
	//private String pathtovideo = "file%3A%2F%2F%2FApplications%2FMAMP%2Fhtdocs%2Fsong%2F";
	private static String urlcommand;
	//private swapmode sm = new swapmode();
	private String urlplaylist;
	private String serverpath;
	public ArrayList<String> sN;
	public ArrayList<String> sID;
	public ArrayList<String> sF;
 	public List<song> data;
 	private Boolean mutestat;
 	private ImageView mutebtn;
 	private SeekBar volbar1;
 	private String selectedID;
 	private int selectedIndex;
 	private SharedPreferences s;
 	private song tempsong1 = null;
 	private song tempsong2 = null;
 	//private int tempsong1 = 99999;
 	//private int tempsong2 = 99999;
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
 	//public static AlbumListAdapter _a = null;
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
 	
 	//private String cmdurl;
 	//private int currentid;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("remote.java","Start Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote);
        String _l = Lang.getLang();
        s = PreferenceManager.getDefaultSharedPreferences(this);
        ip = s.getString("ip", null);
        
        
//        ip = host+":8080";
//        urlstatus = "http://"+ip+"/requests/status.xml";
//    	urlcommand = "http://"+ip+"/requests/status.xml?";
//    	urlplaylist = "http://"+ip+"/requests/playlist.xml";
//    	serverpath = "http://"+host+"/karaoke/";
//        newplaylist = serverpath+"control.php";
        
        webPath = "/karaoke/";
        serverpath = "http://"+ip+"/karaoke/";
        urlstatus = serverpath+"playlist.php?MODE=status";
    	urlcommand = serverpath+"playlist.php?";
    	urlplaylist = serverpath+"playlist.php";
        newplaylist = serverpath+"control.php";
        
        
        Log.d("remote.java serverpath = ",serverpath);
        Log.d("remote.java urlstatus = ",urlstatus);
        Log.d("remote.java urlcommand = ",urlcommand);
        
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
				sendnewplaylist();
				swapmode.setstatus("no");
				chgRemote("control");
			}
		});
		   
        pausebtn = (ImageView) findViewById(R.id.rpause);
        pausebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String cmdurl = urlcommand+"MODE=pl_pause";
				Log.d("remote.java urlstatus = ",urlstatus);
		    	
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
				Log.d("remote.java urlstatus = ",urlstatus);
			}
		});
        
        mutebtn = (ImageView) findViewById(R.id.rmute);
        mutebtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Log.d("remote.java urlstatus = ",urlstatus);
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
				Log.d("remote.java urlstatus = ",urlstatus);
				// TODO Auto-generated method stub
		    	String cmdurl = urlcommand+"MODE=seek&val=0";
		    	Log.d("remote.java urlstatus = ",urlstatus);
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
			if(data.size() > 0){  
				
				cmd("play",String.valueOf(0));
				String _timestr = data.get(0).time;
				int _timeint = Integer.valueOf(_timestr)/1000-1500;

				karaokeTimeManage.clear();
				karaokeTimeManage.buildTime(_timeint, _timeint, true);
				
				String currentid = data.get(0).id;
				final String deleteid = playlist.currentID;
				
				tempforplayagain = data.get(0);
				data.remove(0);
				items.remove(0);

				
				
				_a.notifyDataSetChanged();
				playlist._index = 0;
				playlist.currentID = currentid;
				
				Handler handler = new Handler();
				handler.postDelayed(new Runnable(){
				@Override
				      public void run(){
						cmd("delete", deleteid);
				   }
				}, 1000);
				
			}
			}
		});
        

        
        ImageView playbtn = (ImageView) findViewById(R.id.rplay);
        playV = playbtn;
        playbtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 	
					String cmdurl = urlcommand+"MODE=pl_play&id="+playlist.currentID;
					
					//String _timestr = tempforplayagain.time; comment by ton
					//int _timeint = Integer.valueOf(_timestr)/1000-1500; comment by ton
					//Log.e("###show time ####",String.valueOf(_timeint));
					//karaokeTimeManage.clear();
					//karaokeTimeManage.buildTime(_timeint, _timeint, true);
					
		    	
		    	try{
		    		final InputStream is1 = new URL(cmdurl).openStream();
		    	}catch(Exception e){
		    		
		    	}
		    	
	    		
			}   
		});
        
        ImageView prevbtn = (ImageView) findViewById(R.id.rprev);
        prevV = prevbtn;
        prevbtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(playlist._index != 0){
					String cmdurl = urlcommand+"MODE=pl_previous";  
					int v1 = Integer.valueOf(playlist._index)-1;
					String v2 = data.get(v1).id;
			    	playlist.currentID = v2;
			    	playlist._index = v1;
			    	try{
			    		final InputStream is5 = new URL(cmdurl).openStream();
			    	}catch(Exception e){
			    		
			    	}   
				}
			}
		});
        
        ImageView nextbtn = (ImageView) findViewById(R.id.rnext);
        nextV = nextbtn;
        nextbtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(playlist._index != data.size()-1 && data.size() > 0){
					String cmdurl = urlcommand+"MODE=pl_next";
					int v1 = Integer.valueOf(playlist._index)+1;
					String v2 = data.get(v1).id;
			    	playlist.currentID = v2;
			    	playlist._index = v1;
			    	try{
			    		final InputStream is5 = new URL(cmdurl).openStream();
			    	}catch(Exception e){
			    		
			    	}
				}
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
						cmdurl = "http://"+serverpath+"sound.php?R=100&L=100";
						Log.d("remote.java cmdurl = ",cmdurl);
					}else if(soundC==1){
						//0%,100%
						cmdurl = "http://"+serverpath+"sound.php?R=0&L=100";
						Log.d("remote.java cmdurl = ",cmdurl);
					}else if(soundC==2){
						//100%,0%
						cmdurl = "http://"+serverpath+"sound.php?R=100&L=0";
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
        volbar1.setProgress(50);
        Log.d("remote.java","Setup Seek Bar");
        volbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        
    	 //int num = 500; comment by ton
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
		
        
          //final ListView l = (ListView) findViewById(R.id.listView1);
          data = _parseXml(urlplaylist);
          
          data = _parseXml(newplaylist);
          Log.d("remote.java","setup New PLaytlist Data");
          int firstremove = 0;
          Log.d("remote.java firstremove = ","Initial Value for firstremove");
	          for(int i = 0; i < data.size(); ++i){
	        	  if(data.get(i).id.equals(playlist.currentID)){
	        		  firstremove = i;
	        		  Log.d("remote.java","firstremove");
	        	  }
	        	  Log.d("remote.java firstremove = ","Finish Loop");
	          }
	      Log.d("remote.java firstremove = ","Exit from Loop"); 
          
	      // comment by ton disable for error after start activity
	      //tempforplayagain = data.get(firstremove);
	      //Log.d("remote.java tempforplayagain = ","Set Data for tempforplayagain");
          //data.remove(firstremove);
          //Log.d("remote.java remove(firstremove) = ","Set Data for remove(firstremove)");
          
          //playlist.updateplaylist(data);
          //Log.d("remote.java"," playlist.updateplaylist");
          // end comment by ton
          
          final DragNDropListView l = (DragNDropListView) findViewById(R.id.listView1);
	  		items = new ArrayList<Map<String, Object>>();
	  		for(int i = 0; i < data.size(); ++i) {
	  			HashMap<String, Object> item = new HashMap<String, Object>();
	  			item.put("name", data.get(i).name );
	  			item.put("_id", data.get(i).id );
	  			item.put("_current", data.get(i).current );
	  			//item.put("path", data.get(i).path );
	  			//item.put("time", data.get(i).time );
	
	  			items.add(item);
	  		}  
	  		  
	  		_a = new DragNDropSimpleAdapter(this.getApplicationContext(),
	  				items, 
	  				R.layout.row_remote,
	  				new String[]{"name"},
	  				new int[]{R.id.songname},
	  				R.id.icondrag);
	  		
	  		l.setDragNDropAdapter(_a);
	  		
	  		
	  		l.setOnItemDragNDropListener(new OnItemDragNDropListener(){

				@Override
				public void onItemDrag(DragNDropListView parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
				}
  
				@Override
				public void onItemDrop(DragNDropListView parent, View view,
						int startPosition, int endPosition, long id) {
					// TODO Auto-generated method stub

					
					Collections.swap(data, startPosition, endPosition);
					//Collections.swap(items, startPosition, endPosition);
					
				}  
	  			
	  		});

          
          
          /* /
          _a = new AlbumListAdapter(remote.this, R.layout.row_pic_layout, data);
          l.setAdapter(_a); 
          // */
          l.setOnItemClickListener(new OnItemClickListener(){
        
        	  
    		
			public void onItemClick(final AdapterView<?> arg0, final View arg1, final int arg2,
					long arg3) {
				//selectedID = sID.get(arg2); 	
				selectedIndex = arg2;
				      
    
				  
				
						final CharSequence[] c = {"Play","Delete","Cancel"}; 
						//final CharSequence[] c = {"Play","Delete","Cancel"}; 
						AlertDialog.Builder alertB = new AlertDialog.Builder(remote.this);
						alertB.setTitle("Choose Action");

						alertB.setItems(c,new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								String s = (String) c[which];
								   
								String cmdurl = null;
								if(s.equals("Play")){  
									cmd("play",String.valueOf(selectedIndex));
									
									for(int x = 0 ; x < data.size() ; ++x){
										if(data.get(selectedIndex).id.equals(items.get(x).get("_id"))){
											tempforplayagain = data.get(selectedIndex);
											data.remove(selectedIndex);
											items.remove(x);
											_a.notifyDataSetChanged();
											break;
										}
//										if( items.get(selectedIndex).get("_id").equals(data.get(x).id )){
//											tempforplayagain = data.get(x);
//											data.remove(x);
//											break;
//										}
									}
									
									String _timestr = tempforplayagain.time;
									int _timeint = Integer.valueOf(_timestr)/1000 - 1500;
									karaokeTimeManage.clear();
									karaokeTimeManage.buildTime(_timeint, _timeint, true);
									
									String currentid = tempforplayagain.id;
									final String deleteid = playlist.currentID;
									
									

//									String _timestr = data.get(selectedIndex).time;
//									int _timeint = Integer.valueOf(_timestr)/1000-1500;
//									Log.e("###show time ####",String.valueOf(_timeint));
//									karaokeTimeManage.clear();
//									karaokeTimeManage.buildTime(_timeint, _timeint, true);
//									
//									String currentid = data.get(selectedIndex).id;
//									final String deleteid = playlist.currentID;
//									
//									tempforplayagain = data.get(selectedIndex);
//									//data.remove(selectedIndex);
//									items.remove(selectedIndex);
//									
//									for(int x = 0 ; x < data.size() ; ++x){
//										if(data.get(x).id.equals()){
//											items.remove(x);
//											break;
//										}
//									}
									
									
									
									playlist._index = selectedIndex;
									Log.d("remote.java playlist._index = ",Integer.toString(playlist._index));
									playlist.currentID = currentid;
									Log.d("remote.java playlist.currentID = ",playlist.currentID);
									Handler handler = new Handler();
									handler.postDelayed(new Runnable(){
									@Override
									      public void run(){
											cmd("delete", deleteid);
									   }
									}, 1000);
									
								}else if(s.equals("Cancel")){
									Log.d("remote.java","Cancel");
									int testnum = arg2;
									Log.e("testnum", String.valueOf(testnum));
								}else if(s.equals("Delete")){
									Log.d("remote.java","Delete");
									if(playlist.currentID.toString().equals(String.valueOf(data.get(selectedIndex).id))){
										final String _cmdurl = urlcommand+"MODE=pl_delete&id="+data.get(selectedIndex).id;
										Log.d("remote.java _cmdurl = ",_cmdurl);
										String cmdurlplay = urlcommand+"MODE=pl_next";
										Log.d("remote.java _cmdurl = ",_cmdurl);
										try{
								    		final InputStream is = new URL(cmdurlplay).openStream();
								    	}catch(Exception e){
								    		
								    	}
										final Handler handler = new Handler();
										handler.postDelayed(new Runnable() {
										  @Override
										  public void run() {
										    //Do something after 100ms
											  if(data.size() > 0){
												data.remove(selectedIndex); 
												items.remove(selectedIndex);
												_a.notifyDataSetChanged();
												try{
											    		final InputStream is = new URL(_cmdurl).openStream();
											    		playlist.currentID = data.get(playlist._index).id;
											    		Log.d("remote.java playlist.currentID = ",playlist.currentID);
											    		song nextsong = data.get(playlist._index);
											    		String _timestr = String.valueOf(nextsong.time);
														int _timeint = Integer.valueOf(_timestr)/1000-1900;
														karaokeTimeManage.clear();
														karaokeTimeManage.buildTime(_timeint, _timeint, true);
										    	}catch(Exception e){
										     	}
											  }
						  				
										  }
										}, 400);  
										
									}else{
										
								   		cmdurl = urlcommand+"MODE=pl_delete&id="+data.get(selectedIndex).id;
								   		Log.d("remote.java cmdurl = ",cmdurl);
										String cmdurlplay = urlcommand+"MODE=pl_next";
										Log.d("remote.java cmdurlplay = ",cmdurlplay);
										data.remove(selectedIndex); 
										items.remove(selectedIndex);
										_a.notifyDataSetChanged();
										try{
								    		final InputStream is = new URL(cmdurl).openStream();
								    	}catch(Exception e){
								    		
								    	}
									}
							    	    
								}  
								
						    	
							}
							
							
						});
						AlertDialog alert = alertB.create();
						alert.show();
				
				
			}
        }
        );
        // */
             
        
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
    	updatelist();
    	
    	Collections.shuffle(mv);
    	addmv(mv);
    	
    }
    
    public void addmv(List<Data> mv){
    	for(int i=0 ;i<mv.size(); ++i){
    		//cmd("addnopath",mv.get(i).path);
    	}
    	//data = mv;
    	//updatelist();
    	cmd("play2","");
    }
    
    public static void updatelist(){
//    	if(_a!=null){
//    		_a.notifyDataSetChanged();
//    	}
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
    		cmdurl = urlcommand+"MODE=in_enqueue&id="+pathtovideo+url;
    		
    	}
    	if(method == "addnopath"){
    		cmdurl = urlcommand+"MODE=in_enqueue&id="+url;
    	}
    	if(method == "revsort"){
    		cmdurl = urlcommand+"MODE=in_enqueue&id="+pathtovideo+url;
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
    	
    	try{
    		final InputStream is = new URL(cmdurl).openStream();
    	}catch(Exception e){
    		
    	}
    }
    
    public List<song> _parseXml(String u) { 
		  List<song> data = null; 
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
		    data = new ArrayList<song>();
		  } catch(IOException ioe) { 
		    Log.e("SAX XML", "sax parse io error", ioe); 
		  } 
		 
		  return data; 
	} 
	
	public class AlbumListAdapter extends ArrayAdapter<Data> {
			
			AlbumListAdapter(Context c, int r, List l) {
				/*super(ParseXMLActivity.this,
						R.layout.row_albumlist,
						data);*/
				
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
				//Log.e("txt",""+type);
				if (row==null) {													
					LayoutInflater inflater=getLayoutInflater();
					
					//if(type!=1){
						//row=inflater.inflate(R.layout.row_pic_layout, null);
					//}else{
						row=inflater.inflate(R.layout.row_moreresult, null);
					//} 
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
				//if(false){
//				if(data.get(position).current!=false){
//					txtViewColor = _t_title;
//					_t_title.setTextColor(Color.parseColor("#FF9900"));
//					prevtext = _t_title;
//				}else{
//					_t_title.setTextColor(Color.parseColor("#FFFFFF"));
//				}
				
				
				
				
				
				
				//_rt.setText(data.get(position).area);
				/*
				row.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Log.e("txt","");
					}
				});
				*/
				
				
				//wrapper.populateFrom(data.get(position), position);
				
				return(row);
			}
			class AlbumListWrapper {
				
				private TextView albumName =null;
				private View row=null;
				//private ImageReceivedCallback imageRenderCallback = AlbumActivity.this;
				
				AlbumListWrapper(View row) {
					this.row=row;
				}
				
				void populateFrom(final Data albumList, int pos){
					/*
					row.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							String s = albumList.songid;
							// TODO Auto-generated method stub
							Intent i = new Intent(result.this,list.class);
							i.putExtra("songid", s);
							startActivity(i);;
						}
					});
					*/
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
			    Datas.add(_data);
				//_insong = true;
			}else if(localName.equals("id")){
				Log.e("#### We're in ID node","Yes, we are.");
				_inId = true;
			}else if(localName.equals("name")){
				Log.e("#### We're in name node","Yes, we are.");
				_inName = true;
			}else if(localName.equals("url")){
				Log.e("#### We're in url node","Yes, we are.");
				_inUrl = true;
			}else if(localName.equals("playtime")){
				Log.e("#### We're in playtime node","Yes, we are.");
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
		    Log.v("endElement", localName); 
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
				Log.e("##show all string id",_data.id);
			}else if(_inName){
				_data.name = chars.toString();
			}else if(_inUrl){
				_data.path = chars.toString();
			}else if(_inTime){
				_data.time = chars.toString();
			}
 
		  } 
		  
		
		  
		}
	
	public class song { 
		  // I know this could be an int, but this is just to show you how it works 
		  
		  public String id="";
		  public String current="";
		  public String time="";
		  public String path="";
		  public String name = "";
		 
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
    
    private void updateVlc(){
    	
//
//       	
//       	playlist temppl = new playlist();
//       	com.bluenightz.karaoke3.remote.song clonesong = temppl.new song();
//       	
//    	
//    	int a = playlist.currentID;
//    	int indexofcurrentID = 0;
//    	
//    	for(int i = 0; i < playlist.gettotal(); ++i){
//    		if(Integer.valueOf(playlist.getsong(i).id) == a){
//    			indexofcurrentID = i;
//    		}
//    	}
//    	
//    	for(int i = 0; i < playlist.gettotal(); ++i){
//    		if(i != indexofcurrentID){
//        		cmd("delete",playlist.getsong(i).id);
//    		}   
//    	}
//    	
//    	for(int i = 0; i < playlist.gettotal(); ++i){
//        	cmd("add",playlist.getsong(i).path);
//    	}
//    	
//    	song original = playlist.getsong(indexofcurrentID);
//       	
//       	clonesong.id = original.id;
//       	clonesong.current = original.current;
//       	clonesong.time = original.time;
//       	clonesong.name = original.name;
//       	clonesong.path = original.path;
//    	
//    	data.add(0, clonesong);
//    	playlist.insertnewplaylist(data);
//    	
//    	List<com.aig.karaoke.playlist.song> aaa = playlist.getplaylist();
//    	
//    	List<Data> LL = _parseXml(urlplaylist);
//    	
//    	playlist._index = indexofcurrentID+1;
//    	playlist.currentID = Integer.valueOf(LL.get(playlist._index).id);
//    	
//    	for(int i = 0; i < playlist.gettotal(); ++i){
//    		if(i!=0){
//    			playlist.getsong(i).id = LL.get(i).id;
//    		}
//    	}
    	
    	
    	
    	      
    	
    }
    
   private void sendnewplaylist(){
   	
	playlist.isswap = true;
	List<song> d = data;
	int indexafterswap = 0;
	for(int a = 0 ; a < d.size() ; ++a){
		String _id = d.get(a).id;
		if( _id.toString().equals(playlist.currentID.toString()) ){
			indexafterswap = a;
		}
	}
	playlist._index = indexafterswap;
	playlist.insertnewplaylist(d);
   	//playlist.insertnewplaylist(data);
   	
   	updateVlc();
   	   
   }
    
}
