package com.otiasj.memoryLogger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;

import com.otiasj.memoryLogger.loggers.FileLogger;
import com.otiasj.memoryLogger.loggers.MemoryLogger;
import com.otiasj.memoryLogger.loggers.OnMemoryLog;
import com.otiasj.memoryLogger.loggers.WidgetLogger;
import com.otiasj.memoryLogger.utils.MemoryUtils;

/**
 * In your manifest add the permission :
 * 
 * <pre>
 * {@code
 * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
 * }
 * </pre>
 * 
 * In your application onCreate method call :
 * 
 * <pre>
 * MemoryTracker.getInstance().logToFile(context, &quot;/sdcard/yourfile.csv&quot;).enableWidget(context).logToLogcat()
 *         .start(1000, &quot;myAppTag&quot;);
 * </pre>
 */
public class Memory {

    /**
     * Set this to true if you want to explicitly call the garbage collector before logging the memory usage
     */
    public boolean doGCBeforeLogging = false;

    public static final String TAG = Memory.class.getCanonicalName();
    private static final int START_DELAY_MILLIS = 1000;
    private final MemoryUtils mMemUtils;

    private Timer mTimer;
    private String mTag;

    private static final Memory instance = new Memory();
    private final List<WeakReference<OnMemoryLog>> mLoggers;
    private MemoryLogger mLogcatLogger;
    private WidgetLogger mWidgetLogger;

    private Memory() {
        mMemUtils = new MemoryUtils();
        mLoggers = new ArrayList<WeakReference<OnMemoryLog>>();
    }

    public static Memory getInstance() {
        return Memory.instance;
    }

    /**
     * Call this to use the automatic logger and save the logs to an sdcard file
     * 
     * @param context
     * @param filePath the path of the file to write the data
     */
    public Memory logToFile(final Context context, final String filePath) {
        mLogcatLogger = new MemoryLogger();
        mLoggers.add(new WeakReference<OnMemoryLog>(new FileLogger(context, filePath)));
        return this;
    }

    /**
     * Use this to output the logs to logcat
     * 
     * @return
     */
    public Memory logToLogcat() {
        mLoggers.add(new WeakReference<OnMemoryLog>(mLogcatLogger));
        return this;
    }

    /**
     * Enable the floating widget
     * 
     * @return
     */
    public Memory enableWidget(final Context context) {
        mWidgetLogger = new WidgetLogger();
        mWidgetLogger.showWidget(context);
        mLoggers.add(new WeakReference<OnMemoryLog>(mWidgetLogger));
        return this;
    }

    /**
     * Add a custom logger
     * 
     * @param logger
     * @return
     */
    public Memory addLogger(final MemoryLogger logger) {
        mLoggers.add(new WeakReference<OnMemoryLog>(logger));
        return this;
    }

    /**
     * Automatically call logMemoryUsage
     * 
     * @param milliseconds time interval
     */
    public synchronized void start(final int milliseconds, final String tag) {
        mTag = tag;
        mTimer = new Timer(false);
        mTimer.schedule(mLogUpdateRunnable, Memory.START_DELAY_MILLIS, milliseconds);
    }

    /**
     * Stop automatic logging
     */
    public synchronized void stop() {
        mTimer.cancel();
    }

    private final TimerTask mLogUpdateRunnable = new TimerTask() {
        @Override
        public void run() {
            if (doGCBeforeLogging) {
                System.gc();
            }
            logMemoryUsage(mTag);
        }
    };

    /**
     * Triggers a memory log to all loggers, do not call this from the UI thread if you are using a sdcard logger
     * 
     * @param tag some information to be written before the memory info
     */
    public void logMemoryUsage(final String tag) {
        // get memory log
        final double allocated = mMemUtils.getAllocatedDouble();
        final double heapSize = mMemUtils.getHeapDouble();
        final double percent = mMemUtils.getPercentUsedDouble();

        for (final Iterator<WeakReference<OnMemoryLog>> iterator = mLoggers.iterator(); iterator.hasNext();) {
            final WeakReference<OnMemoryLog> weakRef = iterator.next();
            final OnMemoryLog logger = weakRef.get();
            if (logger != null) {
                logger.onLog(tag, allocated, heapSize, percent);
            } else {
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        return mMemUtils.toString();
    }

}
