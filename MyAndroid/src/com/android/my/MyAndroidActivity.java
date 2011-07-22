package com.android.my;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.webkit.*;
import android.widget.*;
import android.view.View;
import android.view.View.*;

public class MyAndroidActivity extends Activity {
	
	private WebView myWebView = null;
	private LoadPageManager myLoadPageManager = null;
	private MyWebViewClient myWebViewClient = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        myWebView = (WebView) findViewById(R.id.webview);
        
        // 获取 WebSetting 
        WebSettings webSettings = myWebView.getSettings();
        
        // 开启Web View对JavaScript的支持
        webSettings.setJavaScriptEnabled(true);
        
        //myWebView.setHapticFeedbackEnabled(false);
        
        myWebViewClient = new MyWebViewClient(MyAndroidActivity.this);
        myLoadPageManager = new LoadPageManager(MyAndroidActivity.this);
        myWebView.setInitialScale(30);
        myWebView.setWebViewClient(myWebViewClient);
        
        try{
			String source = myLoadPageManager.loadPage(getResources().getAssets().open("html/index.html"));
			myWebView.loadDataWithBaseURL("http://3g.tuan588.com/",source,"text/html","UTF-8","about:blank");
		}
		catch(IOException e){
			myWebView.loadUrl("file:///android_asset/html/error.html");
		}
        
        Button button1 = (Button)findViewById(R.id.Button1);
        button1.setOnClickListener(myButton1Listener);
        
        Button button2 = (Button)findViewById(R.id.Button2);
        button2.setOnClickListener(myButton2Listener);
        
        Button button3 = (Button)findViewById(R.id.Button3);
        button3.setOnClickListener(myButton3Listener);
        
        Button button4 = (Button)findViewById(R.id.Button4);
        button4.setOnClickListener(myButton4Listener);

    }
    
    private OnClickListener myButton1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			myWebView = (WebView) findViewById(R.id.webview);
			myWebView.setInitialScale(30);
			myWebViewClient.showProgressDialog();
			myWebView.setWebViewClient(myWebViewClient);
			
			try{
				//String source = myLoadPageManager.loadPage(getResources().getAssets().open("html/index.html"));
				String source = myLoadPageManager.loadHttpData("http://3g.tuan588.com/");
				myWebView.loadDataWithBaseURL("http://3g.tuan588.com/",source,"text/html","UTF-8","about:blank");
			}
			catch(IOException e){
				myWebView.loadUrl("file:///android_asset/html/error.html");
			}
			
		}
    };
    
    private OnClickListener myButton2Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			myWebView = (WebView) findViewById(R.id.webview);
			myWebView.setInitialScale(30);
			myWebView.getSettings().setJavaScriptEnabled(true);
			myWebView.getSettings().setPluginsEnabled(true);
			myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
			myWebView.getSettings().setSupportMultipleWindows(true);
			myWebView.setWebViewClient(myWebViewClient);
			WebChromeClient client = new WebChromeClient();
			myWebView.setWebChromeClient(client);
			
			try{
				String source = myLoadPageManager.loadPage(getResources().getAssets().open("html/issue.html"));
				myWebView.loadDataWithBaseURL("http://weibo.tuan588.com/",source,"text/html","UTF-8","about:blank");
			}
			catch(IOException e){
				myWebView.loadUrl("file:///android_asset/html/error.html");
			}
		}
    };
    
    private OnClickListener myButton3Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			myWebView = (WebView) findViewById(R.id.webview);
			myWebView.setInitialScale(30);
			myWebView.setWebViewClient(myWebViewClient);
		    myWebView.loadUrl("file:///android_asset/index.html");
		}
    };
    
    private OnClickListener myButton4Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 创建显式意图
            Intent intent = new Intent(MyAndroidActivity.this, HomeActivity.class);
			//Intent intent = new Intent("com.android.my.HomeActivity");
            // 打开新的Activity
            startActivity(intent);
		}
    };
    
    @Override
    protected void onDestroy() {
    	myWebView.destroy();
        super.onDestroy();
    }
}