package com.inquisoft.sqlitedemo;

import java.sql.Date;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDB {

	private MyDBHelper dbHelper;
	private SQLiteDatabase db;
	
	static MyDB myDB;
	
	public static MyDB getInstance(Context c){
		if(myDB == null)
			myDB = new MyDB(c);
		return myDB;
	}
	
	private MyDB(Context c){
		dbHelper = new MyDBHelper(c);
		db = dbHelper.getWritableDatabase();
	}
	
	public void createComment(Comment comment){
		String insert = "INSERT INTO Comments VALUES(null,date('now'),'"+comment.who+"', '"+comment.comment+"' )";
		db.execSQL(insert);
	}
	
	public ArrayList<Comment> getComments(){
		String select = "SELECT * FROM Comments";
		Cursor c = db.rawQuery(select, null);
		ArrayList<Comment> comments = new ArrayList<Comment>();
		while(c.moveToNext()){
			Comment comment = new Comment();
			comment.set_id(c.getInt(0));
			comment.setDate(new Date(c.getLong(1)));
			comment.setWho(c.getString(2));
			comment.setComment(c.getString(3));
			comments.add(comment);
		}
		return comments;
	}
	
	public Comment getCommentByID(int id){
		String select = "SELECT * FROM Comments WHERE _id = ?";
		Cursor c = db.rawQuery(select, new String[]{id+""});
		if(c.moveToNext()){
			Comment comment = new Comment();
			comment.set_id(c.getInt(0));
			comment.setDate(new Date(c.getLong(1)));
			comment.setWho(c.getString(2));
			comment.setComment(c.getString(3));
			return comment;
		}else{
			return null;
		}
	}
	
	
	public void updateComment(Comment comment){
		String update = "UPDATE Comments SET who=?, comment=? WHERE _id=?";
		db.execSQL(update, new String[]{comment.who,comment.comment,comment._id+""});
	}
	
	public void deleteComment(Comment comment){
		String update = "DELETE FROM Comments WHERE _id = ?";
		db.execSQL(update, new String[]{comment._id+""});
	}
	
}
