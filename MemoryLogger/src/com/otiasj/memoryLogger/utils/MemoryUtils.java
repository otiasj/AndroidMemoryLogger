package com.otiasj.memoryLogger.utils;

import java.text.DecimalFormat;

import android.os.Debug;

/**
 * This utility class can be used to retrieve memory information,
 */
public class MemoryUtils {
    private final double BYTES = 1048576.0; // 1024.0;// 1048576.0;
    public final static DecimalFormat decimalFormat = new DecimalFormat("00.000");
    public final static DecimalFormat decimalPercentFormat = new DecimalFormat("00.00");

    /**
     * @return the allocated memory in MB (String)
     */
    public String getAllocated() {
        return MemoryUtils.decimalFormat.format(getAllocatedDouble());
    }

    /**
     * @return the allocated memory in MB (Double)
     */
    public double getAllocatedDouble() {
        return getHeapDouble() - getFreeMemoryDouble();
    }

    /**
     * @return the heap size in MB (String)
     */
    public String getHeap() {
        return MemoryUtils.decimalFormat.format(getHeapDouble());
    }

    /**
     * @return the heap size in MB (Double)
     */
    public double getHeapDouble() {
        return Double.valueOf(Runtime.getRuntime().totalMemory()) / BYTES;
    }

    /**
     * @return free memory in MB (String)
     */
    public String getFreeMemory() {
        return MemoryUtils.decimalFormat.format(getFreeMemoryDouble());
    }

    /**
     * @return free memory in MB (Double)
     */
    public double getFreeMemoryDouble() {
        return Double.valueOf(Runtime.getRuntime().freeMemory()) / BYTES;
    }

    /**
     * @return free memory in percent
     */
    public String getPercentUsed() {
        final double current = getPercentUsedDouble();
        return MemoryUtils.decimalPercentFormat.format(current);
    }

    /**
     * @return free memory in percent
     */
    public double getPercentUsedDouble() {
        return getAllocatedDouble() / getHeapDouble() * 100.00d;
    }

    /**
     * This api is useful to find out how much of native heap is allocated.
     * 
     * @return the getNativeHeapAllocatedSize in MB
     */
    public double getNativeHeap() {
        return Debug.getNativeHeapAllocatedSize() / BYTES;
    }

    public double getNativeHeapFreeSize() {
        return Debug.getNativeHeapFreeSize() / BYTES;
    }

    public double getNativeHeapUsed() {
        final double total = getNativeHeap();
        return total - getNativeHeapFreeSize();
    }

    /**
     * allocated / heap, free
     * 
     * @return the detailed memory information
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getAllocated());
        sb.append("/");
        sb.append(getHeap());
        sb.append("MB ");
        sb.append(getPercentUsed());
        sb.append(" %");
        return sb.toString();
    }
}
