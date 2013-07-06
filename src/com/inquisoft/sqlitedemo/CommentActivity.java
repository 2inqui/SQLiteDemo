package com.inquisoft.sqlitedemo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class CommentActivity extends Activity {

	ListView listView;
	SearchView svSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comments);
		
		listView = (ListView) findViewById(R.id.list_view);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				MyAdapter adapter = (MyAdapter) arg0.getAdapter();
				Intent i = new Intent(CommentActivity.this,AddCommentActivity.class);
				i.putExtra("commentID", adapter.getComment(arg2).get_id());
				startActivity(i);
			}
		});
		// Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		svSearch = (SearchView) findViewById(R.id.search_view);
		svSearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		
		
		// Get the intent, verify the action and get the query
	    Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      listView.setAdapter(new MyAdapter(this, R.layout.list_item_comment, MyDB.getInstance(this).getCommentsByWho(query)));
	    }else{
	    	listView.setAdapter(new MyAdapter(this, R.layout.list_item_comment, MyDB.getInstance(this).getComments()));
	    }
		
	}
	

	private class MyAdapter extends ArrayAdapter<Comment>{

		List<Comment> comments;
		
		public MyAdapter(Context context, int textViewResourceId,
				List<Comment> objects) {
			super(context, textViewResourceId, objects);
			comments = objects;
		}
		
		public Comment getComment(int position){
			return comments.get(position);
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
