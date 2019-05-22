package com.example.ttms;

import com.example.database.MyDatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BoughtActivity extends Activity{
	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_bought);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		dbHelper = new MyDatabaseHelper(this,"UserStor1.db",null,2);
		final SharedPreferences  sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		Button but1 = (Button)findViewById(R.id.button1);	
		Button but2 = (Button)findViewById(R.id.button2);	
		but1.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				String username = sp.getString("username","NO_VALUE");			
				TextView money1 = (TextView)findViewById(R.id.money1);
				TextView startTime1 = (TextView)findViewById(R.id.startTime1);	
					
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
						
				values.put("name", "复仇者联盟4");
				values.put("money", money1.getText().toString());
				values.put("time", startTime1.getText().toString());
				values.put("username", username);
				db.insert("OrderStor", null, values);
				Toast.makeText(BoughtActivity.this, "Order succeeded", Toast.LENGTH_SHORT).show();
			}
		});
		but2.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				String username = sp.getString("username","NO_VALUE");				
				TextView money2 = (TextView)findViewById(R.id.money2);	
				TextView startTime2 = (TextView)findViewById(R.id.startTime2);	
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
						
				values.put("name", "复仇者联盟4");
				values.put("money", money2.getText().toString());
				values.put("time", startTime2.getText().toString());
				values.put("username", username);
				db.insert("OrderStor", null, values);
				Toast.makeText(BoughtActivity.this, "Order succeeded", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
