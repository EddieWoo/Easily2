package com.spring.framgent;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spring.gowhere.R;
import com.spring.gowhere.newprojectok;
import com.spring.usecenter.MyExperienceActivity;

public class newproject extends Activity{
	
	
	private RelativeLayout categories00;
	private RelativeLayout deadlineline;
	private RelativeLayout projectname;
	private RelativeLayout goal,cardid;
	private RelativeLayout initiator;
	private RelativeLayout description;
	private RelativeLayout sure;
	private TextView descriptionwords;
	private TextView initiatorwords;
	private TextView goaldetail;
	private TextView deadlinetime;
	private TextView category00;
	private TextView projectnamewords;
	private Calendar calendar;// 用来装日期的
	private DatePickerDialog dialog;
	private Button surebutt;
	
	private LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newproject);
		
		layout			=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	
		descriptionwords = (TextView) findViewById(R.id.descriptionwords);
		initiatorwords= (TextView) findViewById(R.id.initiatorwords);
		goaldetail=(TextView) findViewById(R.id.goaldetail);
		deadlinetime=(TextView) findViewById(R.id.deadlinetime);
		category00=(TextView) findViewById(R.id.category00);
		projectnamewords=(TextView) findViewById(R.id.projectnamewords);
		surebutt=(Button) findViewById(R.id.myButton1);
		
		categories00			=(RelativeLayout) findViewById(R.id.categories00);
		deadlineline          =(RelativeLayout) findViewById(R.id.deadlineline);
		projectname            =(RelativeLayout) findViewById(R.id.projectname);
		goal          =(RelativeLayout) findViewById(R.id.goal);
		cardid          =(RelativeLayout) findViewById(R.id.cardid);
		initiator          =(RelativeLayout) findViewById(R.id.initiator);
		description          =(RelativeLayout) findViewById(R.id.description);
		
		
		
		deadlineline.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				calendar = Calendar.getInstance();
				dialog = new DatePickerDialog(newproject.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								System.out.println("年-->" + year + "月-->"
										+ monthOfYear + "日-->" + dayOfMonth);
								deadlinetime.setText(year + "/" + monthOfYear + "/"
										+ dayOfMonth);
							}
						}, calendar.get(Calendar.YEAR), calendar
								.get(Calendar.MONTH), calendar
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});
		
		
		projectname.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
				   /*
	                Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(newproject.this, keywords.class);
	                /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                
	                startActivityForResult(intent, 0001);
	                */
	                
	                AlertDialog.Builder builder = new AlertDialog.Builder(newproject.this);
//	                builder.setIcon(R.drawable.ic_launcher);
	                builder.setTitle("请输入项目名称");
	                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
	                View view = LayoutInflater.from(newproject.this).inflate(R.layout.dialogg, null);
	                //    设置我们自己定义的布局文件作为弹出框的Content
	                builder.setView(view);
	                
	                final EditText keyyy = (EditText)view.findViewById(R.id.keyyy);
	                
	                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        String a = keyyy.getText().toString().trim();
	                        projectnamewords.setText(a);
	                      
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
		
		
		goal.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
	                /*Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(newproject.this, keywords.class);
	                /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                 
	                startActivityForResult(intent, 0002);*/
	                
	                
	                
	                AlertDialog.Builder builder = new AlertDialog.Builder(newproject.this);
//	                builder.setIcon(R.drawable.ic_launcher);
	                builder.setTitle("请输入众筹目标");
	                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
	                View view = LayoutInflater.from(newproject.this).inflate(R.layout.dialogg, null);
	                //    设置我们自己定义的布局文件作为弹出框的Content
	                builder.setView(view);
	                
	                final EditText keyyy = (EditText)view.findViewById(R.id.keyyy);
	                
	                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        String a = keyyy.getText().toString().trim();
	                        goaldetail.setText(a);
	                      
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
		
		cardid.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
	                /*Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(newproject.this, keywords.class);
	                /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                 
	                startActivityForResult(intent, 0002);*/
	                
	                
	                
	                AlertDialog.Builder builder = new AlertDialog.Builder(newproject.this);
