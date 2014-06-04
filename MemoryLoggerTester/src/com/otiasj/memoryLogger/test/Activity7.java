package com.otiasj.memoryLogger.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;

import com.otiasj.memoryLogger.amltester.R;

public class Activity7 extends Activity {

    public final byte[] someBigData = new byte[1000000];

    private final Timer mTimer = new Timer();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity6);

        // If you forget to stop the timer the whole activity is leaked
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Update some stuff at regular interval
            }
        }, 1000, 1000);
    }

}
