package com.example.georunsample;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.actionbarsherlock.view.Window;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
	
	private ContentFragment content;
	private MenuFragment menu;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		content = new ContentFragment();
		menu = new MenuFragment();
		
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.content_frame);
		setBehindContentView(R.layout.menu_frame);

		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.content_frame, content);
		trans.replace(R.id.menu_frame, menu);
		trans.commit();
	}

	public void switchFragment(ContentFragment newFragment) {
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, newFragment).commit();
		getSlidingMenu().showContent();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		GlobalApplication glob = (GlobalApplication) getApplication();
		glob.getDatabaseHelper().cropTable();
	}
	
	public void successfulConnection(){
        setSupportProgressBarIndeterminateVisibility(false);
        Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
	}

	public void failureConnection() {
        setSupportProgressBarIndeterminateVisibility(false);
        Toast.makeText(getApplicationContext(),R.string.failure, Toast.LENGTH_SHORT).show();
	}

}
