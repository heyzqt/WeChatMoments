package com.heyzqt.wechatmoments.util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class OkHttpUtils {

	private static OkHttpUtils sOkHttpUtils;
	private static OkHttpClient okHttpClient;
	private Handler mHandler;

	private OkHttpUtils() {
		okHttpClient = new OkHttpClient();
		okHttpClient.newBuilder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.readTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS);
		mHandler = new Handler(Looper.getMainLooper());
	}

	private static OkHttpUtils getInstance() {
		if (sOkHttpUtils == null) {
			sOkHttpUtils = new OkHttpUtils();
		}
		return sOkHttpUtils;
	}


	/**
	 * 异步GET请求
	 *
	 * @param url      url
	 * @param params   key value
	 * @param callBack data
	 */
	public static void getFormConn(String url, Map<String, String> params, DataCallBack callBack)
			throws IOException {
		getInstance().innerGetFormAsync(url, params, callBack);
	}

	/**
	 * 异步POST请求
	 *
	 * @param url      url
	 * @param params   params
	 * @param callBack data
	 */
	public static void postFormConn(String url, Map<String, String> params, DataCallBack callBack)
			throws IOException {
		getInstance().innerPostFormAsync(url, params, callBack);
	}

	/**
	 * 异步GET请求 内部实现
	 *
	 * @param url      url
	 * @param params   key value
	 * @param callBack data
	 */
	private void innerGetFormAsync(String url, Map<String, String> params,
			final DataCallBack callBack) {
		if (params == null) {
			params = new HashMap<>();
		}

		String doUrl = urlJoint(url, params);
		//Create a new Request
		final Request request = new Request.Builder()
				.url(doUrl)
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				deliverDataFailure(request, e, callBack);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String result = response.body().string();
					deliverDataSuccess(result, callBack);
				} else {
					throw new IOException(response + "");
				}
			}
		});
	}

	/**
	 * 异步POST请求 内部实现
	 *
	 * @param url      url
	 * @param params   param
	 * @param callBack data
	 */
	private void innerPostFormAsync(String url, Map<String, String> params,
			final DataCallBack callBack) {
		if (params == null) {
			params = new HashMap<>();
		}


		RequestBody requestBody;
		FormBody.Builder builder = new FormBody.Builder();

		/**
		 * 遍历参数
		 */
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (value == null) {
				value = "";
			}
			builder.add(key, value);
		}
		requestBody = builder.build();

		final Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				deliverDataFailure(request, e, callBack);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					try {
						String result = response.body().toString();
						deliverDataSuccess(result, callBack);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					throw new IOException(response + "");
				}
			}
		});
	}

	/**
	 * Connect Internet failed
	 *
	 * @param request  request
	 * @param e        e
	 * @param callBack callback
	 */
	private void deliverDataFailure(final Request request, final IOException e,
			final DataCallBack callBack) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				if (callBack != null) {
					callBack.requestFailure(request, e);
				}
			}
		});
	}

	/**
	 * Connect Internet successed.
	 *
	 * @param result   result
	 * @param callBack callback
	 */
	private void deliverDataSuccess(final String result, final DataCallBack callBack) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				if (callBack != null) {
					try {
						callBack.requestSuccess(result);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	private String urlJoint(String url, Map<String, String> params) {
		StringBuilder result = new StringBuilder(url);
		boolean isFirst = true;

		Set<Map.Entry<String, String>> entrySet = params.entrySet();
		for (Map.Entry<String, String> entry : entrySet) {
			if (isFirst && !url.contains("?")) {
				result.append("?");
				isFirst = false;
			} else {
				result.append("&");
			}
			result.append(entry.getKey());
			result.append("=");
			result.append(entry.getValue());
		}
		return result.toString();
	}

	public interface DataCallBack {
		void requestSuccess(String result) throws Exception;

		void requestFailure(Request request, IOException e);
	}
}
