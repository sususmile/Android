package com.example.ttms;

import com.example.database.MyDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalActivity extends Activity {
	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_personal);
		dbHelper = new MyDatabaseHelper(this,"UserStor1.db",null,2);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		final SharedPreferences  sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		TextView user = (TextView)findViewById(R.id.username);
		String username = sp.getString("username","NO_VALUE");
		String password = sp.getString("password", "NO_VALUE");
		user.setText(username);
		
		 Button cinema = (Button)findViewById(R.id.cinema);
	       cinema.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PersonalActivity.this,CinemaActivity.class);
				startActivity(intent);		
			}
		});
	       
	       Button hot = (Button)findViewById(R.id.hot);
	       hot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(PersonalActivity.this,LoginActivity.class);
				startActivity(intent1);		
			}
		});
	       
	       TextView discount = (TextView)findViewById(R.id.discount);
			discount.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(PersonalActivity.this,DiscountActivity.class);
					startActivity(intent);
				}
			});
			
			 TextView order = (TextView)findViewById(R.id.myOrder);
				order.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(PersonalActivity.this,OrderActivity.class);
						startActivity(intent);
					}
				});
				
			 TextView changeInfo = (TextView)findViewById(R.id.changeInfo);
				changeInfo.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(PersonalActivity.this,ChangeInfoActivity.class);
						startActivity(intent);
					}
				});
				
			TextView changePwd = (TextView)findViewById(R.id.changePwd);
			changePwd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(PersonalActivity.this,ChangePwdActivity.class);
					startActivity(intent);
				}
			});
			
			TextView LogOut = (TextView)findViewById(R.id.logOut);
			LogOut.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(PersonalActivity.this,LoginInActivity.class);
					sp.edit().clear().commit();
					startActivity(intent);
				}
			});
			
			ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Log.d("username",username);
			Log.d("password",password);
			Cursor cursor  = db.rawQuery("select * from User where username='"+username+"'and password='"+password+"';", null);
			if(cursor.moveToFirst()) {
				String imagePath = cursor.getString(3);
				Log.d("PersonalActivity", imagePath);
				imageView1.setImageBitmap(BitmapFactory.decodeFile(imagePath));
			}			
			
	}

}
