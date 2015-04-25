package com.spring.gowhere;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.LayoutInflater;


public class HotelActivity extends Activity{
	
	private TextView title;
	private LinearLayout layout;
	private RelativeLayout search;
	private RelativeLayout gettimee;
	private RelativeLayout categories;
	private RelativeLayout keywords;
	private RelativeLayout rangements;
	
	private TextView getTime;
	private TextView gettime2;
	private TextView keyword;
	private TextView rangement;
	private TextView categoryy;
	private Calendar calendar;// 用来装日期的
	private DatePickerDialog dialog;
	private Button searchbutt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel);
		
		getTime = (TextView) findViewById(R.id.deadlinetime);
		gettime2= (TextView) findViewById(R.id.deadline);
		keyword=(TextView) findViewById(R.id.keyword);
		categoryy=(TextView) findViewById(R.id.categoryy);
		rangement=(TextView) findViewById(R.id.rangement);
		
		gettimee			=(RelativeLayout) findViewById(R.id.deadall);
		categories          =(RelativeLayout) findViewById(R.id.categories);
		keywords            =(RelativeLayout) findViewById(R.id.keywords);
		rangements          =(RelativeLayout) findViewById(R.id.rangements);
		searchbutt=(Button) findViewById(R.id.searchbutton);
		
		
		final Builder builder = new AlertDialog.Builder(this);
		
		gettimee.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				calendar = Calendar.getInstance();
				dialog = new DatePickerDialog(HotelActivity.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								System.out.println("年-->" + year + "月-->"
										+ monthOfYear + "日-->" + dayOfMonth);
								getTime.setText(year + "/" + monthOfYear + "/"
										+ dayOfMonth);
							}
						}, calendar.get(Calendar.YEAR), calendar
								.get(Calendar.MONTH), calendar
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});
       
		gettime2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				calendar = Calendar.getInstance();
				dialog = new DatePickerDialog(HotelActivity.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								System.out.println("年-->" + year + "月-->"
										+ monthOfYear + "日-->" + dayOfMonth);
								getTime.setText(year + "/" + monthOfYear + "/"
										+ dayOfMonth);
							}
						}, calendar.get(Calendar.YEAR), calendar
								.get(Calendar.MONTH), calendar
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});
		

		

// 类别选择！！
		categories.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
	/*			   
	                Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(HotelActivity.this, tolerance.class);
	               
	                startActivityForResult(intent, 1010);

*/
	                 /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                 */
	                
	         	   AlertDialog.Builder builder = new AlertDialog.Builder(HotelActivity.this);
//	                builder.setIcon(R.drawable.ic_launcher);
	                builder.setTitle("选择一个类别");
	                //    指定下拉列表的显示数据
	                final String[] cities = {"娱乐", "公益", "出版", "收藏", "农业","动漫游戏","科学/技术"};
	                //    设置一个下拉的列表选择项
	                builder.setItems(cities, new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
//	                        Toast.makeText(HotelActivity.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
	                    	categoryy.setText(cities[which]);
	                    }
	                });
	                builder.show();
	                
	            }
	        });
		
		
		
		
		
		
		
		keywords.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
				   /*
	                Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(HotelActivity.this, keywords.class);
	                /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                 
	                startActivityForResult(intent, 1000);
	                */

				   AlertDialog.Builder builder = new AlertDialog.Builder(HotelActivity.this);
//	                builder.setIcon(R.drawable.ic_launcher);
	                builder.setTitle("请输入关键字");
	                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
	                View view = LayoutInflater.from(HotelActivity.this).inflate(R.layout.dialogg, null);
	                //    设置我们自己定义的布局文件作为弹出框的Content
	                builder.setView(view);
	                
	                final EditText keyyy = (EditText)view.findViewById(R.id.keyyy);
	                
	                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        String a = keyyy.getText().toString().trim();
	                        keyword.setText(a);
	                      
	                    } 
	                });
	                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        
	                    }
	                });
	                builder.show();
				   
				   
				   
				   
				   
	            }
	        });
		    
		
//		金额选择！！！！
		rangements.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
	/*			   
	                Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(HotelActivity.this, tolerance.class);
	               
	                startActivityForResult(intent, 1010);

*/
	                 /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                 */
	                
	         	   AlertDialog.Builder builder = new AlertDialog.Builder(HotelActivity.this);
//	                builder.setIcon(R.drawable.ic_launcher);
	                builder.setTitle("选择一个类别");
	                //    指定下拉列表的显示数据
	                final String[] cities = {"500以下", "500-1000", "1000-2000", "2000-4000", "4000-7000","7000-10000","10000以上"};
	                //    设置一个下拉的列表选择项
	                builder.setItems(cities, new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
//	                        Toast.makeText(HotelActivity.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
	                    	rangement.setText(cities[which]);
	                    }
	                });
	                builder.show();
	                
	            }
	        });
		
		
		    
		title	=(TextView) findViewById(R.id.title_text);
		title.setText("项目浏览");
		search			=(RelativeLayout) findViewById(R.id.hote_search);
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent			=new Intent();
				intent.setClass(getApplicationContext(), HotelSearch.class);
				startActivity(intent);
			}
		});
		
		
//		最下面搜索按钮的
		searchbutt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent			=new Intent();
				intent.setClass(getApplicationContext(), HotelSearch.class);
				startActivity(intent);
			}
		});
		
		
		
		layout			=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	
	
//	在oncreate 外面
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && resultCode == 1001)
        {
            String result_value = data.getStringExtra("result");
            keyword.setText(result_value);
        }
        
        if(requestCode == 1100 && resultCode == 1101)
        {
            String result_value = data.getStringExtra("result");
            categoryy.setText(result_value);
        }
        
        if(requestCode == 1010 && resultCode == 0101)
        {
            String result_value = data.getStringExtra("result");
            rangement.setText(result_value);
        }
        
    }
    

    
    
    
}
