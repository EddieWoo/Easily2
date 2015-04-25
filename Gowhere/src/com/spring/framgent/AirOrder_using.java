package com.spring.framgent;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.spring.gowhere.R;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AirOrder_using extends Fragment{

	private MyHandler myhandler;
	private PullToRefreshListView mPullRefreshListView;
	private LinearLayout imageView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.air_order_using, container, false);	
		mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		imageView				=(LinearLayout) view.findViewById(R.id.network_failed);
		myhandler	=new MyHandler();
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//				new GetDataTask().execute();
				imageView.setVisibility(View.INVISIBLE);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
							myhandler.sendEmptyMessage(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		return view; 
	}
	
	private class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				mPullRefreshListView.onRefreshComplete();
				imageView.setVisibility(View.VISIBLE);
				break;

			default:
				break;
			}
		}
	}
}
