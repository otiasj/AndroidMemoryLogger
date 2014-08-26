package com.otiasj.memoryLogger.loggers;

public interface OnMemoryLog {
    public void onLog(String tag, double allocated, double heapSize, double percent, double nativeUsed,
            double nativeHeapSize);
}
