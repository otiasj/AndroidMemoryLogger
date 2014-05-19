AndroidMemoryLogger
===================

Graph your android application memory usage in real time.

This library helps finding memory leaks.
You should test run your application using this library, to quickly locate leaks.
Then use DDMS and memory dumps to identify the origin of the leak and fix it.

Add the following permission to your manifest (Don't forget to remove it before releasing your app!)
 <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

In your application call :
 Memory.getInstance().enableWidget(getApplicationContext()).start(1000, "test");
 
