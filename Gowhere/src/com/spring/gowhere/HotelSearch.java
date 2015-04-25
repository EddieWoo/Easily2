package com.spring.gowhere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

//投资项目搜索activity
public class HotelSearch extends Activity implements OnClickListener {

	private TextView title;
	private LinearLayout layout;

	private ListView listView;
	private MyAdapter adapter;
	private LayoutInflater inflater;

	// 底部栏的事件
	private LinearLayout tabLayout1, tabLayout2, tabLayout3, tabLayout4;

	private ArrayList<String> list;
	
	  private Integer[] imgeIDs = {R.drawable.placeholder,   
	            R.drawable.placeholder2, R.drawable.placeholder3,  
	            R.drawable.placeholder4, R.drawable.placeholder5}; 
	
	private String[] titles = new String[]
			{ "肌肤管家-你的智能肌肤检测仪器", "刷刷手环——你走路，我买单，全球首款智能支付手环", "护苗计划——南邮沐霖公益协会为西部儿童众筹", "团委书记为汾西黑小米众筹 帮助农村青年创业", "我的执着，云南民族传统手工古法普洱茶"};
	private String[] didian = new String[]
			{ "only 北京东城区", "刷刷手环  北京朝阳区", "南大公益协会  江苏南京", "米司令  山西临汾", "文玩天下论坛  北京"};
	
	private String[] pinlun = new String[]
			{ "/112条评论", "/30条评论", "/59条评论", "/402条评论", "/150条评论"};

	private String[] wanchengdu = new String[]
			{ "目前完成 50%", "目前完成 30%", "目前完成 59%", "目前完成 40%", "目前完成 15%"};
	private String[] jiezhiriqi = new String[]
			{ "截止日期：2014年12月21日", "截止日期：2015年1月1日", "截止日期：2014年12月28日", "截止日期：2015年5月11日", "截止日期：2015年3月2日"};
	
	private String[] zan = new String[]
			{ "100个赞", "53个赞", "85个赞", "500个赞", "360个赞"};
	
	private String[] money = new String[]
			{ "目标：30天   ¥5000", "目标：180天   ¥288,493", "目标：10天   ¥800", "目标：30天   ¥8000", "目标：30天   ¥10000"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotelsearch);

		tabLayout1 = (LinearLayout) findViewById(R.id.linear);
		tabLayout2 = (LinearLayout) findViewById(R.id.linear1);
		tabLayout3 = (LinearLayout) findViewById(R.id.linear2);
		tabLayout4 = (LinearLayout) findViewById(R.id.linear3);

		tabLayout1.setOnClickListener(this);
		tabLayout2.setOnClickListener(this);
		tabLayout3.setOnClickListener(this);
		tabLayout4.setOnClickListener(this);

		title = (TextView) findViewById(R.id.title_text);
		title.setText("投资项目搜索");
		layout = (LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		
		
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < titles.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("imageView1", imgeIDs[i]);
			listItem.put("title", titles[i]);
			listItem.put("didian", didian[i]);
			listItem.put("pinlun", pinlun[i]);
			listItem.put("wanchengdu", wanchengdu[i]);
			listItem.put("jiezhiriqi", jiezhiriqi[i]);
			listItem.put("zan", zan[i]);
			listItem.put("money", money[i]);
			listItems.add(listItem);
		}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this
				, listItems 
				, R.layout.hotelsearch_item
				, new String[]{ "imageView1","title", "didian","pinlun","wanchengdu","jiezhiriqi","zan","money" }
				, new int[]{R.id.imageView1, R.id.title , R.id.didian,R.id.pinlun,R.id.wanchengdu,R.id.jiezhiriqi,R.id.zan,R.id.money});
			ListView listView = (ListView)findViewById(R.id.listView1);
			//为ListView设置Adapter
			listView.setAdapter(simpleAdapter);
		
		
		
		/*
		
				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		
		
		adapter = new MyAdapter();
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);

		*/
		
		
		
		
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), HotelDetail.class);
				startActivity(intent);
				// 想做成点击后变颜色，没搞成功，下次回来该
				// if (Integer.parseInt(view.findViewById(R.id.flag).getTag()
				// .toString()) == 1){
				// ((RelativeLayout) view.findViewById(R.id.listview_item))
				// .setBackgroundColor(getResources().getColor(
				// R.color.inselected));
				// view.findViewById(R.id.flag).setTag("2");
				// }
			}
		});

	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			view = inflater.inflate(R.layout.hotelsearch_item, null);

			return view;
		}

	}

/*	
	if (hasFocus) {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View music_popunwindwow = mLayoutInflater.inflate(
				R.layout.hotel_filter, null);
		PopupWindow mPopupWindow = new PopupWindow(music_popunwindwow,
				LayoutParams.MATCH_PARENT, 150, true);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int height = windowManager.getDefaultDisplay().getHeight();
		mPopupWindow
				.showAtLocation(
						v,
						Gravity.NO_GRAVITY,
						tabLayout1.getWidth() / 2
								- mPopupWindow.getWidth() / 2,
						height - mPopupWindow.getHeight()
								- tabLayout1.getHeight() + 4);
		mPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				tabLayout3.clearFocus();
			}
		});
	}*/
	
	@Override
	public void onClick(View v) {

		int tag		=0;
		
		switch (v.getId()) {
		case R.id.linear:
			tag		=0;
			break;
		case R.id.linear1:
			tag		=1;
			break;
		case R.id.linear2:
			tag		=2;
			break;
		case R.id.linear3:
			tag		=3;
			break;
		default:
			break;
		}
		
		//这一部分有个Bug没还没有解决
		/*Intent intent		=new Intent();
		intent.setClass(getApplicationContext(), HotelTab.class);
		intent.putExtra("tag", tag);
		startActivity(intent);*/
	}

}
