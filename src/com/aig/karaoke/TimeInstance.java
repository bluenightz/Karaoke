package com.aig.karaoke;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.util.Log;     

import com.bluenightz.karaoke3.remote;
import com.cycleindex.multitimer.CountDownTimerWithPause;
import com.aig.karaoke.*;
import com.aig.karaoke.mvClass.Data;
import com.aig.karaoke.playlist.song;

public class TimeInstance extends CountDownTimerWithPause{
	private static String songname = ""; 
	private static String urlcommand;
	private static String idfordelete = "";
	private static String idfornext;
	private static String idfordeletewhenfinish = "";
	//private static int cursorposition = -1;  
	 
	public TimeInstance(long millisOnTimer, long countDownInterval,
			boolean runAtStart) {
		super(millisOnTimer, countDownInterval, runAtStart);
		// TODO Auto-generated constructor stub

		Log.e("state","on TimeInstance");
	}
         
	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		
	}   
   
	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		// jump to next song and remove prev song from playlist
		ArrayList<com.aig.karaoke.playlist.song> _p = playlist.pl;
		com.aig.karaoke.playlist.song nextsong;
		String nextid;
		String _timestr = "";
		int _timeint;
		try{
			 nextsong = _p.get(playlist._index+1);
			 nextid = nextsong.id;
			_timestr = String.valueOf(nextsong.time);
			_timeint = Integer.valueOf(_timestr)/1000-1500;
			karaokeTimeManage.clear();
			karaokeTimeManage.buildTime(_timeint, _timeint, true);
			cmd("play",String.valueOf(nextid));
			playlist._index = playlist._index+1;
			playlist.currentID = nextid;
		}catch(Exception e){
			
			try{
				 nextsong = _p.get(0);
				 nextid = nextsong.id;
				_timestr = String.valueOf(nextsong.time);
				_timeint = Integer.valueOf(_timestr)/1000-1500;
				karaokeTimeManage.clear();
				karaokeTimeManage.buildTime(_timeint, _timeint, true);
				cmd("play",String.valueOf(nextid));
				playlist._index = 0;
				playlist.currentID = nextid;
			}catch(Exception e1){
				
			}
		}
		
		
		
		


	}
	
	public static String getidfordelete(){
		return idfordelete;
	}
	
	public static void setidfordelete(String str){
		idfordelete = str;
	}
	
	public static String getidfornext(){
		return idfornext;
	}
	
	public static void setidfornext(String str){
		idfornext = str;
	}   
	
	public static void setidfordeletewhenfinish(String str){
		idfordeletewhenfinish = str;
	}
	
	public static void setpath(String ip){
		urlcommand = "http://"+ip+"/requests/status.xml?";
		deleteDelay.setpath(urlcommand);
	}
	
	private void deletesong(String id){   
		cmd("delete", id);
		Log.e("txt","delete prev song");
	}
	
	public static void setname(String str){
		songname = str;
	}
	
	
	private void cmd(String method, String url){
    	String cmdurl = null;
    	if(method == "next"){
    		cmdurl = urlcommand+"command=pl_next";
    	}else if(method == "pause"){
    		cmdurl = urlcommand+"command=pl_forcepause";
    	}else if(method == "play"){
    		cmdurl = urlcommand+"command=pl_play&id="+url;
    	}else if(method == "delete"){
    		cmdurl = urlcommand+"command=pl_delete&id="+url;
    	}
    	
    	try{
    		final InputStream is = new URL(cmdurl).openStream();
    	}catch(Exception e){
    		
    	}
    }

}
