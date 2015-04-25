package com.spring.gowhere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class tolerance extends Activity{

	   RadioGroup radiogroup;  
	   RadioButton radio1,radio2,radio3,radio4,radio5,radio6,radio7;  
	   TextView textview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tolerance);
		
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
	            		 result ="500以下";
	            	 if(checkedId==radio2.getId()) 
	            		 result ="500-1000";
	            	 if(checkedId==radio3.getId()) 
	            		 result ="1000-2000";
	            	 if(checkedId==radio4.getId()) 
	            		 result ="2000-4000";
	            	 if(checkedId==radio5.getId()) 
	            		 result ="4000-7000";
	            	 if(checkedId==radio6.getId()) 
	            		 result ="7000-10000";
	            	 if(checkedId==radio7.getId()) 
	            		 result ="10000以上";
	 
	                 Intent intent = new Intent();
	                 intent.putExtra("result", result);
	                 /*
	                  * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
	                  */
	                 setResult(0101, intent);
	                 //    结束当前这个Activity对象的生命
	                 finish();
	            }  
				
	        });  
	}
	
	
	
}
