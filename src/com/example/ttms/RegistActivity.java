package com.example.ttms;

import com.example.database.MyDatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends Activity{
	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_regist);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		dbHelper = new MyDatabaseHelper(this,"UserStor1.db",null,2);
		
		TextView reLogin = (TextView)findViewById(R.id.reLogin); 
		Button regist = (Button)findViewById(R.id.regist);
		reLogin.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {			
				Intent intent = new Intent(RegistActivity.this,LoginInActivity.class);
				startActivity(intent);				
			}
		});
		
		regist.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
//				Intent intent = new Intent(RegistActivity.this,MainActivity.class);
				TextView username = (TextView)findViewById(R.id.userName);
				TextView password = (TextView)findViewById(R.id.password);
				String user = username.getText().toString();
				String pwd = password.getText().toString();
				Cursor cursor  = db.rawQuery("select * from User where username='"+user+"'and password='"+pwd+"';", null);
				if(cursor.getCount()==0) {
					values.put("username", user);
					values.put("password", pwd);
					values.put("imagePath", 0);
					db.insert("User", null, values);
					Toast.makeText(RegistActivity.this, "regist succeeded", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(RegistActivity.this, "regist refused", Toast.LENGTH_SHORT).show();
				}
//				startActivity(intent);
				
			}
		});
	}

}
