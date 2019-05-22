package com.example.ttms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class LaunchActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//隐藏状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 setContentView(R.layout.activity_lanuch);
	        Integer time = 5000;    //设置等待时间，单位为毫秒
	        Handler handler = new Handler();
	        //当计时结束时，跳转至主界面
	        handler.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                startActivity(new Intent(LaunchActivity.this, LoginInActivity.class));
	                LaunchActivity.this.finish();
	            }
	        }, time);
	}

}
