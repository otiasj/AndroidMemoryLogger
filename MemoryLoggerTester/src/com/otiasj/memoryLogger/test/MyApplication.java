package com.otiasj.memoryLogger.test;

import android.app.Application;
import android.os.Environment;

import com.otiasj.memoryLogger.Memory;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Memory.getInstance()
                .logToFile(getApplicationContext(), Environment.getExternalStorageDirectory() + "/MemoryLog.csv")
                .logToLogcat().enableWidget(getApplicationContext()).start(1000, "test");
    }

}
