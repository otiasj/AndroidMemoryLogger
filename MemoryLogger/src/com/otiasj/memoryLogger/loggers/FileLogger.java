package com.otiasj.memoryLogger.loggers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.otiasj.memoryLogger.utils.MemoryUtils;

import android.content.Context;
import android.util.Log;

/**
 * Write a log to a sdcard file
 */
public class FileLogger implements OnMemoryLog {

    public static final String TAG = FileLogger.class.getCanonicalName();
    private final String mLogPath;
    private PrintWriter mLogFile = null;

    /**
     * @param context
     * @param filePath the path of the file where to write the log data
     */
    public FileLogger(final Context context, final String filePath) {
        mLogPath = filePath;
        Log.v(FileLogger.TAG, "Logging memory usage to file " + mLogPath);
    }

    @Override
    public void onLog(final String tag, final double allocated, final double heapSize, final double percent) {
        if (mLogPath != null) {
            try {
                // Append data to the file
                final File file = new File(mLogPath);
                if (mLogFile == null) {
                    mLogFile = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                }
                mLogFile.println("" + MemoryUtils.decimalFormat.format(allocated) + "," + MemoryUtils.decimalFormat.format(heapSize) + "," + MemoryUtils.decimalPercentFormat.format(percent));
                mLogFile.flush();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}
