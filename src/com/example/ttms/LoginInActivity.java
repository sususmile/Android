package com.example.ttms;

import com.example.database.MyDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginInActivity extends Activity {	
	private MyDatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_main);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		dbHelper = new MyDatabaseHelper(this,"UserStor1.db",null,2);
		final SharedPreferences  sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		final SharedPreferences.Editor editor = sp.edit();
		
		
		Button login = (Button)findViewById(R.id.login);	
		TextView fpwd = (TextView)findViewById(R.id.findPwd);
		TextView regist = (TextView)findViewById(R.id.regist);
		
		
        fpwd.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginInActivity.this,FPwdActivity.class);
				startActivity(intent);
				
			}

			
		});
        regist.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginInActivity.this,RegistActivity.class);
				startActivity(intent);
				
			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				TextView username = (TextView)findViewById(R.id.editUsername);
				TextView password = (TextView)findViewById(R.id.editPwd);
				String user = username.getText().toString();
				String pwd = password.getText().toString();
				Cursor cursor  = db.rawQuery("select * from User where username='"+user+"'and password='"+pwd+"';", null);
				if(cursor.getCount()==0) {
					Toast.makeText(LoginInActivity.this, "用户名或密错误", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(LoginInActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(LoginInActivity.this,LoginActivity.class);
					editor.putString("username",user);
					editor.putString("password",pwd);
					editor.commit();
					startActivity(intent);
				}
				cursor.close();				
			}
			
		});
	}	
	
}