package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.spring.gowhere.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CustomizedListView extends Activity {
	// All static variables
	static final String URL = "http://content.businessvalue.com.cn/feed";
	// XML node keys
	static final String KEY_SONG = "item"; // parent node
	static final String KEY_ID = "comments";
	static final String KEY_TITLE = "title";
	static final String KEY_ARTIST = "dc:creator";  //artist
	static final String KEY_DURATION = "pubDate";
	static final String KEY_THUMB_URL = "link";
	 private ProgressDialog progressDialog;
	 private LinearLayout layout;
	ListView list;
	LazyAdapter adapter;
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		 layout =(LinearLayout) findViewById(R.id.title_back);
		 
		 
		progressDialog = new ProgressDialog(CustomizedListView.this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在下载中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为圆圈的形式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        
		
//		android.os.NetworkOnMainThreadException
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		
		  adapter=new LazyAdapter(this, new MyAsyncTask().doInBackground(URL));        
	      list.setAdapter(adapter);

		
/*
		list=(ListView)findViewById(R.id.list);
        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_SONG);
		// looping through all song nodes <song>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
			map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));
			// adding HashList to ArrayList
			songsList.add(map);
		}
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);        
        list.setAdapter(adapter);
        */
        
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
									
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) adapter.getItem(position);     
		        String newsurl = (String) map.get("link");
		            
                Intent intent = new Intent(); 
                intent.setClass(CustomizedListView.this,webview.class); 
                Bundle bundle = new Bundle();
                bundle.putString("newsurl",newsurl);            
                intent.putExtras(bundle);             
                startActivity(intent);

			}
		});		
        
        layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
        
	}	
	
	
	
	
	
	@Override
	protected void onStart() {
		ConnectivityManager manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			Toast.makeText(CustomizedListView.this, "网络连接连接", 0).show();
		} else {
			AlertDialog.Builder builder = new Builder(CustomizedListView.this);
			builder.setTitle("开启网络服务");
			builder.setMessage("网络没有连接，请到设置进行网络设置！");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							if (android.os.Build.VERSION.SDK_INT > 10) {
								// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
								startActivity(new Intent(
										android.provider.Settings.ACTION_SETTINGS));
							} else {
								startActivity(new Intent(
										android.provider.Settings.ACTION_WIRELESS_SETTINGS));
							}
							dialog.cancel();
						}
					});

			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			builder.show();
		}
		super.onStart();
	}
        

	
	  public class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<HashMap<String, String>>>
	    {
	        @Override
	        protected void onPreExecute()
	        {
	            super.onPreExecute();
	            //    在onPreExecute()中我们让ProgressDialog显示出来
	            progressDialog.show();
	        }
	        @Override
	        protected ArrayList<HashMap<String, String>> doInBackground(String... params)
	        {
	     
	        	list=(ListView)findViewById(R.id.list);
	        	ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
        		XMLParser parser = new XMLParser();
	            try
	            {
	            	
	        		String xml = parser.getXmlFromUrl(URL); // getting XML from URL
	        		Document doc = parser.getDomElement(xml); // getting DOM element
	        	   
	        		NodeList nl = doc.getElementsByTagName(KEY_SONG);
	        		// looping through all song nodes <song>
	        		for (int i = 0; i < nl.getLength(); i++) {
	        			// creating new HashMap
	        			HashMap<String, String> map = new HashMap<String, String>();
	        			Element e = (Element) nl.item(i);
	        			// adding each child node to HashMap key => value
	        			map.put(KEY_ID, parser.getValue(e, KEY_ID));
	        			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
	        			map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
	        			map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
	        			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));
	        			// adding HashList to ArrayList
	        			songsList.add(map);
	
	        		}
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	            finally
	            {
	               
	            }
	            return songsList;
	        }
	        @Override
	        protected void onProgressUpdate(Integer... values)
	        {
	            super.onProgressUpdate(values);
	        }
	        @Override
	        protected void onPostExecute(ArrayList<HashMap<String, String>> songsList)
	        {
	            super.onPostExecute(songsList);
	            
	            //    使ProgressDialog框消失
	            progressDialog.dismiss();
	            
	        }
	        
	    }
	  
	 
		
		
	
}



