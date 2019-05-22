package com.example.ttms;

import java.util.ArrayList;
import java.util.List;

import com.example.database.MyDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class OrderActivity extends Activity{
	private MyDatabaseHelper dbHelper;
	private List<String> order = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_order);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		final SharedPreferences  sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		String username = sp.getString("username","NO_VALUE");
		dbHelper = new MyDatabaseHelper(this,"UserStor1.db",null,2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor  = db.rawQuery("select * from OrderStor where username='"+username+"';", null);
		while(cursor.moveToNext()) {		
			String Stime = cursor.getString(cursor.getColumnIndex("time"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String money = cursor.getString(cursor.getColumnIndex("money"));
			String res = Stime+" "+name+" "+money;
			order.add(res);				
		}
			
		ArrayAdapter<String> adpter = new ArrayAdapter<String>(OrderActivity.this, android.R.layout.simple_list_item_1,order);
		ListView listView = (ListView)findViewById(R.id.list_view1);
		listView.setAdapter(adpter);
		
		Button retMain = (Button)findViewById(R.id.retMain);
		retMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderActivity.this,PersonalActivity.class);
				startActivity(intent);		
			}
		});
	}

}
