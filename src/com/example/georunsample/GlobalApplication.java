package com.example.georunsample;

import android.app.Application;

public class GlobalApplication extends Application {

	private MyDatabaseHelper dh;

	@Override
	public void onCreate() {
		super.onCreate();
		dh = new MyDatabaseHelper(getApplicationContext());
		dh.addBasicData();
	}
	
	public MyDatabaseHelper getDatabaseHelper() {
		return dh;
	}

}
