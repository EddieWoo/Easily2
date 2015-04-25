package com.spring.framgent;


import com.spring.usecenter.LoginActivity;
import com.spring.gowhere.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Uc_Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view			=  inflater.inflate(R.layout.uc_bak, container, false);
		TextView textView	=(TextView) view.findViewById(R.id.title_text);
		textView.setText("个人中心");
		Button button = (Button) view.findViewById(R.id.login);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}

}
