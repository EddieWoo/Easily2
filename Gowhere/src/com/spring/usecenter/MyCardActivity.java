package com.spring.usecenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.spring.gowhere.R;

public class MyCardActivity extends Activity {
	
	private TextView tv_tips;
	private LinearLayout layout;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycard);
		TextView textView	=(TextView)findViewById(R.id.title_text);
		textView.setText("我的银行卡");
		
		layout			=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		Intent intent = this.getIntent();        //获取已有的intent对象   
		Bundle bundle = intent.getExtras();    //获取intent里面的bundle对象   
		String str = bundle.getString("str");    //获取Bundle里面的字符串 
		if(null!=str && ""!=str)
			dealStr(str);
		//listView = (ListView) findViewById(R.id.mycard_ls);  
		
	}
	
		
		private void dealStr(String str){

			ListView resList = (ListView)findViewById(R.id.mycard_ls);
			tv_tips = (TextView) findViewById(R.id.tips);
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();//用于listview显示
			Map<String, Object> map = new HashMap<String, Object>();
			
			Map<String, Object> mapStr = new HashMap<String, Object>();
			mapStr = JsonUtils.getMapObj(str);
			if(mapStr.containsKey("rtnmsg")){
				Object obj_n = new Object();
				obj_n = mapStr.get("rtnmsg");
				if(obj_n.toString().equals("查询无记录")){
					tv_tips.setVisibility(View.VISIBLE);
					return;
				}
			}
			Object obj = new Object();
			obj = mapStr.get("saplist");
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			list = JsonUtils.getListMap(obj.toString());
			String t = "";
			String ifncal = "未明";
			for(int i=0;i<list.size();i++){
				 t = t + i+ ":" + "  "+list.get(i).get("accno").toString()+"  ";
				 map = new HashMap<String, Object>();
				 map.put("accno", list.get(i).get("accno").toString());
				 map.put("alias", list.get(i).get("alias").toString());
				 if(list.get(i).get("ifncal").toString().equals("0")){
					 ifncal = "未登记";
				 }else if(list.get(i).get("ifncal").toString().equals("1")){
					 ifncal = "已登记";
				 }
				 map.put("ifncal", ifncal);
				 data.add(map);
			}
			resList.setAdapter(new SimpleAdapter(this, data, R.layout.mycard_ls, new String[]{"accno", "alias", "ifncal"}, new int[]{R.id.account, R.id.alias, R.id.ifn}));
		}
}
