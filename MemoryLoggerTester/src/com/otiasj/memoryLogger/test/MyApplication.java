package com.otiasj.memoryLogger.test;

import com.otiasj.memoryLogger.Memory;

import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Memory.getInstance().logToFile(getApplicationContext(), "/sdcard/MemoryLog.csv").logToLogcat().enableWidget(getApplicationContext()).start(1000, "test");
	}

}
