package com.spring.gowhere;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.spring.usecenter.HTTP_Request;
import com.spring.usecenter.ICallbackListener;
import com.spring.usecenter.JsonUtils;
import com.spring.usecenter.LoginActivity;
import com.spring.usecenter.MyCardActivity;
import com.spring.usecenter.NetworkConstant;

public class payment extends Activity implements OnClickListener,ICallbackListener{
	
	private final String HTTP_SEARCH = "https://opendtp.boc.cn/bocop/appfindusrinfo";
	
	private static final char SPACE = 0x20;
	
	private LinearLayout layout;
	
	
	private EditText account1;
	private Button submit;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		
		layout = (LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		submit = (Button) findViewById(R.id.payment);
		submit.setOnClickListener(this);
		
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case R.id.payment: // 收到登录成功消息
				account1 = null;
				account1 = (EditText) findViewById(R.id.account1);//我要转账的卡号
				String account = account1.getText().toString();//转账账户
				account = removeAllSpace(account);
				request(account);
				break;
			}
		}
	};
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.payment:
			if(NetworkConstant.userid == "" || NetworkConstant.userid == null || NetworkConstant.userid == "null"){
				//showDialog("亲爱的用户，您还没有登录或者系统正在识别你的账户~~~");
				authorize();
				break;
			}
			else{
				showDialog("亲爱的用户，正在为您处理交易，请稍候~~~");
				account1 = (EditText) findViewById(R.id.account1);//我要转账的卡号
				String account = account1.getText().toString();//转账账户
				account = removeAllSpace(account);
				request(account);
				break;
			}
		default:
			break;
		}

	}
	
	private void request(String accno) {
		 HTTP_Request http = new HTTP_Request();
		  Map<String, Object>  jsonMap = new HashMap<String, Object>();
		  jsonMap.put("accno",accno);
		  jsonMap.put("pageno",1);
		  String json = JsonUtils.convertToJson(jsonMap);
		  http.sendPostRequest(json,this,HTTP_SEARCH);
	}
	
	
	/** 
     * 去除字符串中所有空格 
     */  
    public String removeAllSpace(String s) {  
        String endString = "";  
        StringBuilder builder = new StringBuilder(endString);  
        int len = s.length();  
        for (int i = 0; i <len ; i++) {  
            // 获得字符  
            char c = s.charAt(i);  
            // 如果该字符不是空格  
            if (c != SPACE) {  
                builder.append(String.valueOf(c));  
            }  
        }  
        return builder.toString();  
    }  
    
    private void authorize() {
		//Log.i("msg", context.toString());
		BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(this, NetworkConstant.CONSUMER_KEY,
				NetworkConstant.CONSUMER_SECRET);
		// 设置等参数      上下文，           登录地址，          登录端口，          是否显示注册按钮，        注册地址
		bocopSDKApi
				.initURLIPPort(this, NetworkConstant.LOGIN_SVR, NetworkConstant.HTTPS_PORT, true, NetworkConstant.SIGNUP_URL);
		// 登录SDK回调接口
		bocopSDKApi.authorize(this, new ResponseListener() {
			@Override
			public void onException(Exception arg0) {
			}
			@Override
			public void onError(Error arg0) {
			}
			@Override
			public void onComplete(ResponseBean response) {
				// 这是登陆成功回调
				BOCOPOAuthInfo info = (BOCOPOAuthInfo) response;
				NetworkConstant.access_token = info.getAccess_token();// 登录成功产生的token
				NetworkConstant.userid = info.getUserId();// 登录的用户名
				handler.sendEmptyMessage(R.id.payment);
			}
			@Override
			public void onCancel() {
			}
		});
	}
    
    private void showDialog(String msg) {

		AlertDialog.Builder adb = new AlertDialog.Builder(payment.this);
		adb.setTitle("温馨小提示");
		adb.setMessage(msg);
		adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		adb.create().show();
	}

	/**
	 * 请求回调
	 */
	@Override
	public void callback(Integer statValue, String url, String str) {
		
		Intent intentMyCard = new Intent();
		intentMyCard.setClass(payment.this,TransferActivity.class);
		Bundle bundle = new Bundle(); 
		bundle.putString("str", str);     //装入数据   
		intentMyCard.putExtras(bundle);             
		startActivity(intentMyCard);
	}

}
