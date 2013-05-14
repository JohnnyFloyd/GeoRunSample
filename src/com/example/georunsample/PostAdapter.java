package com.example.georunsample;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PostAdapter extends ArrayAdapter<Post> {

	Context ctx;
	ArrayList<Post> items;
	LayoutInflater inflater;
	
	public PostAdapter(Context context, int textViewResourceId, List<Post> items) {
		super(context, textViewResourceId, items);
		this.items = (ArrayList<Post>) items;
		ctx = context;
		inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.row, null);
		}
		Post post = items.get(position);
		if ( post != null) {
			TextView title = (TextView) view.findViewById(R.id.textViewRow);
			title.setText(post.getTitle());
		}
		return view;
	}
	
	

}
