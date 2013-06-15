package com.inquisoft.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCommentActivity extends Activity {

	EditText etWho;
	EditText etMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_comment);
		etWho = (EditText) findViewById(R.id.activity_add_comment_et_who);
		etMessage = (EditText) findViewById(R.id.activity_add_comment_et_message);
	}
	
	
	/**
	 * Event when the user do clinck on save button
	 * @param v
	 */
	public void save(View v){
		Comment comment  = new Comment();
		
		comment.who = etWho.getText().toString();
		comment.comment = etMessage.getText().toString();
		
		MyDB.getInstance(this).createComment(comment);
		
		Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
	}
	
}
