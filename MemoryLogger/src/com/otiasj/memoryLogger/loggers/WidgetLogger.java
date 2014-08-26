package com.otiasj.memoryLogger.loggers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.otiasj.memoryLogger.utils.MemoryUtils;
import com.otiasj.memoryLogger.view.MemoryLoggerGraphView;
import com.otiasj.memoryLogger.view.OverlayWidget;
import com.otiasj.memoryLogger.view.WidgetView;

public class WidgetLogger implements OnMemoryLog {

    private static UpdaterHandler mHandler;

    /**
     * This creates the layout and start the overlay widget
     * 
     * @param context
     */
    public void showWidget(final Context context) {
        WidgetLogger.mHandler = new UpdaterHandler(context.getMainLooper());
        final OverlayWidget widget = new OverlayWidget(context);
        WidgetLogger.mHandler.mGraphView = (MemoryLoggerGraphView) widget.findViewById(WidgetView.GRAPH_VIEW_ID);
    }

    @Override
    public void onLog(final String tag, final double allocated, final double heapSize, final double percent,
            final double nativeUsed, final double nativeHeapSize) {
        WidgetLogger.mHandler.sendMessage(WidgetLogger.mHandler.obtainMessage(UpdaterHandler.UPDATE_TEXT, new double[] {
                allocated, heapSize, percent, nativeUsed
        }));
    };

    private static class UpdaterHandler extends Handler {
        public static final int UPDATE_TEXT = 0;
        public MemoryLoggerGraphView mGraphView;

        public UpdaterHandler(final Looper mainLooper) {
            super(mainLooper);
        }

        @Override
        public void handleMessage(final Message msg) {
            if (msg.what == UpdaterHandler.UPDATE_TEXT) {
                if (mGraphView != null) {
                    final double[] values = (double[]) msg.obj;
                    final String log = "" + MemoryUtils.decimalFormat.format(values[0]) + "/"
                            + MemoryUtils.decimalFormat.format(values[1]) + " "
                            + MemoryUtils.decimalPercentFormat.format(values[2]) + "% "
                            + MemoryUtils.decimalPercentFormat.format(values[3]);
                    mGraphView.update(log, values[0], values[1], values[3]);
                }
            }
        }
    }

}
