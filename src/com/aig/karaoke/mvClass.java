package com.aig.karaoke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import com.bluenightz.karaoke3.result.Data;
import com.bluenightz.karaoke3.result.DataHandler;

import android.net.Uri;
import android.util.Log;

public class mvClass {
	
	public static String serverpath;
	public static String vlcpath;
	public static String playlistpath;
	public static List<Data> allMV;
	public static String commandpath;
	public static String pathtovideo;
	public static boolean mvmode = false;
	
	public static void setpath(String ip){
		serverpath = "http://"+ip+"/karaoke/";
		vlcpath = "http://"+ip+":8080/";
		playlistpath = vlcpath+"requests/playlist.xml";
		commandpath = vlcpath+"requests/status.xml";
		//pathtovideo = "%2Fvar%2Fwww%2Fhtml%2Fsong%2F";
		pathtovideo = "%2FhddExt%2FKARAOKESONG%2F";
	}
	
	
	public static List<Data> getAllMV(){
		return allMV;
	}
	
	public static void loadsong(){
		//String _urlSearch = serverpath+"webservice.php?type=search&mode="+modestr+"&name="+searchstr+"&page="+page+"&clrcache="+Math.random();
		
		String _urlSearch = serverpath+"webservice.php?type=search&mode=mv&page=all&clrcache="+Math.random();
		allMV = _parseXml(_urlSearch);
		Collections.shuffle(allMV);
		
		for(int i = 0 ; i<allMV.size() ; ++i){
			try{
				if(i==0){
					cmd("addAndPlay",allMV.get(i).songurl);
				}else{
					cmd("add",allMV.get(i).songurl);
				}
			}catch(Exception e){
				
			}
		}  
		
		playlist.mvmode = true;
	}
	    
	public static void setPropertyTime(){
		
	}  
	
	private static void cmd(String method, String url){
		String encodedurl="";
        try {
            encodedurl = URLEncoder.encode(url,"UTF-8");
            Log.e("TEST", encodedurl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
		if(method == "add"){
			String cmdurl = commandpath+"?command=in_enqueue&input="+pathtovideo+encodedurl;
			
			
			
		    Log.e("txt",cmdurl);
			HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost(cmdurl);
		    try {
		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
	   
		        HttpEntity ht = response.getEntity();
	
		            BufferedHttpEntity buf = new BufferedHttpEntity(ht);
	
		            InputStream is = buf.getContent();
	
		            BufferedReader r = new BufferedReader(new InputStreamReader(is));
	
		            StringBuilder total = new StringBuilder();
		            String line;
		            while ((line = r.readLine()) != null) {
		                total.append(line);
		            }
	
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		}else if(method == "addAndPlay"){
			String cmdurl = commandpath+"?command=in_play&input="+pathtovideo+encodedurl;
			
			
			
		    Log.e("txt",cmdurl);
			HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost(cmdurl);
		    try {
		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
	
		        HttpEntity ht = response.getEntity();
	
		            BufferedHttpEntity buf = new BufferedHttpEntity(ht);
	
		            InputStream is = buf.getContent();
	
		            BufferedReader r = new BufferedReader(new InputStreamReader(is));
	
		            StringBuilder total = new StringBuilder();
		            String line;
		            while ((line = r.readLine()) != null) {
		                total.append(line);
		            }
	
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		}
	}
	
	public static List<Data> _parseXml(String u) { 
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
	
	public static class DataHandler extends DefaultHandler { 
		 
		  // booleans that check whether it's in a specific tag or not 
		  private boolean _inTitle, _inArtist, _inAlbum, _songid, _inleaf; 
		 
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
			  
			if(localName.equals("onesong")){
			  _data = new Data();
		      String s = atts.getValue("id");
		      _data.songid = s;
		      _data.songurl = atts.getValue("songurl");
		      String _picurl = atts.getValue("picurl");
		      _data.picurl = _picurl;
		      
			} else if(localName.equals("title")) { 
		      _inTitle = true; 
		    } else if(localName.equals("artist")) { 
		      _inArtist = true; 
		    } else if(localName.equals("album")) { 
			  _inAlbum = true; 
			} else if(localName.equals("leaf")){
			  _data = new Data();
		      Datas.add(_data);
			  _data.id = atts.getValue("id");
			  _data.time = atts.getValue("duration");
			  _inleaf = true;
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
		 
		    if(localName.equals("title")) { 
		      _inTitle = false; 
		    } else if(localName.equals("artist")) { 
		      _inArtist = false; 
		    } else if(localName.equals("album")) { 
			  _inAlbum = false; 
			}else if(localName.equals("onesong")){
		      Datas.add(_data);
		    }else if(localName.equals("leaf")){
		      //Datas.add(_data);
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
		 
		    if(_inTitle) { 
		      _data.title = chars.toString(); 
		    } else if(_inArtist) { 
		      _data.artist = chars.toString(); 
		    } else if(_inAlbum){
		      _data.album = chars.toString();
		    }
		  } 
		  
		
		  
	}
	
	
	public static class Data { 
		  // I know this could be an int, but this is just to show you how it works 
		  public String title = ""; 
		  public String artist = ""; 
		  public String album = ""; 
		  public String songid = "";
		  public String picurl = "";
		  public String songurl= "";
		  public String id="";
		  public String time="";
		 
		  public Data() { 
		 
		  }
	}
	
	
}
