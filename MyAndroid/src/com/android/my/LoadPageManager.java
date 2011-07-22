package com.android.my;

import java.io.*;
import android.app.Activity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;

public class LoadPageManager {
	
	private HttpClient mHttpclient = null;
	private Activity mActivity = null;
	private String mHtmlData = "";
	
	public LoadPageManager(Activity activity){
		mActivity = activity;
	}
	
	//load loaction file
	public String loadPage(InputStream fileName) throws IOException {
		BufferedReader br = null;
		mHtmlData = "";
		try {
			br = new BufferedReader(new InputStreamReader(fileName));
			String data = "";
			while((data = br.readLine())!=null)
			{
				mHtmlData += data;
			}
			br.close();
			
		}
		catch(IOException e){
			throw e;
		}
		finally {
		     if (br != null) {
		    	 br.close();
		     }
		}
		return mHtmlData;
	}
	

	public String loadHttpData(String url) throws IOException{
		mHtmlData = "";
		if(mHttpclient==null){
			mHttpclient = new DefaultHttpClient();
		}
		
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Content-Type","text/html; charset=UTF-8");
		HttpResponse response;

		BufferedReader real_response = null;
		try {
			response = mHttpclient.execute(httpget);
			real_response = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        }
		catch (IOException e) {
			throw e;
        }
		
        if (real_response != null){
                String data = "";
                try {
                    while((data = real_response.readLine()) != null){
                    	mHtmlData += data;
                    }
                } catch (IOException e) {
                	throw e;
                }
        }
		
		return mHtmlData;
	}
	
}
