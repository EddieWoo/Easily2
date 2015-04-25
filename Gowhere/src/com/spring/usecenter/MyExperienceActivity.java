package com.spring.usecenter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.spring.gowhere.R;

public class MyExperienceActivity extends Activity {

	private LinearLayout layout;
	private ImageView titlebar_info;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uc_experience_back);
		TextView textView = (TextView) findViewById(R.id.title_text);
		textView.setText("我的理财经验");
		layout			=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		titlebar_info = (ImageView)findViewById(R.id.titlebar_info);
		titlebar_info.setOnClickListener(
				new View.OnClickListener() {
			            public void onClick(View v)
			            {
						   AlertDialog.Builder builder = new AlertDialog.Builder(MyExperienceActivity.this);    
						   builder.setTitle("温馨小提示" );  
					       builder.setMessage("此处点击后原本应该是可以撰写自己的理财经验，但因为没有服务器，因此暂时未实现该功能以及其页面~~" );  
					       builder.setPositiveButton("确定", null);  
					       builder.show();
			            }
				}
				);
		
		initData();
	}

	private void initData() {
		ListView bookList = (ListView) findViewById(R.id.experience_ls);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book3);// 图像资源的ID
		map.put("title", "如何选择合适的投资产品");
		map.put("cate", "基础理财");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book4);// 图像资源的ID
		map.put("title", "我的投资心酸史");
		map.put("cate", "心路历程");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book1);// 图像资源的ID
		map.put("title", "比较好的理财产品");
		map.put("cate", "经验点滴");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book3);// 图像资源的ID
		map.put("title", "怎样合理分配投资比例");
		map.put("cate", "基础理财");
		remoteWindowItem.add(map);

		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listItemAdapter = new SimpleAdapter(this,
				remoteWindowItem,// 数据源
				R.layout.experience_ls,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "list_image", "title", "cate" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.list_image, R.id.title, R.id.cate });
		bookList.setAdapter(listItemAdapter);
		
		bookList.setOnItemClickListener(new ListView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, android.view.View arg1,
					int arg2, long arg3) {
		        Intent intent = new Intent(MyExperienceActivity.this,ExperienceContent.class);
		         startActivity(intent);	
 
			}
		});
	}

}
