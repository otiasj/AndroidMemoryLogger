Android Memory Logger
=====================

### Purpose
Graph your android application memory usage in real time.

This library helps finding memory leaks.
You should test run your application using this library, to quickly locate leaks.
Then use DDMS and memory dumps to identify the origin of the leak and fix it.


### Usage 
Add the following permission to your manifest (Don't forget to remove it before releasing your app!)
 ```xml
 <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
 ```

Import this library as an Android project library, or add the jar file to your project's libs directory

In your application just call :
```java
 Memory.getInstance().enableWidget(getApplicationContext()).start(1000, "test");
```
 
![ScreenShot](https://raw.githubusercontent.com/otiasj/AndroidMemoryLogger/master/doc/middle.png)

![ScreenShot](https://raw.githubusercontent.com/otiasj/AndroidMemoryLogger/master/doc/big.png)

![ScreenShot](https://raw.githubusercontent.com/otiasj/AndroidMemoryLogger/master/doc/small.png)


### Credits :
This library is using GraphView
[a link](http://android-graphview.org/#)
