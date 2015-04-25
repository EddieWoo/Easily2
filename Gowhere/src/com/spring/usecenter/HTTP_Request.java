package com.spring.usecenter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;


public class HTTP_Request {
	/**
	 * 网络请求
	 * @param json  上传json 串
	 * @param ct  请求的Activity
	 * @param url  请求的地址
	 */
		public void sendPostRequest(String json, Context ct, String url) {
				BaseThread thread = new BaseThread(json, ct, url);
				thread.execute();
		}
		/**
		 * 异步网络请求
		 * @author Shan
		 *
		 */
		private class BaseThread extends AsyncTask<Integer, Integer, Integer> {
			ICallbackListener iCallback; // 回调接口
			String mJson;
			private String url;
			String recvStr = null;
			Context mCon;

			public BaseThread(String json, Context ct, String url) {
				this.mCon = ct;
				iCallback = (ICallbackListener) ct;
				this.mJson = json;
				this.url = url;
			}

			/**
			 * 联网前准备连接器 存放url和machine到hashmap
			 */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected Integer doInBackground(Integer... params) {
				try {
					recvStr = write(url, mJson);//请求
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
				}
				return 0;
			}
			@Override
			protected void onProgressUpdate(Integer... values) {
				super.onProgressUpdate(values);
			}
			@Override
			protected void onPostExecute(Integer result) {
				super.onPostExecute(result);
						iCallback.callback(result, url, recvStr);
			}
			@Override
			protected void onCancelled() {
				super.onCancelled();
			}
		}
		/**
		 * 增对post请求
		 * 
		 * @param url
		 *            请求的url
		 * @param json
		 *            请求参数
		 * @return 返回的json数据
		 */
		public String write(String url, String json) throws Exception {
			String ResStr = null;
			InputStream in = null;
			ByteArrayOutputStream out = null;
			HttpClient client = new DefaultHttpClient();
			 HttpResponse response;

			try {
				HttpPost post = new HttpPost(url);
				addHeader(post);
				if (!TextUtils.isEmpty(json)) {
					Log.d("request", "URL：" + url);
					Log.d("request", "上送报文："+json);
					if (!"{}".equals(json)) {
						StringEntity se = new StringEntity(json, "utf-8");
						se.setContentType("application/json");
						post.setEntity(se);
					}
				}
				response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						in = entity.getContent();
						out = new ByteArrayOutputStream();
						byte[] arr = new byte[1024 * 8];
						int len = 0;
						while ((len = in.read(arr)) != -1) {
							out.write(arr, 0, len);
							out.flush();
						}
						ResStr = out.toString(NetworkConstant.ENCODE);
					}
				}
				Log.d("request", "返回报文："+ResStr);
				return ResStr;
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (out != null) {
						out.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/**
		 * 为报文请求添加统一的报文头,其中这几个字段是必输的，其他为选择。
		 * 
		 * @param post2
		 */
		private void addHeader(HttpRequestBase request) {
			request.addHeader("clentid", NetworkConstant.CONSUMER_KEY);// 第三方应用KEY
			request.addHeader("userid", NetworkConstant.userid);// 第三方用户ID
			request.addHeader("acton", NetworkConstant.access_token);// 访问令牌
			request.addHeader("chnflg", "1");// 渠道标识  1-移动终端 2-PC终端
			request.addHeader("trandt", "20140916");// 交易日期    yyyymmdd
			request.addHeader("trantm", "151600");// 交易时间    hhmmss
		}
}
