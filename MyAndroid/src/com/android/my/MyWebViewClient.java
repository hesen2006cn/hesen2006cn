package com.android.my;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
	
	private Activity mActivity = null;

	private ProgressDialog progressDialog = null;
	
	public MyWebViewClient(Activity activity){
		mActivity = activity;
	}
	
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
    	/*
        if (Uri.parse(url).getHost().equals("www.sina.com.cn")) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }
       
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        return true;
        */
    	return false;
    }
    
    @Override
    public void onPageFinished (WebView view, String url){
    	System.out.println("onPageFinished: " + url);
    	dismissProgressDialog();
    }
    
    @Override
    public void onPageStarted (WebView view, String url, Bitmap favicon){
    	System.out.println("onPageStarted: " + url);
    	showProgressDialog();
    }
   
    @Override
    public void onReceivedError (WebView view, int errorCode, String description, String failingUrl){
    	System.out.println("onReceivedError: " + description);
    	dismissProgressDialog();
    }
    
    public void showProgressDialog (){
    	if(progressDialog==null || !progressDialog.isShowing()){
    		progressDialog = ProgressDialog.show(mActivity, "请稍等...", "获取数据中...", true);
    	}
    }
    public void dismissProgressDialog (){
    	if(progressDialog!=null && progressDialog.isShowing()){
    		progressDialog.dismiss();
    	}
    }
}
