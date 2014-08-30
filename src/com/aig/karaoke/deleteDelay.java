package com.aig.karaoke;

import java.io.InputStream;
import java.net.URL;

import android.os.Handler;
import android.util.Log;

public class deleteDelay {
	private static String urlcommand;   
	private static Handler globalH;
	
	public static void deletesong(final String id){
		final Handler h = new Handler();
		
		Runnable r = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				
				Log.e("iddd",String.valueOf(id));
				cmd(id);
				//play("");
			}
			
		};   
		
    
		h.postDelayed(r, 100);  
	}
	
	public static void deletesong300(final String id){
		final Handler h = new Handler();
		
		Runnable r = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				
				Log.e("iddd",String.valueOf(id));
				cmd(id);
				//play("");
			}
			
		};     
		

		h.postDelayed(r, 00);  
	}
	
	public static void delayplay(final String id){
		final Handler h = new Handler();
		
		Runnable r = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				String cmdurl = null;
				cmdurl = urlcommand+"MODE=pl_play&id="+id;  
		    	try{
		    		final InputStream is = new URL(cmdurl).openStream();
		    	}catch(Exception e){
		    		   
		    	}
			}
			
		};   
		

		h.postDelayed(r, 50);  
	}
	
	public static void delayaction(Runnable _r,int sec){
		globalH = new Handler();
		
		Runnable r = _r;
		

		globalH.postDelayed(r, sec); 
		//globalH.removeCallbacks(r);
	}
	
	private static void cmd(String url){
		//int _indexfromid = playlist.findindexfromid(url);
    	//Log.e("find index",String.valueOf(playlist.findindexfromid(url)));   
    	//playlist.deletesong(_indexfromid);
    	String cmdurl = null;
    	//playlist.findindexfromid(url);
    	cmdurl = urlcommand+"MODE=pl_delete&id="+url;    
    	try{
    		final InputStream is = new URL(cmdurl).openStream();
    	}catch(Exception e){
    		   
    	}
		//long timestr = playlist.getsong(playlist.gettotal()-1).gettime();
		//karaokeTimeManage.buildTime(timestr, timestr, true);
		//karaokeTimeManage.setidforDWF(playlist.getsong(playlist.gettotal()-1).getid()); 
    }
	
	private static void play(String url){

    	String cmdurl = urlcommand+"MODE=pl_next";
    	
		try{
    		final InputStream is = new URL(cmdurl).openStream();
    	}catch(Exception e){
    		
    	}
	}
	
	public static void setpath(String p){
		urlcommand = p;
	}
}
