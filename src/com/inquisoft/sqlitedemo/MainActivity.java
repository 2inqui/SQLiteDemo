package com.inquisoft.sqlitedemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}	
	
	public void addComment(View v){
		Intent intent = new Intent(this, AddCommentActivity.class);
		startActivity(intent);
	}
	
	
	public void viewComments(View v){
		Intent intent = new Intent(this, CommentActivity.class);
		startActivity(intent);
	}

}
