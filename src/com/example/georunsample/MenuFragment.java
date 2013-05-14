package com.example.georunsample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {

	private static final String JSON_URL = "http://cerber.cs.put.poznan.pl/~inf94270/georun.json";
	private String[] datas;
	private PostAdapter adapter;
	private MainActivity mainAct;
	private GlobalApplication globalApp;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainAct = (MainActivity) getActivity();
		globalApp = (GlobalApplication) mainAct.getApplication();
		initializeList();
		getExtrenalData();
	}

	private void initializeList() {
		ArrayList<Post> posts = retrieveData();
		adapter = new PostAdapter(getActivity().getApplicationContext(), R.layout.row, posts);
		setListAdapter(adapter);
	}

	private void getExtrenalData() {
		AsyncHttpClient asyncHC = new AsyncHttpClient();
		asyncHC.get(JSON_URL, 
			new AsyncHttpResponseHandler()
			{

				@Override
				public void onSuccess(String response) {
					try {
						JSONObject json = new JSONObject(response);
						JSONArray postArray = json.getJSONArray("posts");
						mainAct.successfulConnection();
						globalApp.getDatabaseHelper().insertJSON(postArray);
						initializeList();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onFailure(Throwable arg0, String arg1) {
					mainAct.failureConnection();
				}
				
			});
	}
	

	private ArrayList<Post> retrieveData() {
		return globalApp.getDatabaseHelper().retrieveData();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ContentFragment newFragment = null;
		newFragment = new ContentFragment(adapter.getItem(position));
		setNewFragment(newFragment);
	}

	private void setNewFragment(ContentFragment newFragment) {
		mainAct.switchFragment(newFragment);
	}
	

}
