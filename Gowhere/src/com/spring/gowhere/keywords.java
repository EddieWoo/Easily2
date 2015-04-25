package com.spring.gowhere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class keywords extends Activity{
       
	private EditText editText110;
	private Button button2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keywords);
		
	    editText110 = (EditText)findViewById(R.id.editText110);
		button2 =(Button)findViewById(R.id.button2);
	    
		
		button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String result = editText110.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("result", result);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
                setResult(1001, intent);
                //    结束当前这个Activity对象的生命
                finish();
            }
        });

	}
	
}
