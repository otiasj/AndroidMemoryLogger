package com.otiasj.memoryLogger.test;

import com.otiasj.memoryLogger.amltester.R;

import android.app.Activity;
import android.os.Bundle;

public class SecondActivity extends Activity {

	private byte[] someBigData = new byte[1000000]; //This will be removed from memory when the activity closes
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.secondactivity);
		
	}

}
