package com.spring.usecenter;
/*package com.spring.usecenter;

import java.util.HashMap;
import java.util.Map;

import com.spring.gowhere.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RequestActivity extends Activity implements OnClickListener,
		ICallbackListener {

	private Context context = this;
	*//** 牌价查询接口地址 *//*
	private final String HTTP_SEARCH = "http://openapi.boc.cn/bocop/rate/search";

	private Button btn_request;
	private TextView tv_up, tv_down;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request);
		initData();
	}

	*//**
	 * 初始化数据
	 *//*
	private void initData() {
		btn_request = (Button) findViewById(R.id.btn_request);
		tv_up = (TextView) findViewById(R.id.tv_up);
		tv_down = (TextView) findViewById(R.id.tv_down);
		btn_request.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_request:// 登录Button
			request();
			break;
		default:
			break;
		}

	}

	*//**
	 * 发送请求
	 *//*
	private void request() {
		HTTP_Request http = new HTTP_Request();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("crrncy", "000");// 上传的参数
		// jsonMap.put("crrncy", "000");//上传的参数
		// jsonMap.put("crrncy", "000");//上传的参数
		// jsonMap.put("crrncy", "000");//上传的参数
		String json = JsonUtils.convertToJson(jsonMap);
		tv_up.setText("上送报文：\n" + json);
		http.sendPostRequest(json, this, HTTP_SEARCH);
	}

	*//**
	 * 对话框提示
	 * 
	 * @param msg
	 *//*
	private void showDialog(String msg) {

		AlertDialog.Builder adb = new AlertDialog.Builder(RequestActivity.this);
		adb.setTitle(null);
		adb.setMessage(msg);
		adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		adb.create().show();
	}

	*//**
	 * 请求回调
	 *//*
	@Override
	public void callback(Integer statValue, String url, String str) {
		tv_down.setText("返回报文：\n" + str);
		showDialog("返回成功！");
	}
}
*/