//	                builder.setIcon(R.drawable.ic_launcher);
	                builder.setTitle("请输入转账卡号");
	                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
	                View view = LayoutInflater.from(newproject.this).inflate(R.layout.dialogg, null);
	                //    设置我们自己定义的布局文件作为弹出框的Content
	                builder.setView(view);
	                
	                final EditText keyyy = (EditText)view.findViewById(R.id.keyyy);
	                
	                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        String a = keyyy.getText().toString().trim();
	                        goaldetail.setText(a);
	                      
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
		
		initiator.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
	               /* Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(newproject.this, keywords.class);
	                /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                
	                startActivityForResult(intent, 0003); */
	                
	                AlertDialog.Builder builder = new AlertDialog.Builder(newproject.this);
//	                builder.setIcon(R.drawable.ic_launcher);
	                builder.setTitle("请输入发起人");
	                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
	                View view = LayoutInflater.from(newproject.this).inflate(R.layout.dialogg, null);
	                //    设置我们自己定义的布局文件作为弹出框的Content
	                builder.setView(view);
	                
	                final EditText keyyy = (EditText)view.findViewById(R.id.keyyy);
	                
	                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
	                {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which)
	                    {
	                        String a = keyyy.getText().toString().trim();
	                        initiatorwords.setText(a);
	                      
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
		
		description.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
	               /* Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(newproject.this, keywords.class);
	                /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	               
	                startActivityForResult(intent, 0004);  */
	                

					   AlertDialog.Builder builder = new AlertDialog.Builder(newproject.this);
//		                builder.setIcon(R.drawable.ic_launcher);
		                builder.setTitle("请输入项目介绍的关键字");
		                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
		                View view = LayoutInflater.from(newproject.this).inflate(R.layout.dialogg, null);
		                //    设置我们自己定义的布局文件作为弹出框的Content
		                builder.setView(view);
		                
		                final EditText keyyy = (EditText)view.findViewById(R.id.keyyy);
		                
		                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		                {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which)
		                    {
		                        String a = keyyy.getText().toString().trim();
		                        descriptionwords.setText(a);
		                      
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
		
		categories00.setOnClickListener(new View.OnClickListener() {
			   @Override
	            public void onClick(View v)
	            {
				   /*
	                Intent intent = new Intent();
//	                intent.putExtra("message", editText1.getText().toString().trim() + " + " + editText2.getText().toString().trim() + " = ?");
	                intent.setClass(newproject.this, category.class);
	                /*
	                 * 如果希望启动另一个Activity，并且希望有返回值，则需要使用startActivityForResult这个方法，
	                 * 第一个参数是Intent对象，第二个参数是一个requestCode值，如果有多个按钮都要启动Activity，则requestCode标志着每个按钮所启动的Activity
	                
	                startActivityForResult(intent, 0005); */
	                
	                
	                AlertDialog.Builder builder = new AlertDialog.Builder(newproject.this);
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
	                    	category00.setText(cities[which]);
	                    }
	                });
	                builder.show();
	            }
	        });
		
		surebutt.setOnClickListener(
				new View.OnClickListener() {
		            public void onClick(View v)
		            {
					   AlertDialog.Builder builder = new AlertDialog.Builder(newproject.this);    
					   builder.setTitle("温馨小提示" );  
				       builder.setMessage("恭喜您，您发起项目成功，请静候佳音~~" );  
				       builder.setPositiveButton("确定", null);  
				       builder.show();
		            }
			}
			);
		
	}
	
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0001 && resultCode == 1001)
        {
            String result_value = data.getStringExtra("result");
            projectnamewords.setText(result_value);
        }
        
        if(requestCode == 0002 && resultCode == 1001)
        {
            String result_value = data.getStringExtra("result");
            goaldetail.setText(result_value);
        }
        
        if(requestCode == 0003 && resultCode == 1001)
        {
            String result_value = data.getStringExtra("result");
            initiatorwords.setText(result_value);
        }
        
        if(requestCode == 0004 && resultCode == 1001)
        {
            String result_value = data.getStringExtra("result");
            descriptionwords.setText(result_value);
        }
        if(requestCode == 0005 && resultCode == 1101)
        {
            String result_value = data.getStringExtra("result");
            category00.setText(result_value);
        }
        
        
        
    }
    
	
	
	
	
}
