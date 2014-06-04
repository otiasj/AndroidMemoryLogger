package com.otiasj.memoryLogger.test;

import android.app.Activity;
import android.os.Bundle;

import com.otiasj.memoryLogger.amltester.R;

public class Activity6 extends Activity {

    static Leaky leak = null;// This is keeping a reference to the whole activity

    public final byte[] someBigData = new byte[1000000];

    class Leaky {
        void doSomething() {
            // ...
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity6);

        if (Activity6.leak == null) {
            Activity6.leak = new Leaky();
        }
    }

}
