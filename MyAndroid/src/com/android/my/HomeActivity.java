package com.android.my;

import android.app.Activity;
import android.os.*;
import android.webkit.*;
import android.widget.*;
import android.view.View;

public class HomeActivity extends Activity {
	
	private WebView myWebView = null;
	private MyWebViewClient myWebViewClient = null;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置标题显示进度条
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //getWindow().requestFeature(Window.FEATURE_PROGRESS);
        
        setContentView(R.layout.home);
        
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setInitialScale(30);
        // 获取 WebSetting 
        WebSettings webSettings = myWebView.getSettings();
         
        // 开启Web View对JavaScript的支持
        webSettings.setJavaScriptEnabled(true);
        
        myWebViewClient = new MyWebViewClient(HomeActivity.this);
        myWebView.setWebViewClient(myWebViewClient);
        myWebView.loadUrl("http://3g.tuan588.com/");
        
        //setProgressBarIndeterminateVisibility(true);    	 
        //setProgressBarIndeterminateVisibility(false);
        
        Button button1 = (Button)this.findViewById(R.id.Button1);  
        button1.setOnClickListener(new View.OnClickListener() {                
            @Override  
            public void onClick(View v) {  
                HomeActivity.this.finish();  //关闭Activity  
            }  
        });
       
    }
    
    @Override
    protected void onDestroy() {
    	myWebView.destroy();
        super.onDestroy();
    }
    
}
