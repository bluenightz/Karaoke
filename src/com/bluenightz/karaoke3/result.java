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
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
*/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI; 
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog.Builder;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.aig.karaoke.deleteDelay;
import com.aig.karaoke.karaokeTimeManage;
import com.aig.karaoke.playlist;
import com.aig.karaoke.playlist.song;
import com.bluenightz.karaoke3.R;
import com.fedorvlasov.lazylist.ImageLoader;







import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.aig.karaoke.playlist;
//import com.

public class result extends Activity{
    /** Called when the activity is first created. */

	//static String _urlSearch = "http://bluenightz/karaokeServer/searchsongwithpic.xml?clrcache="+Math.random();
	private String _urlSearch;
	private String modestr;
	private String searchstr;
	private int page=1; 
	private ImageView remote;
	private List<Data> datamore;
	private String APP_SD_PATH;
	private static songurlobject _songurlobject;
	
	private String ip;
	
	//private String pathtovideo = "%2Fmedia%2FJP-SERVER 3TB%2FKARAOKESONG%2FTHAI_MODERN_SONG%2F";// 3TB
	//private String pathtovideo = "%2FhddExt%2FKARAOKESONG%2F";
	//private String pathtovideo = "%2Fvar%2Fwww%2Fhtml%2FtoExternal%2FKARAOKESONG%2F";
	//private String pathtovideo = "%2Fvar%2Fwww%2Fhtml%2Fsong%2F";//linux
	//private String pathtovideo ="file%3A%2F%2F%2FC%3A%2Fxampp%2Fhtdocs%2Fkaraoke%2Fsong%2F"; // c://xampp/htdoc/karaoke/song/
	//private String pathtovideo = "file%3A%2F%2F%2FC%3A%2Fsong%2F"; //window c: // c://song/
	//private String pathtovideo = "file%3A%2F%2F%2FApplications%2FMAMP%2Fhtdocs%2Fsong%2F"; //mac
	private String urlcommand;
	private static String serverpath;
	private String urlplaylist;
	private String directpath;
	private static String directpath2;
	private AlbumListAdapter _a;
	public List<Data> data;
	private String webPath;
	
	
	public ArrayList<String> sN;
	public ArrayList<String> sID;
	public ArrayList<String> sF;
	public List<Data> listxml;
	public ImageLoader imageLoader;
	private ListView l22;
	private String newplaylist;

	private SharedPreferences s;
	
	//public int currentid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        Log.d("result.java","Start Activity");
        if(extras !=null) {
	        searchstr = extras.getString("searchstr").toString();
	        modestr = extras.getString("modestr").toString();
	        
	        
	        if(extras.getStringArrayList("sN")!=null && extras.getStringArrayList("sN").size()>0){
		        sN = extras.getStringArrayList("sN");
		        sID = extras.getStringArrayList("sID");
		        sF = extras.getStringArrayList("sF");
	        }
        }
        
  
        
        s = PreferenceManager.getDefaultSharedPreferences(this);
        
//        host = s.getString("ip", null);
//        ip = host+":8080";
//        urlcommand = "http://"+ip+"/requests/status.xml?";
//    	serverpath = "http://"+host+"/karaoke/";
//    	urlplaylist = "http://"+ip+"/requests/playlist.xml";
//    	directpath = "http://"+host+"/song/";
//    	directpath2 = "http://"+host+"/toExternal/KARAOKESONG/"; 
//    	newplaylist = serverpath+"control.php";
        
        ip = s.getString("ip", null);
        webPath = "/karaoke/";
        serverpath = "http://"+ip+webPath;
        urlcommand = serverpath+"playlist.php?";
    	urlplaylist =serverpath+"playlist.php";
    	newplaylist = serverpath+"control.php";
    	
    	
    	
		_urlSearch = String.format(serverpath+"webservice.php?type=search&mode="+modestr+"&name=%s&page="+page+"&clrcache="+Math.random(), Uri.encode(searchstr));
        
        Log.e("txt",_urlSearch); 
         
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        imageLoader=new ImageLoader(this.getApplicationContext());
        
