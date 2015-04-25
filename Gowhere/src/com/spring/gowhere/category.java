package com.spring.gowhere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class category extends Activity{

	   RadioGroup radiogroup;  
	   RadioButton radio1,radio2,radio3,radio4,radio5,radio6,radio7;  
	   TextView textview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);
		
		    textview=(TextView)findViewById(R.id.textview1);  
	        radiogroup=(RadioGroup)findViewById(R.id.radiogroup1);  
	        radio1=(RadioButton)findViewById(R.id.radiobutton1);  
	        radio2=(RadioButton)findViewById(R.id.radiobutton2);  
	        radio3=(RadioButton)findViewById(R.id.radiobutton3);  
	        radio4=(RadioButton)findViewById(R.id.radiobutton4);  
	        radio5=(RadioButton)findViewById(R.id.radiobutton5);  
	        radio6=(RadioButton)findViewById(R.id.radiobutton6);  
	        radio7=(RadioButton)findViewById(R.id.radiobutton7);  
	        
	        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
	              
	            private String result;

				@Override  
	            public void onCheckedChanged(RadioGroup group, int checkedId) {  
	                // TODO Auto-generated method stub  
	              
	            	 if(checkedId==radio1.getId()) 
	            		 result ="娱乐";
	            	 if(checkedId==radio2.getId()) 
	            		 result ="公益";
	            	 if(checkedId==radio3.getId()) 
	            		 result ="出版";
	            	 if(checkedId==radio4.getId()) 
	            		 result ="收藏";
	            	 if(checkedId==radio5.getId()) 
	            		 result ="农业";
	            	 if(checkedId==radio6.getId()) 
	            		 result ="动漫游戏";
	            	 if(checkedId==radio7.getId()) 
	            		 result ="科技/设计";
	 
	                 Intent intent = new Intent();
	                 intent.putExtra("result", result);
	                 /*
	                  * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
	                  */
	                 setResult(1101, intent);
	                 //    结束当前这个Activity对象的生命
	                 finish();
	            }  
				
	        });  
	}
	
	
	
}
