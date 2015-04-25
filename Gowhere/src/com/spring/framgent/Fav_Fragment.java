package com.spring.framgent;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spring.gowhere.R;

public class Fav_Fragment extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view			= inflater.inflate(R.layout.fav, container, false);
		TextView textView		=(TextView) view.findViewById(R.id.title_text);
		textView.setText("我的收藏");
		
		RelativeLayout ly1	= (RelativeLayout) view.findViewById(R.id.ly1);
		ly1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
			   AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());    
			   builder1.setTitle("小提示" );  
		       builder1.setMessage("您还没有关注的理财经验哦~~" );  
		       builder1.setPositiveButton("确定", null);  
		       builder1.show();
            }
		});
		
		RelativeLayout ly2	= (RelativeLayout) view.findViewById(R.id.ly2);
		ly2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
			   AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());    
			   builder2.setTitle("小提示" );  
		       builder2.setMessage("您还没有收藏的投资项目哦~~" );  
		       builder2.setPositiveButton("确定", null);  
		       builder2.show();
            }
		});
		
		RelativeLayout ly3	= (RelativeLayout) view.findViewById(R.id.ly3);
		ly3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
			   AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());    
			   builder3.setTitle("小提示" );  
		       builder3.setMessage("您还没有收藏的理财产品哦~~" );  
		       builder3.setPositiveButton("确定", null);  
		       builder3.show();
            }
		});
		
		RelativeLayout ly4	= (RelativeLayout) view.findViewById(R.id.ly4);
		ly4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
			   AlertDialog.Builder builder4 = new AlertDialog.Builder(getActivity());    
			   builder4.setTitle("小提示" );  
		       builder4.setMessage("您还没有关注的金融资讯哦~~" );  
		       builder4.setPositiveButton("确定", null);  
		       builder4.show();
            }
		});
		
		return view;
	}

}