        remote = (ImageView) findViewById(R.id.remote);
        remote.setOnClickListener(new View.OnClickListener() {
		

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//if(playlist.gettotal()!=0){
				int total = playlist.pl.size();
				//if(total != 0){
				if(true){
					Intent i = new Intent(result.this, remote.class);
					//i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(i);
				}else{
					Context context = getApplicationContext();
					CharSequence text = "Can't Show Playlist, Please add song before try playlist mode.";
					int duration = Toast.LENGTH_SHORT;
					
					Toast toast = Toast.makeText(context, text, duration);
					
					toast.show();
				}
			}
		});
        
        
        
        
        
        Log.e("txt",_urlSearch);
        final ListView l = (ListView) findViewById(R.id.listView1);
        
        l22 = (ListView) findViewById(R.id.listView1);
        
        data = _parseXml(_urlSearch); 
          
        Data more = new Data();
        more.title = "No more result...";
        if(data.size()==20){
        	data.add(more);
        }  
          
        
        _a = new AlbumListAdapter(result.this, R.layout.row_pic_layout, data);
        l.setAdapter(_a);
        
        l.setOnItemClickListener(new OnItemClickListener(){

		
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				final CharSequence[] c = {"Add to Queue","Play","Cancel"};
				final CharSequence[] c2 = {"File not Found!!"};
				AlertDialog.Builder alertB = new AlertDialog.Builder(result.this);
				alertB.setTitle("Choose Action");
				
				
				if(arg2 == l.getCount()-1 && l.getCount() > 20 ){
					++page;
					 
					int m = l.getCount();
					data.remove(m-1);
					
					_urlSearch = serverpath+"webservice.php?type=search&mode="+modestr+"&name="+searchstr+"&page="+page+"&clrcache="+Math.random();
					
					
					datamore = _parseXml(_urlSearch);
					
					data.addAll(datamore);
					
						
					Data more = new Data();
			        more.title = "No more result...";
			        data.add(more);
						
						
					_a.notifyDataSetChanged();
					
					l.smoothScrollToPosition(m);
					
					
					_a.notifyDataSetChanged();
				}else{
//					String urlexist = directpath+data.get(arg2).songurl;
//					String urlexist2 = directpath2+data.get(arg2).songurl;
					
					if(exists(data.get(arg2))){    

				    	Log.e("show newplaylist === ", newplaylist); 
						alertB.setItems(c,new DialogInterface.OnClickListener() {
						
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							
							List<Data> List = _parseXml(newplaylist); 
							String s = (List.size()==0)?"Play":(String) c[which];

							if(playlist.mvmode == true){
								cmd("empty","");
								playlist.mvmode=false;  
							} 
							if(s.equals("Add to Queue")){
								
								final String file = urlencode(_songurlobject.url);
								Log.d("result.java file = ",file);
								cmd("add",file);
								List = _parseXml(newplaylist);
								Data ss = List.get(List.size()-1);
								song _s = new playlist().new song();
								_s.id = ss.id;
								_s.path = ss.path;
								_s.time = Integer.valueOf(ss.time);
								playlist.addsong(_s);
								
							}else if(s.equals("Play")){  
								final String file = urlencode(_songurlobject.url);
									//if(playlist.gettotal() == 0){
										cmd("play",file);
										List = _parseXml(newplaylist);
										Data ss = List.get(List.size()-1);
										song _s = new playlist().new song();
										_s.id = ss.id;
										_s.path = ss.path;   
										_s.time = Integer.valueOf(ss.time);
										playlist.addsong(_s);
										playlist._index = 0;
										playlist.currentID = ss.id;
										String _timestr = ss.time;
										int _timeint = Integer.valueOf(_timestr)/1000-1500;
										Log.e("###show time ####",String.valueOf(_timeint));
										
								
								
							}else if(s.equals("Cancel")){
								
								Log.e("to cancel","");
							}
							
							
						}
					});
					AlertDialog alert = alertB.create();
					alert.show();
					
					
					}else{
						alertB.setTitle("Alert Message");
						alertB.setItems(c2,new DialogInterface.OnClickListener() {
							
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
								 
							}
						});
						AlertDialog alert = alertB.create();
						alert.show();
						
					}
					/**/
					
				}
			}
        });
        
        
        
        
        ImageView _home2 = (ImageView) findViewById(R.id.home2);
	       _home2.setOnClickListener(new View.OnClickListener() {
			
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//_changepage("search");

					Intent i = new Intent(result.this, search.class);
					i.putStringArrayListExtra("sN", sN);
					i.putStringArrayListExtra("sID", sID);
					i.putStringArrayListExtra("sF", sF);
					startActivity(i);
				}
			});
	    
     
       
    }
    
    private void cmd(String method, String url){
    	String cmdurl = null;
    	if(method == "empty"){
    		cmdurl = urlcommand+"MODE=pl_empty";
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
    	if(method == "play"){
    		cmdurl = urlcommand+"MODE=in_play&id="+url;
    		Log.d("result.java cmd play cmdurl = ",cmdurl);
    		HttpClient httpclient = new DefaultHttpClient();
    	    HttpPost httppost = new HttpPost(cmdurl);
    	    try {
    	        // Execute HTTP Post Request
    	        HttpResponse response = httpclient.execute(httppost);

//    	        HttpEntity ht = response.getEntity();
//
//    	            BufferedHttpEntity buf = new BufferedHttpEntity(ht);
//
//    	            InputStream is = buf.getContent(); 
//
//    	            BufferedReader r = new BufferedReader(new InputStreamReader(is));
//
//    	            StringBuilder total = new StringBuilder();
//    	            String line;
//    	            while ((line = r.readLine()) != null) {
//    	                total.append(line);
//    	            }

    	    } catch (ClientProtocolException e) {
    	        // TODO Auto-generated catch block
    	    } catch (IOException e) {
    	        // TODO Auto-generated catch block
    	        e.printStackTrace();
    	    }
    	    
    	    
    	}else if(method == "delete"){
    	
    		cmdurl = urlcommand+"MODE=pl_delete&id="+url;

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
    	}else if(method == "add"){
    		
    		cmdurl = urlcommand+"MODE=in_enqueue&id="+url;

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
    	}else if(method == "trick"){
    		cmdurl = urlcommand+"MODE=pl_play";

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
    	}else if(method == "stop"){
    		cmdurl = urlcommand+"MODE=pl_stop";

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
    
   
    
    private String urlencode(String s){
    	String _s = null;
    	try {
    	    _s = URLEncoder.encode(s,"UTF-8");
    	    //Log.d("TEST", encodedurl);
    	} catch (UnsupportedEncodingException e) {
    	    e.printStackTrace();
    	} 
    	return _s;
    }
    
    public String genurl(String mode){
    	String _url = null;
    	if(mode=="song"){
	    		_url = serverpath+"webservice.php?type=search&mode="+modestr+"&name="+searchstr+"&page="+page+"&clrcache="+Math.random();
    	}else if(mode=="all"){
	    		_url = serverpath+"webservice.php?type=search&mode=all&page="+page+"&clrcache="+Math.random();
    	}
    	return _url;
    }
    //
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
			data = new ArrayList<Data>();
		    Log.e("SAX XML", "sax error", se); 
		  } catch(IOException ioe) { 
		    Log.e("SAX XML", "sax parse io error", ioe); 
		  } 
		 
		  return data; 
	} 

	
	static class ViewHolder {
		TextView _t_title;
		TextView _t_album;
		TextView _t_artist;
		ImageView _img;
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
			  
		    
			        
			
			public View getView(int position, View convertView, ViewGroup parent) {
				  
				Log.e("###bottom here####",String.valueOf(position)+"   "+String.valueOf(data.size()));
				if (position == data.size()-1 && data.size() > 10) {
			        
					++page;
					int m = data.size();   
					data.remove(m-1);
					
					_urlSearch = serverpath+"webservice.php?type=search&mode="+modestr+"&name="+searchstr+"&page="+page+"&clrcache="+Math.random();
					
					
					datamore = _parseXml(_urlSearch);
					
					data.addAll(datamore);
					
						
					Data more = new Data();
			        more.title = "No more result...";
			        data.add(more);
						
						  
					_a.notifyDataSetChanged();
					
					l22.smoothScrollToPosition(m);
					
					_a.notifyDataSetChanged();
					
					
			    }
				
				
				View row = convertView;
				AlbumListWrapper wrapper=null;
				int type = getItemViewType(position);
				//Log.e("txt",""+type);
				//Log.e("type",String.valueOf(type));
				if (row==null) {													
					LayoutInflater inflater=getLayoutInflater();
					
					if(type!=1){
						row=inflater.inflate(R.layout.row_pic_layout, null);
					}else{
						row=inflater.inflate(R.layout.row_moreresult, null);
					} 
					 
					row=inflater.inflate(R.layout.row_pic_layout, null);
					wrapper=new AlbumListWrapper(row);
					row.setTag(wrapper);
					
					ViewHolder holder = new ViewHolder();
					holder._t_title = (TextView)row.findViewById(R.id.txtsong);
					holder._t_album = (TextView)row.findViewById(R.id.txtalbum);
					holder._t_artist = (TextView)row.findViewById(R.id.txtartist);
					holder._img = (ImageView)row.findViewById(R.id.image);
					
//					TextView _t_title = (TextView)row.findViewById(R.id.txtsong);
//					TextView _t_album = (TextView)row.findViewById(R.id.txtalbum);
//					TextView _t_artist = (TextView)row.findViewById(R.id.txtartist);
//					ImageView _img = (ImageView)row.findViewById(R.id.image);
					
					row.setTag(holder);
				}
//				else {
//					wrapper=(AlbumListWrapper)row.getTag();
//				}

				Data entry = data.get(position);
				
				//Bitmap bimage=  getBitmapFromURL(serverpath + data.get(position).picurl);
				String _picurl = data.get(position).picurl;
				if(_picurl.length() < 1){
					_picurl = serverpath + "images/noimage.png";
				}else{
					_picurl = serverpath + _picurl;
				}
				
				
				if(entry != null) {
			        ViewHolder holder = (ViewHolder)row.getTag();
			        holder._t_title.setText(entry.getTitle());
			        holder._t_album.setText(entry.getAlbum());
			        holder._t_artist.setText(entry.getArtist());
					imageLoader.DisplayImage(_picurl, holder._img);
			        
//			        if(bimage == null){  
//						holder._img.setImageResource(R.drawable.noimage);
//					}else{
//						holder._img.setImageBitmap(bimage);
//					}
			    }
			    //return row;
				
			    /*
				if(_t_title!=null){
					//Log.e("txt",""+position);
					_t_title.setText(data.get(position).title);
				}
				if(_t_album!=null){
					_t_album.setText(data.get(position).album);
				}
				if(_t_artist!=null){
					_t_artist.setText(data.get(position).artist);
				}
				
				
				
				
				if(bimage == null){
					_img.setImageResource(R.drawable.noimage);
				}else{
					_img.setImageBitmap(bimage);
				}
				*/
				
				
				
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
				
				return row;
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
		  private boolean _inTitle, _inArtist, _inAlbum, _songid, _inleaf, _inName, _inDuration, _inPlaytime, _inUrl, _inId; 
		 
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
		  
		  /*
		  <songlist>
			  <onesong id="17">
				  <id>17</id>
				  <name>
				  	<![CDATA[ 2JUTA ]]>
				  </name>
				  <url>
				  <![CDATA[ /hddExt/KARAOKESONG/MALAY_SONG/083812.DAT ]]>
				  </url>
				  <duration>352208978</duration>
				  <playtime>352000000</playtime>
			  </onesong>
		  </songlist>
		  */
		  
		  
		  @Override
		  public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException { 
			  
			if(localName.equals("onesong")){
			  _data = new Data();
		      String s = atts.getValue("id");
		      Log.d("result.java s = ",s);
		      _data.songid = s;
		      _data.songurl = atts.getValue("songurl1");
		      _data.songurl1 = atts.getValue("songurl1");
		      _data.songurl2 = atts.getValue("songurl2");
		      
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
			} else if(localName.equals("id")){
				_inId = true;
			}else if(localName.equals("name")){
				_inName = true;
			}else if(localName.equals("url")){
				_inUrl = true;
			}else if(localName.equals("duration")){
				_inDuration = true;
			}else if(localName.equals("playtime")){
				_inPlaytime = true;
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
		    }else if(_inName){
		    	_inName = false;
		    } else if(_inUrl){
		    	_inUrl = false;
		    } else if(_inDuration){
		    	_inDuration = false;
		    } else if(_inPlaytime){
		      _inPlaytime = false;
		    }else if(_inId){
			  _inId = false;
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
		    Log.d("result.java chars = ",chars);
		    if(_inTitle) { 
		      _data.title = chars.toString(); 
		    } else if(_inArtist) { 
		      _data.artist = chars.toString(); 
		    } else if(_inAlbum){
		      _data.album = chars.toString();
		    } else if(_inName){
		      _data.title = chars.toString();
		    } else if(_inUrl){
		    	_data.path = chars.toString();
		    } else if(_inDuration){
		    	
		    } else if(_inPlaytime){
		      _data.time = chars.toString();
		      Log.e("We're in playtime Node","Yes, We are.");
		    }else if(_inId){
			  _data.id = chars.toString();
			  Log.d("result.java _data.id = ",_data.id);
		    }
		  } 
		  
		
		  
		}
	
	public class Data { 
		  // I know this could be an int, but this is just to show you how it works 
		  public String title = ""; 
		  public String artist = ""; 
		  public String album = ""; 
		  public String songid = "";
		  public String picurl = "";
		  public String songurl= "";
		  public String songurl1= "";
		  public String songurl2= "";
		  public String id="";
		  public String time="";
		  public String path = "";
		 
		  public Data() { 
		 
		  }

		public CharSequence getTitle() {
			// TODO Auto-generated method stub
			return title;
		}

		public CharSequence getAlbum() {
			// TODO Auto-generated method stub
			return album;
		}

		public CharSequence getArtist() {
			// TODO Auto-generated method stub
			return artist;
		}
		public CharSequence getSongId(){
			return songid;
		}
	}
    //
	public class objQueqe{
		public String id = "";
		public String name = "";
		public objQueqe(){
			
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

    public static boolean exists(Data data2){
    	String _url = "";
    	boolean result = false;
    	_songurlobject  = new result().new songurlobject();
    	for(int i = 0; i<4; ++i){
    		if(i==0 && result == false){
    			// Set Song URL
    			_url = data2.songid;
    			Log.d("result.java","Set songurl1");
    			Log.d("result.java _url = ",_url);
    			//String ss = _url.substring(_url.length() - 4,_url.length());
    	    	//_songurlobject.url = data2.songurl1; comment by ton
    			_songurlobject.url = data2.songid;
    			Log.d("_songurlobject.url = ",_songurlobject.url);
    			result= true;
// Check file comment by ton     			
//    	    	try {
//    		          HttpURLConnection.setFollowRedirects(false);
//    		          // note : you may also need
//    		          //        HttpURLConnection.setInstanceFollowRedirects(false)
//    		          HttpURLConnection con = (HttpURLConnection) new URL(_url).openConnection();
//    		          
//    		          result =  (con.getResponseCode() == HttpURLConnection.HTTP_OK);
//    		        }
//    		        catch (Exception e) {
//    		           e.printStackTrace();
//    		           Log.e("not found ####","....");
//    		          //result = false;
//    		    }
    		}else if(i==1 && result == false){
    			// Set Song URL
    			_url = data2.songid;
    			Log.d("result.java","Set songurl2");
    			
    			Log.d("result.java _url = ",_url);
    	    	_songurlobject.url = data2.songid;
    	    	Log.d("resout.java _songurlobject.url = ",_songurlobject.url);
    	    	result= true;

// Check file comment by ton 
//    	    	try {
//    		          HttpURLConnection.setFollowRedirects(false);
//    		          // note : you may also need
//    		          //        HttpURLConnection.setInstanceFollowRedirects(false)
//    		          HttpURLConnection con = (HttpURLConnection) new URL(_url).openConnection();
//    		          
//    		          result =  (con.getResponseCode() == HttpURLConnection.HTTP_OK);
//    		        }
//    		        catch (Exception e) {
//    		           e.printStackTrace();
//    		           Log.e("not found ####","....");
//    		          //result = false;
//    		    }
    	    	
    		}
    		
    	}
    	_songurlobject.result = result;
    	return _songurlobject.result;
        
     }
    
    public class songurlobject{
    	public boolean result = false;
    	public String url = "";
    	
    	songurlobject(){
    	
    	}
    }

    public boolean fileExistsInSD(String sFileName){
        //String sFolder = Environment.getExternalStorageDirectory().toString() + APP_SD_PATH;
        String sFolder = APP_SD_PATH;
        String sFile=sFolder+"/"+sFileName;
        java.io.File file = new java.io.File(sFile);
        return file.exists();
    }
    
    
}


