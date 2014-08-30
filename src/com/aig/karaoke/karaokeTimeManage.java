package com.aig.karaoke;

import android.util.Log;

import com.aig.karaoke.*;

public class karaokeTimeManage {
	private static TimeInstance TI = null;  
	
	//private String Songname;
	
	   
	public karaokeTimeManage(){		
		
	}
	
	public static void buildTime(long i1, long i2, Boolean b){
		clear();
		Log.e("state","on buildtime");
		TI = new TimeInstance(i1,i2,b);		
		TI.create();
		
	} 
	
	public static void clear(){
		if(!isNull()){
			TI.cancel();       
			TI = null;  
		}
	}
	
	public static boolean isNull(){
		return (TI==null);
	}
	
	public static long getRemain(){
		return TI.timeLeft();
	}
	
	public static void setsongname(String str){
		TimeInstance.setname(str);
	}
	
	public static void setbasicpath(String ip){
		TimeInstance.setpath(ip);
	}
	
	public static String getidfordelete(){
		return TimeInstance.getidfordelete();
	}
	
	public static String getidfornext(){
		return TimeInstance.getidfornext();
	}
	
	public static void setidforDWF(String str){
		TimeInstance.setidfordeletewhenfinish(str);
	}
}
