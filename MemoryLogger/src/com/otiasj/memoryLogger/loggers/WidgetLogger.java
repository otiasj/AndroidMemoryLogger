package com.otiasj.memoryLogger.loggers;

import com.otiasj.memoryLogger.utils.MemoryUtils;
import com.otiasj.memoryLogger.view.MemoryLoggerGraphView;
import com.otiasj.memoryLogger.view.OverlayWidget;
import com.otiasj.memoryLogger.view.WidgetView;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

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
    public void onLog(final String tag, final double allocated, final double heapSize, final double percent) {
        WidgetLogger.mHandler.sendMessage(WidgetLogger.mHandler.obtainMessage(UpdaterHandler.UPDATE_TEXT, new double[] {
                allocated, heapSize, percent
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
                    final String log = "" + MemoryUtils.decimalFormat.format(values[0]) + "/" + MemoryUtils.decimalFormat.format(values[1])
                            + " " + MemoryUtils.decimalPercentFormat.format(values[2]) + "%";
                    mGraphView.update(log, values[0], values[1]);
                }
            }
        }
    }

}
