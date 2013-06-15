package com.inquisoft.sqlitedemo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CommentActivity extends Activity {

	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comments);
		
		listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(new MyAdapter(this, R.layout.list_item_comment));
	}

	private class MyAdapter extends ArrayAdapter<Comment>{

		ArrayList<Comment> comments;
		
		public MyAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
			// TODO Auto-generated constructor stub
			comments = MyDB.getInstance(context).getComments();
		}
		
		@Override
		public int getCount() {
			return comments.size();
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View  v= convertView;
			if(v == null){
				LayoutInflater inflater = CommentActivity.this.getLayoutInflater();
			      v = inflater.inflate(R.layout.list_item_comment, null);
			      ViewHolder viewHolder = new ViewHolder();
			      viewHolder.etID = (TextView) v.findViewById(R.id.list_item_comment_id);
			      viewHolder.etWho = (TextView) v.findViewById(R.id.list_item_comment_who);
			      viewHolder.etDate = (TextView) v.findViewById(R.id.list_item_comment_date);
			      viewHolder.etComment = (TextView) v.findViewById(R.id.list_item_comment_comment);
			      v.setTag(viewHolder);
			}
			ViewHolder holder = (ViewHolder) v.getTag();
			holder.etID.setText(""+ comments.get(position).get_id() );
			holder.etWho.setText(""+ comments.get(position).getWho() );
			holder.etDate.setText(""+ comments.get(position).getDate() );
			holder.etComment.setText(comments.get(position).getComment());
			return v;
		}
		
		private class ViewHolder{
			TextView etID;
			TextView etWho;
			TextView etDate;
			TextView etComment;
		}
		
	}
	
}
