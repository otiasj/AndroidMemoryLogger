package com.otiasj.memoryLogger.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.otiasj.memoryLogger.amltester.R;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.mainactivity);

        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MyActivity.this, Activity2.class));
            }
        });

        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MyActivity.this, Activity3.class));
            }
        });

        findViewById(R.id.button3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MyActivity.this, Activity4.class));
            }
        });

        findViewById(R.id.button4).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MyActivity.this, Activity5.class));
            }
        });

        findViewById(R.id.button5).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MyActivity.this, Activity6.class));
            }
        });

        findViewById(R.id.button6).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MyActivity.this, Activity7.class));
            }
        });
    }

}
