package com.example.webviewtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

/**
 * @author zsj
 * @date 2016年6月25日上午10:05:31
 * @function 实现html和Android的交互
 */
public class MainActivity extends Activity {

	private WebView mWebView;

	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mWebView = (WebView) findViewById(R.id.webview);
		// mWebView.loadUrl("http://www.baidu.com");
		mWebView.loadUrl("file:///android_asset/webview.html");

		// 开启javascript的函数
		mWebView.getSettings().setJavaScriptEnabled(true);
		// 将一个java对象传递到html页面中,"plugin"--->html页面中调用的那个类
		mWebView.addJavascriptInterface(new Plugin(), "plugin");
		
	}

	// js的Api有限，性能有限，所以要调用Android的
	public class Plugin {
		// public void call(){
		// Intent intent = new
		// Intent(Intent.ACTION_CALL,Uri.parse("tel:123456"));
		// startActivity(intent);
		// }
		public void call(String number) {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ number));
			startActivity(intent);
		}
	}
	
	public void use(View v){
		// 调用js函数,需要在html中写一个htmlMethod，这样的函数
		mWebView.loadUrl("javascript:htmlMethod('test');");
	}

}
