package com.spring.usecenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.spring.gowhere.R;

public class SearchProductSearch extends ListActivity {
	private RelativeLayout layout_name;
	private RelativeLayout layout_time;
	private RelativeLayout layout_files;

	private TextView startTime;
	private TextView endTime;
	private int yearOfCalendar;
	private int monthOfYear;
	private int dayOfMonth;
	private static int INDEX_OF_DIALOG = 0;
	String dest = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.uc_searchprojectsearch);

		layout_name = (RelativeLayout) findViewById(R.id.file_search_by_name);
		layout_time = (RelativeLayout) findViewById(R.id.file_search_by_time);
		layout_files = (RelativeLayout) findViewById(R.id.file_list);
		layout_name.setVisibility(View.VISIBLE);// 0可见，4不可见(占位)，8gone
		layout_time.setVisibility(View.GONE);
		layout_files.setVisibility(View.GONE);

		// ---------------创建日历控件
		startTime = (TextView) findViewById(R.id.start_time);
		endTime = (TextView) findViewById(R.id.end_time);

		Calendar calendar = Calendar.getInstance();
		yearOfCalendar = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH) + 1;
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

		startTime
				.setText(yearOfCalendar + "-" + monthOfYear + "-" + dayOfMonth);
		endTime.setText(yearOfCalendar + "-" + monthOfYear + "-" + dayOfMonth);

		startTime.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				// 4.按钮监听事件，点击按钮时调用showDialog()方法。
				// 该方法会自动调用onCreateDialog()方法创建并显示一个DatePickerDialog。
				INDEX_OF_DIALOG = 0;
				showDialog(INDEX_OF_DIALOG);
			}
		});
		endTime.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				INDEX_OF_DIALOG = 1;
				showDialog(INDEX_OF_DIALOG);
			}
		});

		// ----------------创建list显示查询结果-

		ListView res_List = (ListView) findViewById(android.R.id.list);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.product1);// 图像资源的ID
		map.put("pname", "“中银智荟”2014年第261期");
		map.put("prate", "4.90%");
		map.put("ifn", "1000");
		remoteWindowItem.add(map);

		map = new HashMap<String, Object>();
		map.put("list_image", R.drawable.product1);// 图像资源的ID
		map.put("pname", "“中银汇增”2014年第19期");
		map.put("prate", "3.30%");
		map.put("ifn", "400");
		remoteWindowItem.add(map);


		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listItemAdapter = new SimpleAdapter(this,
				remoteWindowItem,// 数据源
				R.layout.product_ls,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "list_image", "pname", "prate", "ifn" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.list_image, R.id.pname, R.id.prate, R.id.ifn });
		res_List.setAdapter(listItemAdapter);

		// 点击两个查询按钮的监听事件, ········显示查询结果···································
		Button search_by_name_bt = (Button) findViewById(R.id.search_file_byname_bt);
		Button search_by_time_bt = (Button) findViewById(R.id.search_file_bytime_bt);
		search_by_time_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				layout_files.setVisibility(View.VISIBLE);
			}
		});
		search_by_name_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				layout_files.setVisibility(View.VISIBLE);
			}
		});

	}

	// 日历选择后，设定相应文本中的值
	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int month, int day) {// 这是日期被设定后进行的处理。这里比较简单，只是输出一个字符串。
			if (INDEX_OF_DIALOG == 0)
				startTime.setText(year + "-" + (month + 1) + "-" + day);
			else if (INDEX_OF_DIALOG == 1)
				endTime.setText(year + "-" + (month + 1) + "-" + day);

		}
	};

	// 创建选择日期的对话框
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			return new DatePickerDialog(this, onDateSetListener,
					yearOfCalendar, monthOfYear - 1, dayOfMonth);
		case 1:
			return new DatePickerDialog(this, onDateSetListener,
					yearOfCalendar, monthOfYear - 1, dayOfMonth);
		}
		return null;
	}

	// 选择查询条件
	public void show_search_way_dialog(View v) {
		new AlertDialog.Builder(SearchProductSearch.this)
				.setIcon(
						getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("请选择")
				.setSingleChoiceItems(new String[] { "已完成项目名称", "时间范围" }, 0,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								if (which == 0) {
									layout_name.setVisibility(View.VISIBLE);
									layout_time.setVisibility(View.GONE);
									layout_files.setVisibility(View.GONE);
								} else {
									layout_name.setVisibility(View.GONE);
									layout_time.setVisibility(View.VISIBLE);
									layout_files.setVisibility(View.GONE);
								}
							}
						}).setNegativeButton("取消", null).show();
	}
}
