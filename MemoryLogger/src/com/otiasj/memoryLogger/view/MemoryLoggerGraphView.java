package com.otiasj.memoryLogger.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

/**
 * A view to show the memory usage. This is using the GraphView library.
 */
public class MemoryLoggerGraphView extends LinearLayout {

    private GraphView mGraphView;
    private GraphViewSeries mMemoryUsed;
    private GraphViewSeries mMemoryAllocated;

    final private GraphViewDataInterface[] emptyData = new GraphViewDataInterface[] {
            new GraphViewData(0, 0), new GraphViewData(0, 0)
    };
    final private GraphViewSeriesStyle style1 = new GraphViewSeriesStyle(Color.rgb(200, 50, 00), 1);
    final private GraphViewSeriesStyle style2 = new GraphViewSeriesStyle(Color.rgb(90, 250, 00), 1);

    public MemoryLoggerGraphView(final Context context) {
        super(context);
        initGraphView(this);
    }

    public MemoryLoggerGraphView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initGraphView(this);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MemoryLoggerGraphView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        initGraphView(this);
    }

    private synchronized void initGraphView(final LinearLayout layout) {
        mGraphView = new LineGraphView(getContext(), "Memory");

        // data
        mMemoryUsed = new GraphViewSeries("Used", style1, emptyData);
        mMemoryAllocated = new GraphViewSeries("Allocated", style2, emptyData);
        mGraphView.addSeries(mMemoryUsed);
        mGraphView.addSeries(mMemoryAllocated);

        // style
        mGraphView.getGraphViewStyle().setTextSize(8);
        mGraphView.setViewPort(0, 60);
        mGraphView.setScrollable(true);
        mGraphView.setScalable(true);

        layout.addView(mGraphView);
    }

    private static int index = 0;

    /**
     * Add some data to the graph
     * 
     * @param title
     * @param used
     * @param allocated
     */
    public synchronized void update(final String title, final double used, final double allocated) {
        mMemoryUsed.appendData(new GraphViewData(MemoryLoggerGraphView.index, used), false, 1000);
        mMemoryAllocated.appendData(new GraphViewData(MemoryLoggerGraphView.index, allocated), false, 1000);
        mGraphView.setTitle(title);
        mGraphView.redrawAll();
        MemoryLoggerGraphView.index++;
    }

    /**
     * Hide or show the lines legend
     * 
     * @param showLegend
     */
    public void setShowLegend(final boolean showLegend) {
        mGraphView.setShowLegend(showLegend);
    }

    /**
     * Clear all graph data
     */
    public synchronized void clear() {
        MemoryLoggerGraphView.index = 0;
        // mMemoryUsed.resetData(emptyData);
        // mMemoryAllocated.resetData(emptyData);
        // mGraphView.redrawAll();

        // mMemoryUsed = new GraphViewSeries("Used", style1, emptyData);
        // mMemoryAllocated = new GraphViewSeries("Allocated", style2, emptyData);
        // mGraphView.removeAllSeries();
        // mGraphView.addSeries(mMemoryUsed);
        // mGraphView.addSeries(mMemoryAllocated);
    }

}
