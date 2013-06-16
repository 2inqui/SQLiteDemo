package com.inquisoft.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCommentActivity extends Activity {

	EditText etWho;
	EditText etMessage;
	
	Comment comment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_comment);
		etWho = (EditText) findViewById(R.id.activity_add_comment_et_who);
		etMessage = (EditText) findViewById(R.id.activity_add_comment_et_message);
		
		int commentID = getIntent().getIntExtra("commentID", -1);
		
		if(commentID != -1){
			comment = MyDB.getInstance(this).getCommentByID(commentID);
			etWho.setText(comment.getWho());
			etMessage.setText(comment.getComment());
		}
	}
	
	
	/**
	 * Event when the user do clinck on save button
	 * @param v
	 */
	public void save(View v){
		Comment commentNew  = new Comment();
		
		commentNew.who = etWho.getText().toString();
		commentNew.comment = etMessage.getText().toString();
		
		MyDB.getInstance(this).createComment(commentNew);
		
		Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
	}
	
	public void update(View v){
		if(comment != null){
			comment.who = etWho.getText().toString();
			comment.comment = etMessage.getText().toString();
			MyDB.getInstance(this).updateComment(comment);
		}else{
			Toast.makeText(this, "No valid commetn", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void delete(View v){
		if(comment != null){
			MyDB.getInstance(this).deleteComment(comment);
		}else{
			Toast.makeText(this, "No valid commetn", Toast.LENGTH_SHORT).show();
		}
	}
	
}
