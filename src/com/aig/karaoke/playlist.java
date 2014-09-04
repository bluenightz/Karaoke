package com.aig.karaoke;


import java.util.ArrayList;
import java.util.List;

import com.bluenightz.karaoke3.remote;




import android.util.Log;

public class playlist {
	public static ArrayList<song> pl = null;
	public static String currentID = null;
	public static String nextID = null;
	public static int _index = -1;
	public static song nowsong;
	public static int total = 0;
	public static boolean isswap = false;
	public static int soundChannel = 0;
	public static boolean mvmode = false;
	public static int mvtotal = 0;
	public static List<song> mvArray;
	public static long timetemp;
//	public static List<com.bluenightz.karaoke3.result.Data> listtemp;
	public static song tempsongadd;
	public static Boolean isPlaying = false;
	public static playlist thisclass;

	
	public playlist(){
		//thisclass = this;
		Log.d("playlist.java","Into this class");
	}
	
	
	public static void insertnewplaylist(List<com.bluenightz.karaoke3.remote.song> p){
		Log.d("playlist.java","insertnewplaylist");
		ArrayList<song> _p = new ArrayList<song>();
		if(_p != null){Log.d("playlist.java","_n not null");}
		for(int a = 0 ; a < p.size() ; ++a){
			playlist ppp = new playlist();
			song __s = ppp.new song();
			__s.id = p.get(a).id;
			Log.d("playlist.java __s.id = ",__s.id);
			__s.name = p.get(a).name;
			_p.add(__s);
		}
		Log.d("playlist.java","pl");
		pl = _p;
		if(pl == null){
			Log.d("playlist.java pl =","null");
		}
	}
	
	
	public static boolean isplaylistnull(){
		Log.d("playlist.java","isplaylistnull");
		boolean b = (pl==null)?true:false;
		return b;
	}
	
	public static int findindexfromid(String id){
		Log.d("playlist.java","findindexfromid");
		int index = 0;
			for(int a = 0; a<pl.size() ;++a){
				try{
					if(pl.get(a).id == id){
						index = pl.indexOf(pl.get(a));
						break;
					}
				}catch(Exception e){
					Log.e("###error###", String.valueOf(e));
				}
			}
		
		return index;
	}
	
	public static void setindex(int i){
		_index = i;
		Log.d("playlist.java","setindex");
	}
	
	public static void buildplaylist(){
		Log.d("playlist.java","buildplaylist");
		pl = new ArrayList<song>();
	}
	
	public static ArrayList<song> getplaylist(){
		Log.d("playlist.java","getplaylist");
		ArrayList<song> _pl = pl;
		return _pl;
	}
	
	public static void addsong(song _s){
		Log.d("playlist.java","addsong");
		pl.add(_s);
	}
	
	public static void addsongat(int i, song s){
		Log.d("playlist.java","addsongat");
		pl.add(i, s);
	}
	
	public static void setcurrentID(String i){
		Log.d("playlist.java","setcurrentID");
		currentID = i;
		Log.d("playlist.java",currentID);
		
	}
	
	public static song getnowsong(){
		Log.d("playlist.java","getnowsong");
		song s = getsong(Integer.valueOf(currentID));
		Log.d("playlist.java s = ",s.toString());
		return s;
	}
	
	public static song getsong(int i){
		Log.d("playlist.java","getsong");
		song s = null;
		try{
			s = pl.get(i);
			
		}catch(Exception e){
			
		}
		Log.d("playlist.java s = ",s.toString());
		return s;
	}
	
	public static void deletesong(int i){
		Log.d("playlist.java","deletesong");
		pl.remove(i);
	}
	
	public static void clearplaylist(){
		Log.d("playlist.java","clearplaylist");
		pl.clear();
	}
	
	public static void updateplaylist(List<com.bluenightz.karaoke3.remote.song> data){
		Log.d("playlist.java","updateplaylist");
		pl.clear(); // comment by ton
		for(int i = 0 ; i < data.size()-1 ; ++i){
			remote rr = new remote();
			com.bluenightz.karaoke3.remote.song _s = rr.new song();
			_s.id = data.get(i).id;
			_s.name = data.get(i).name;
		}
		
	}
	
	 
	public static int gettotal(){
		Log.d("playlist.java","gettotal");
		total = pl.size();
		Log.d("playlist.java total = ",Integer.toString(total));
		return total;
	}
	
	public class song{
		public  String name;
		public  String id;
		
		public  int current;
		
		public  String getname(){
			return name;
		}
		public  String getid(){
			Log.d("playlist.java getid()",id);
			return id;
		}
		public  int getCurrent(){
			Log.d("playlist.java getCurrent()",Integer.toString(current));
			return current;
		}
		
		
		/*
		public  long gettime(){
			return time;
		}
		public  String getpath(){
			return path;
		}
		*/
		
	}
	
  
}
