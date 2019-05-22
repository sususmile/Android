package com.example.ttms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CinemaActivity extends Activity{
	private String[] cinema = {"影院一","影院二","影院三","影院四","影院五","影院六","影院七","影院八","影院九","影院十","影院十一","影院十二","影院十三","影院十四","影院十五","影院十六"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_cinema);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		
		ArrayAdapter<String> adpter = new ArrayAdapter<String>(CinemaActivity.this, android.R.layout.simple_list_item_1,cinema);
		ListView listView = (ListView)findViewById(R.id.list_view);
		listView.setAdapter(adpter);
		
		Button hot = (Button)findViewById(R.id.hot);
	       hot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CinemaActivity.this,LoginActivity.class);
				startActivity(intent);		
			}
		});
	       
       Button person = (Button)findViewById(R.id.personal);
       person.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(CinemaActivity.this,PersonalActivity.class);
			startActivity(intent);		
		}
	});
	}

}
