package com.otiasj.memoryLogger.test;

import com.otiasj.memoryLogger.amltester.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

public class FourthActivity extends Activity {

	/**
	 * see http://www.androiddesignpatterns.com/2013/01/inner-class-handler-memory-leak.html
	 * 
	 * This activity will be kept in memory until the message is posted by the handler, even after the activity is closed
	 */
	private class MyLeakyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	}
	
	public final byte[] someBigData = new byte[1000000]; //Growing leak, this will be created everytime this activity is launched

	private MyLeakyHandler mMyLeakyHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fourthactivity);
		mMyLeakyHandler = new MyLeakyHandler();
		mMyLeakyHandler.postDelayed(new Runnable() {
		      @Override
		      public void run() { }
		    }, 60 * 10 * 1000);
	}

}
