package com.spring.usecenter;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.spring.gowhere.R;

public class LoginActivity extends Activity implements OnClickListener, ICallbackListener {
	private Context context = this;
	
	private final String HTTP_SEARCH = "https://openapi.boc.cn/bocop/appfindusrinfo";
	
	//private final String HTTP_ADDCARD = "https://opendtp.boc.cn/app/adduserinfo";

	private Button btn_login, btn_loginOut;
	private TextView tv_userid;
	private RelativeLayout lyt_card,lyt_project,lyt_about,lyt_experience,lyt_product,lyt_income;
	private LinearLayout layout;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uc);
		TextView textView	=(TextView)findViewById(R.id.title_text);
		textView.setText("个人中心");
		
		layout			=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		btn_login = (Button) findViewById(R.id.login);
		btn_loginOut = (Button) findViewById(R.id.loginOut);
		tv_userid = (TextView) findViewById(R.id.userid);
		lyt_card = (RelativeLayout)findViewById(R.id.lyt_card);		
		lyt_product = (RelativeLayout)findViewById(R.id.lyt_product);		
		lyt_project = (RelativeLayout)findViewById(R.id.lyt_project);
		lyt_income = (RelativeLayout)findViewById(R.id.lyt_income);
		lyt_experience = (RelativeLayout)findViewById(R.id.lyt_experience);
		lyt_about = (RelativeLayout)findViewById(R.id.lyt_about);
		tv_userid.setText(NetworkConstant.userid+"access:"+NetworkConstant.access_token);
		if(NetworkConstant.userid == "" || NetworkConstant.userid == null|| NetworkConstant.userid == "null"){
			btn_login.setVisibility(View.VISIBLE);
			btn_loginOut.setVisibility(View.GONE);
			tv_userid.setText("您还没有登录轻松理哦~~~");
		}else{
			btn_loginOut.setVisibility(View.VISIBLE);
			btn_login.setVisibility(View.GONE);
			tv_userid.setText(NetworkConstant.userid+",欢迎您来到轻松理~~~");
		}
		btn_login.setOnClickListener(this);
		btn_loginOut.setOnClickListener(this);
		lyt_card.setOnClickListener(this);
		lyt_product.setOnClickListener(this);
		lyt_project.setOnClickListener(this);
		lyt_income.setOnClickListener(this);
		lyt_experience.setOnClickListener(this);
		lyt_about.setOnClickListener(this);

	}
	/**
	 * 自定义接受消息的Handler
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case R.id.login: // 收到登录成功消息
				btn_loginOut.setVisibility(View.VISIBLE);
				btn_login.setVisibility(View.GONE);
				tv_userid.setText(NetworkConstant.userid+",欢迎您来到轻松理~~~");
				//showDialog("登录成功！");
				break;
			case R.id.loginOut: // 收到登录成功消息
				btn_login.setVisibility(View.VISIBLE);
				btn_loginOut.setVisibility(View.GONE);
				tv_userid.setText("您还没有登录轻松理哦~~~");
				showDialog("退出登录！");
				break;
			}
		}
	};
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:// 登录Button
			authorize();
			break;
		case R.id.loginOut:// 退出登录Button
			logoutApp() ;
			break;
		case R.id.lyt_card:
			if(NetworkConstant.userid == "" || NetworkConstant.userid == null || NetworkConstant.userid == "null"){
				authorize();
				break;
			}
			else{
				request();
				//add();
				/*Intent intentMyCard = new Intent(this,MyCardActivity.class);
				startActivity(intentMyCard);*/
				break;
			}
		case R.id.lyt_product:
			if(NetworkConstant.userid == "" || NetworkConstant.userid == null || NetworkConstant.userid == "null"){
				authorize();
				break;
			}
			else{
				Intent intentProduct = new Intent(this,ProductActivity.class);
				startActivity(intentProduct);
				break;
			}
		case R.id.lyt_project:
			if(NetworkConstant.userid == "" || NetworkConstant.userid == null || NetworkConstant.userid == "null"){
				authorize();
				break;
			}
			else{
				Intent intentProject = new Intent(this,ProjectActivity.class);
				startActivity(intentProject);
				break;
			}
		case R.id.lyt_income:
			if(NetworkConstant.userid == "" || NetworkConstant.userid == null || NetworkConstant.userid == "null"){
				authorize();
				break;
			}
			else{
				Intent intentIncome = new Intent(this,IncomeActivity.class);
				startActivity(intentIncome);
				break;
			}
		case R.id.lyt_experience:
			if(NetworkConstant.userid == "" || NetworkConstant.userid == null || NetworkConstant.userid == "null"){
				authorize();
				break;
			}
			else{
				Intent intentExperience = new Intent(this,MyExperienceActivity.class);
				startActivity(intentExperience);
				break;
			}
		case R.id.lyt_about:
			Intent intentAbout = new Intent(this,AboutActivity.class);
			startActivity(intentAbout);
			break;
		default:
			break;
		}

	}

	/**
	 * 登录
	 */
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
				handler.sendEmptyMessage(R.id.login);
			}
			@Override
			public void onCancel() {
			}
		});
	}

	/**
	 * 退出登录
	 */
	public void logoutApp() {
		BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
				NetworkConstant.CONSUMER_KEY, NetworkConstant.CONSUMER_SECRET);
		bocopSDKApi.delOAuthorize(context);
		//清空用户及token缓存
		NetworkConstant.access_token =""; 
		NetworkConstant.userid ="";
		handler.sendEmptyMessage(R.id.loginOut);
	}



	/**
	 * 对话框提示
	 * 
	 * @param msg
	 */
	private void showDialog(String msg) {

		AlertDialog.Builder adb = new AlertDialog.Builder(LoginActivity.this);
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
	
	private void request() {
		  HTTP_Request http = new HTTP_Request();
		  Map<String, Object>  jsonMap = new HashMap<String, Object>();
		  jsonMap.put("pageno",1);//上传的参数
		  String json = JsonUtils.convertToJson(jsonMap);
		  http.sendPostRequest(json,this,HTTP_SEARCH);
	}


	/**
	 * 请求回调
	 */
	@Override
	public void callback(Integer statValue, String url, String str) {
		/*tv_userid.setText("返回报文：\n"+str);
		showDialog("返回成功！");*/
		Intent intentMyCard = new Intent();
		intentMyCard.setClass(LoginActivity.this,MyCardActivity.class);
		Bundle bundle = new Bundle(); 
		bundle.putString("str", str);     //装入数据   
		intentMyCard.putExtras(bundle);             
		startActivity(intentMyCard);
	}
}
