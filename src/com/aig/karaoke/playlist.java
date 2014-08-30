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
	}
	
	
	public static void insertnewplaylist(List<com.bluenightz.karaoke3.remote.song> p){
		ArrayList<song> _p = new ArrayList<song>();
		for(int a = 0 ; a < p.size() ; ++a){
			playlist ppp = new playlist();
			song __s = ppp.new song();
			__s.id = p.get(a).id;
			__s.name = p.get(a).name;
			__s.time = Integer.valueOf(p.get(a).time);
			__s.path = p.get(a).path;
			_p.add(__s);
		}
		pl = _p;
	}
	
	public static boolean isplaylistnull(){
		boolean b = (pl==null)?true:false;
		return b;
	}
	
	public static int findindexfromid(String id){
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
	}
	
	public static void buildplaylist(){
		pl = new ArrayList<song>();
	}
	
	public static ArrayList<song> getplaylist(){
		ArrayList<song> _pl = pl;
		return _pl;
	}
	
	public static void addsong(song _s){
		pl.add(_s);
	}
	
	public static void addsongat(int i, song s){
		pl.add(i, s);
	}
	
	public static void setcurrentID(String i){
		currentID = i;
	}
	
	public static song getnowsong(){
		song s = getsong(Integer.valueOf(currentID));
		return s;
	}
	
	public static song getsong(int i){
		song s = null;
		try{
			s = pl.get(i);
		}catch(Exception e){
			
		}
		return s;
	}
	
	public static void deletesong(int i){
		pl.remove(i);
	}
	
	public static void clearplaylist(){
		pl.clear();
	}
	
	public static void updateplaylist(List<com.bluenightz.karaoke3.remote.song> data){
		//List<song> List = new ArrayList<song>();
		pl.clear();
		for(int i = 0 ; i < data.size()-1 ; ++i){
			remote rr = new remote();
			com.bluenightz.karaoke3.remote.song _s = rr.new song();
			_s.id = data.get(i).id;
			_s.name = data.get(i).name;
			_s.path = data.get(i).path;
			_s.time = data.get(i).time;
		}
	}
	 
	public static int gettotal(){
		total = pl.size();
		return total;
	}
	
	public class song{
		public  String name;
		public  String id;
		public  long time;
		public  String path;
		public  Boolean current;
		
		public  String getname(){
			return name;
		}
		public  String getid(){
			return id;
		}
		public  long gettime(){
			return time;
		}
		public  String getpath(){
			return path;
		}
	}
	
  
}
