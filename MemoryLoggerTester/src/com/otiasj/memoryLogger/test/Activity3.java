package com.otiasj.memoryLogger.test;

import com.otiasj.memoryLogger.amltester.R;

import android.app.Activity;
import android.os.Bundle;

public class Activity3 extends Activity {

	private static byte[] someBigData = new byte[1000000]; //Single leak, this will stay in memory until the application is destroyed
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity3);
		
	}

}
