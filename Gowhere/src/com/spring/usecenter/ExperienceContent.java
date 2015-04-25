package com.spring.usecenter;

import com.spring.gowhere.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExperienceContent extends Activity {
	
	private LinearLayout layout1;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uc_experience_content);
		
		TextView textView	=(TextView)findViewById(R.id.title_text);
		textView.setText("理财经验");
		
		layout1 = (LinearLayout) findViewById(R.id.title_back);
		layout1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

}
