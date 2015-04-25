package com.spring.gowhere;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spring.usecenter.HTTP_Request;
import com.spring.usecenter.ICallbackListener;
import com.spring.usecenter.JsonUtils;
import com.spring.usecenter.NetworkConstant;

public class TransferActivity extends Activity implements ICallbackListener {

	private LinearLayout layout;
	private RelativeLayout lyt_tracfee, lyt_trandate, lyt_trantime;

	private final String HTTP_TRANSFER = "https://opendtp.boc.cn/bocop/base/asr/cardtransfer";

	private TextView text, tracfee, trandate, trantime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deal);

		layout = (LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		initData();
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		String str = bundle.getString("str"); // 获取Bundle里面的字符串
		if (null != str && "" != str)
			dealStr(str);
	}

	/*
	 * 初始化数据
	 */
	private void initData() {

		lyt_tracfee = (RelativeLayout) findViewById(R.id.lyt_tracfee);
		lyt_trandate = (RelativeLayout) findViewById(R.id.lyt_trandate);
		lyt_trantime = (RelativeLayout) findViewById(R.id.lyt_trantime);
		text = (TextView) findViewById(R.id.text);
		tracfee = (TextView) findViewById(R.id.tracfee);
		trandate = (TextView) findViewById(R.id.trandate);
		trantime = (TextView) findViewById(R.id.trantime);

	}

	private void dealStr(String str) {

		Map<String, Object> mapStr = new HashMap<String, Object>();
		mapStr = JsonUtils.getMapObj(str);
		if (mapStr.containsKey("rtnmsg")) {
			Object obj_n = new Object();
			obj_n = mapStr.get("rtnmsg");
			if (obj_n.toString().equals("查询无记录")) {
				// tv_down.setText("您暂无此卡，请核对您的卡是否正确");
				text.setText("对不起，您暂无此卡，请核对卡号是否正确~");
				lyt_tracfee.setVisibility(View.GONE);
				lyt_trandate.setVisibility(View.GONE);
				lyt_trantime.setVisibility(View.GONE);
				return;
			}
		}
		Object obj = new Object();
		obj = mapStr.get("saplist");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = JsonUtils.getListMap(obj.toString());
		/*
		 * for(int i=0;i<list.size();i++){
		 * cardtransfer(list.get(i).get("accno").toString()); }
		 */
		if (list.size() > 0) {
			cardtransfer(list.get(0).get("accno").toString());
		}
	}

	/**
	 * 发送请求userid lmtamtout cardnumin amount currency expdate username
	 */
	private void cardtransfer(String lmtamtout) {
		HTTP_Request http = new HTTP_Request();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("userid", NetworkConstant.userid);
		jsonMap.put("lmtamtout", lmtamtout);
		jsonMap.put("cardnumin", "6217870700000000001");
		jsonMap.put("amount", 100);
		jsonMap.put("currency", "001");
		jsonMap.put("username", "cary32_test_773");
		String json = JsonUtils.convertToJson(jsonMap);
		http.sendPostRequest(json, this, HTTP_TRANSFER);
	}


	/**
	 * 请求回调
	 */
	@Override
	public void callback(Integer statValue, String url, String str) {
		Map<String, Object> mapStr = new HashMap<String, Object>();
		mapStr = JsonUtils.getMapObj(str);

		if (mapStr.containsKey("rtnmsg")) {
			Object obj_n = new Object();
			obj_n = mapStr.get("rtnmsg");
			text.setText("对不起，" + obj_n.toString() + "~");
			return;
		}

		//tracfee.setText(String.format("%.2f", mapStr.get("tracfee").toString())+"元");
		//转换了之后当前activity就很容易跳转？？
		tracfee.setText(Integer.parseInt(mapStr.get("tracfee").toString())/100 + "");
		trandate.setText(mapStr.get("trandate").toString());
		trantime.setText(mapStr.get("trantime").toString());


	}

}
