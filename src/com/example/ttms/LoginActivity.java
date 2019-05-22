package com.example.ttms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private GridView imageList;
	private TextView description;
	private int[] pics;
	private String[] descs=new String[10];
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_login);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
     
		imageList=(GridView) findViewById(R.id.gridview);
        description=(TextView) findViewById(R.id.text);
        pics=new int[]{R.drawable.movie,R.drawable.movie,R.drawable.movie,R.drawable.movie,R.drawable.movie,R.drawable.movie,R.drawable.movie,R.drawable.movie,R.drawable.movie,R.drawable.movie};
		for(int i=0;i<descs.length;i++){
			descs[i]="影片"+(i+1);
		}
        List<Map<String,Object>> pic_items=new ArrayList<Map<String, Object>>();
        for(int i=0;i<pics.length;i++){
        	Map<String,Object> item=new HashMap<String, Object>();
        	item.put("pic", pics[i]);
        	item.put("desc",descs[i]);
        	pic_items.add(item);
        	
        }
        SimpleAdapter adapter=new SimpleAdapter(this, pic_items, R.layout.gridview_item, new String[]{"pic","desc"}, new int[]{R.id.img,R.id.text});
        imageList.setAdapter(adapter);
     
        imageList.setOnItemClickListener(new OnItemClickListener() {
 
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
//				AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
//	            builder.setTitle("提示").setMessage(descs[position].toString()).create().show();					
				Intent intent  = new Intent(LoginActivity.this,BoughtActivity.class);
				startActivity(intent);		
			}
		});
      
        Button cinema = (Button)findViewById(R.id.cinema);
       cinema.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(LoginActivity.this,CinemaActivity.class);
			startActivity(intent);		
		}
	});
       
       Button person = (Button)findViewById(R.id.personal);
       person.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent1 = new Intent(LoginActivity.this,PersonalActivity.class);
			startActivity(intent1);		
		}
	});
        
       
		
	}
	
}
