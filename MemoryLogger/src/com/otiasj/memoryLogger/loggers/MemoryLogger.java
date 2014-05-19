package com.otiasj.memoryLogger.loggers;

import com.otiasj.memoryLogger.utils.MemoryUtils;

import android.util.Log;

public class MemoryLogger implements OnMemoryLog {

    @Override
    public void onLog(final String tag, final double allocated, final double heapSize, final double percent) {
        Log.v(tag, "" + MemoryUtils.decimalFormat.format(allocated) + "/" + MemoryUtils.decimalFormat.format(heapSize) + " " + MemoryUtils.decimalPercentFormat.format(percent)
                + "%");
    }
}
