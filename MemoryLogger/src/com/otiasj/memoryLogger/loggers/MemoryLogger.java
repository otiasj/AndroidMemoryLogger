package com.otiasj.memoryLogger.loggers;

import android.util.Log;

import com.otiasj.memoryLogger.utils.MemoryUtils;

public class MemoryLogger implements OnMemoryLog {

    @Override
    public void onLog(final String tag, final double allocated, final double heapSize, final double percent,
            final double nativeUsed, final double nativeHeapSize) {
        Log.v(tag,
                "" + MemoryUtils.decimalFormat.format(allocated) + "/" + MemoryUtils.decimalFormat.format(heapSize)
                        + " " + MemoryUtils.decimalPercentFormat.format(percent) + "%"
                        + MemoryUtils.decimalPercentFormat.format(nativeUsed) + "/"
                        + MemoryUtils.decimalPercentFormat.format(nativeHeapSize));
    }
}
