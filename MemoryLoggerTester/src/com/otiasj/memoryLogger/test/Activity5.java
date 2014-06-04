package com.otiasj.memoryLogger.test;

import com.otiasj.memoryLogger.amltester.R;

import android.app.Activity;
import android.os.Bundle;

public class Activity5 extends Activity {
	
	public final byte[] someBigData = new byte[1000000]; //Growing leak, this will be created everytime this activity is launched
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity5);
		MyVeryLeakySingleton.getInstance().addListener(new OnSomeEventListener() {
			@Override
			public void onSomeEvent() {
				//...
			}
		});
	}

}
