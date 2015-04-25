package com.spring.framgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.gowhere.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class productdetail extends Activity {
	
	private LinearLayout layout;
//	10项数据
	private String[] code = new String[]
			{ "830002", "830002", "830002", "830002", "830002", "830002", "830002", "830002", "830002", "830002",
			"830002", "830002", "830002", "830002", "830002", "830002", "830002", "830002", "830002", "830002"};
	
	private String[] name = new String[]
			{ "中银QD02","中银QD02" ,"中银QD02","中银QD02","中银QD02","中银QD02","中银QD02","中银QD02","中银QD02","中银QD02",
			"中银QD02","中银QD02" ,"中银QD02","中银QD02","中银QD02","中银QD02","中银QD02","中银QD02","中银QD02","中银QD02"};
	private String[] amount = new String[]
			{"1.0707","1.0715" ,"1.0796","1.0743","1.0810","1.0845","1.0833","1.0650","1.0540","1.0892"
			,"1.0707","1.0715" ,"1.0796","1.0743","1.0810","1.0845","1.0833","1.0650","1.0540","1.0892"};
	private String[] bonus = new String[]
			{ "0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000" 
			, "0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000","0.2000"};
	
	private String[] ratio = new String[]
			{"-0.0746","-0.0467","-0.0593" ,"0.2580" ,"0.1786" ,"0.2559" ,"0.6809" ,"0.1111" ,"0.3562" ,"0.1537"
			,"-0.0746","-0.0467","-0.0593" ,"0.2580" ,"0.1786" ,"0.2559" ,"0.6809" ,"0.1111" ,"0.3562" ,"0.1537"};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productdetail);
		
		layout=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < code.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("code", code[i]);
			listItem.put("name", name[i]);
			listItem.put("amount", amount[i]);
			listItem.put("bonus", bonus[i]);
			listItem.put("ratio", ratio[i]);
			listItems.add(listItem);
		}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this
				, listItems 
				, R.layout.productitem
				, new String[]{ "code", "name","amount","bonus","ratio" }
				, new int[]{R.id.code , R.id.name,R.id.amount,R.id.bonus,R.id.ratio});
			ListView list = (ListView)findViewById(R.id.listView);
			//为ListView设置Adapter
			list.setAdapter(simpleAdapter);
		
	}
	
}
