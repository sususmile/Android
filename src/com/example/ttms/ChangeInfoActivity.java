package com.example.ttms;

import com.example.database.MyDatabaseHelper;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeInfoActivity extends Activity{
	private MyDatabaseHelper dbHelper;
	private ImageView image_choose; 
	private String mImagePath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Window window = getWindow();
	    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		setContentView(R.layout.activity_changeinfo);
		window.setStatusBarColor(getResources().getColor(R.color.myStatusColor));
		dbHelper = new MyDatabaseHelper(this,"UserStor1.db",null,2);
		final SharedPreferences  sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
		
		Button confirm = (Button)findViewById(R.id.confirm);	
		
		confirm.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
		        String oldusername = sp.getString("username","NO_VALUE");
		        String oldpwd = sp.getString("password", "NO_VALUE");
		        TextView username = (TextView)findViewById(R.id.editUsername);
				String newuser = username.getText().toString();
				Log.d("ChangeInfoActivity", oldusername);
				Log.d("ChangeInfoActivity", oldpwd);
				Log.d("ChangeInfoActivity", newuser);
				db.execSQL("update User set username='"+newuser+"',imagePath='"+mImagePath+"'where username='"+oldusername+"'and password='"+oldpwd+"';");
				Toast.makeText(ChangeInfoActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
			}
		});
		
		image_choose = (ImageView) findViewById(R.id.image_choose); 
		image_choose.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				switch (v.getId()) { 
				  case R.id.image_choose: { 
				   Intent intent = new Intent(Intent.ACTION_PICK, null); 
				   intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
				     "image/*"); 
				   startActivityForResult(intent, 0x1); 
				   break; 
				  } 
				  } 
			}
		});
		
	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
	  // TODO Auto-generated method stub 
	  image_choose = (ImageView) findViewById(R.id.image_choose); 
	  if (requestCode == 0x1 && resultCode == RESULT_OK) { 
	   if (data != null) { 
		   handleImageOnKitkat(data); 
	   } 
	  } 
	  super.onActivityResult(requestCode, resultCode, data); 
	 } 
	
	private void handleImageOnKitkat(Intent data) {
	    String imagePath = null;
	    Uri uri = data.getData();
	    if (DocumentsContract.isDocumentUri(this, uri)) {
	        //如果是document类型的uri，则通过document id处理
	        String docId = DocumentsContract.getDocumentId(uri);
	        if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
	            String id = docId.split(":")[1];
	            String selection = MediaStore.Images.Media._ID + "=" + id;
	            imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
	        } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
	            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
	                    "//downloads/public_downloads"), Long.valueOf(docId));
	            imagePath = getImagePath(contentUri, null);
	        }
	    } else if ("content".equalsIgnoreCase(uri.getScheme())) {
	        //如果是content类型的uri，则使用普通方式处理
	        imagePath = getImagePath(uri, null);
	    } else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        //如果是File类型的uri，直接获取图片路径即可
	        imagePath = uri.getPath();
	    }
	    displayImage(imagePath);//根据图片路径显示图片

	}
	private String getImagePath(Uri uri, String selection) {
	    String path = null;
	    //通过uri和selection来获取真实的图片路径
	    Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
	    if (cursor != null) {
	        if (cursor.moveToFirst()) {
	            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
	        }
	        cursor.close();
	    }
	    return path;
	}
	private void displayImage(String imagePath) {
		mImagePath = imagePath;
	    if (imagePath != null) {
	        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
	        image_choose.setImageBitmap(bitmap);
	    } else {
	        Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
	    }
	}

}
