package com.example.ttms;

import com.example.database.MyDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePwdActivity extends Activity {
	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_changepwd);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		dbHelper = new MyDatabaseHelper(this,"UserStor1.db",null,2);
		final SharedPreferences  sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		final String oldusername = sp.getString("username","NO_VALUE");
        final String oldpwd = sp.getString("password", "NO_VALUE");
        
		Button verChangePwd = (Button)findViewById(R.id.verChangePwd);
		
		verChangePwd.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				TextView editTextOld = (TextView)findViewById(R.id.editTextOld);
		        TextView editTextNew = (TextView)findViewById(R.id.editTextNew);
				String TextOld = editTextOld.getText().toString();
				String TextNew = editTextNew.getText().toString();
				Log.d("oldpwd", oldpwd);
				Log.d("ChangePwdActivity", TextOld);
				if(oldpwd.equals(TextOld)) {
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					db.execSQL("update User set password='"+TextNew+"'where username='"+oldusername+"'and password='"+oldpwd+"';");
					Toast.makeText(ChangePwdActivity.this, "更改密码成功", Toast.LENGTH_SHORT).show();
				}
				Toast.makeText(ChangePwdActivity.this, "前后密码不一致", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
