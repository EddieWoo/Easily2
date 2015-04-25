package com.spring.framgent;

import com.spring.gowhere.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;  
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;  
import android.view.ViewGroup; 
import android.view.View.OnClickListener;


public class financialproduct extends Activity{
	
	private LinearLayout layout;
	
	
	private static final String[] strs = new String[] {
		    "中银QD02(中银稳健增长)", "中银QD03(中银新兴市场)", "债市通(中银债市通理财计划)", "中银FOF1(中银精选基金理财计划)", "中银日积月累-日计划收益率"
		    };//定义一个String数组用来显示ListView的内容
		private ListView lv;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.financialproduct);
		
		layout=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		setTheme(R.style.AppBaseTheme);
		
		lv = (ListView) findViewById(R.id.lv);//得到ListView对象的引用
		/*为ListView设置Adapter来绑定数据*/
		lv.setAdapter(new ArrayAdapter<String>(this,
		 android.R.layout.simple_list_item_1, strs));
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener()  
        {  
  
            @Override  
            public void onItemClick(AdapterView<?> parent, View view, int position,  
                    long id) {   
                    Intent intent1=new Intent(financialproduct.this,productdetail.class);   
                    startActivity(intent1);  
            }  
              
        });  
		
		
	}
	
	

}
