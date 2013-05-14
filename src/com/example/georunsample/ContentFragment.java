package com.example.georunsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {

	TextView textView;
	String body;
	
	public ContentFragment(){
		this(null);
	}
	
	public ContentFragment(Post post){
		if (post != null) {
			this.body = post.getBody();
		}
		else {
			this.body = "";
		}
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.content_layout, container, false);
		textView = (TextView) view.findViewById(R.id.tvContent);
		textView.setText(body);
		return view;
	}
	
	
	
}
