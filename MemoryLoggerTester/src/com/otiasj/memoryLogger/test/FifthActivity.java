package com.otiasj.memoryLogger.test;

import com.otiasj.memoryLogger.amltester.R;

import android.app.Activity;
import android.os.Bundle;

public class FifthActivity extends Activity {
	
	public final byte[] someBigData = new byte[1000000]; //Growing leak, this will be created everytime this activity is launched
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fifthactivity);
		MyVeryLeakySingleton.getInstance().addListener(new OnSomeEventListener() {
			@Override
			public void onSomeEvent() {
				//...
			}
		});
	}

}
