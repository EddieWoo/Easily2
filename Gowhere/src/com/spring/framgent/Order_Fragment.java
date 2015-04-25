package com.spring.framgent;


import java.util.ArrayList;
import java.util.HashMap;

import com.spring.gowhere.AirOrder;
import com.spring.gowhere.R;
import com.spring.usecenter.ExperienceContent;
import com.spring.usecenter.MyExperienceActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Order_Fragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view			= inflater.inflate(R.layout.uc_experience, container, false);
		TextView textView		=(TextView) view.findViewById(R.id.title_text);
		textView.setText("理财广场");

		ListView bookList = (ListView) view.findViewById(R.id.experience_ls);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book3);// 图像资源的ID
		map.put("title", "如何正确认识投资");
		map.put("cate", "基础理财");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book5);// 图像资源的ID
		map.put("title", "投资项目Or投资理财产品");
		map.put("cate", "心路历程");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book1);// 图像资源的ID
		map.put("title", "高风险？高收益？");
		map.put("cate", "经验点滴");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book4);// 图像资源的ID
		map.put("title", "怎样合理分配投资比例");
		map.put("cate", "基础理财");
		remoteWindowItem.add(map);
		
		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.book5);// 图像资源的ID
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
		map.put("title", "投资要如何取舍");
		map.put("cate", "基础理财");
		remoteWindowItem.add(map);

		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listItemAdapter = new SimpleAdapter(this.getActivity(),
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
		        Intent intent = new Intent(getActivity(),ExperienceContent.class);
		        startActivity(intent);	
 
			}
		});

		return view;
	}
		
}
