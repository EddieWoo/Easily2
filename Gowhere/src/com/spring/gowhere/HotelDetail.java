package com.spring.gowhere;

import java.util.ArrayList;

import com.spring.pic.PicActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class HotelDetail extends Activity{

	private LayoutInflater inflater;
	private ListView listView;
	private ArrayList<String> title		=new ArrayList<String>();
	private ArrayList<String > content		=new ArrayList<String>();
	private ArrayList<String > price		=new ArrayList<String>();
	private View view;
	private ImageView imageView;
	private LinearLayout layout, layout1;
	private boolean flags=true;
	
	private ImageView imageView_bg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel_info);
		
		layout1 = (LinearLayout) findViewById(R.id.title_back);
		layout1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		imageView_bg			=(ImageView) findViewById(R.id.imageView_bg);
		
		imageView_bg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent			=new Intent();
				intent.setClass(getApplicationContext(), PicActivity.class);
				startActivity(intent);
			}
		});
		
//		Bitmap bitmap			=BitmapFactory.decodeResource(getResources(), R.drawable.hotel_info_bg);
//		imageView_bg.setImageBitmap(BitmapUtils.zoomImage(bitmap, getWindowManager().getDefaultDisplay().getWidth(), 200));
		
		title.add("投资¥50 ");
		title.add("投资¥100");
		title.add("投资¥150");
		title.add("投资¥XXX");
		content.add("已有 10 位支持者 / 限额 20 位");
		content.add("已有 22 位支持者 / 限额 30 位");
		content.add("已有 5 位支持者 / 限额 40 位");
		content.add("自由投资金额");
		price.add("¥50");
		price.add("¥100");
		price.add("¥150");
		price.add("¥XXX");
		
		layout			=(LinearLayout) findViewById(R.id.hotel_detail);
		imageView		=(ImageView) findViewById(R.id.imageView_but);
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(flags){
					System.out.println("w:"+view.getWidth()+" h:"+view.getHeight());
					LinearLayout.LayoutParams	params		=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 130*4);
					params.leftMargin=16;
					params.rightMargin=16;
//					LayoutParams params			=new LayoutParams(LayoutParams.WRAP_CONTENT, 130*4);
					layout.addView(view,params);
					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_arrow_down));
					flags=false;
				}else{
					layout.removeView(view);
					imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_arrow_right));
					flags=true;
				}
			}
		});
		
		inflater			=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		view				=inflater.inflate(R.layout.hotel_listview, null);
		listView				=(ListView) view.findViewById(R.id.listView1);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), payment.class);
				startActivity(intent);
			}
		});
		
		listView.setAdapter(new MyAdapter());
		
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return title.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			if(view==null){
				view			=inflater.inflate(R.layout.hotel_listeview_detail, null);
			}
			TextView titleTextView		=(TextView) view.findViewById(R.id.title);
			TextView contentTextView	=(TextView) view.findViewById(R.id.content);
			TextView priceTextView		=(TextView) view.findViewById(R.id.money);
			titleTextView.setText(title.get(position));
			contentTextView.setText(content.get(position));
			priceTextView.setText(price.get(position));
			return view;
		}
		
	}
	
	
}
