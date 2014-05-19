package com.otiasj.memoryLogger.view;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * The widget view + title bar and buttons
 */
public class WidgetView extends LinearLayout {

    static int size = 0;

    private static final int NB_BUTTON = 3;
    public static final int BUTTON_SIZE = 32;

    public static final int CLOSE_BUTTON_ID = 42;
    public static final int RESIZE_BUTTON_ID = 43;
    public static final int GRAPH_VIEW_ID = 45;
    public static final int TITLEBAR_VIEW_ID = 46;

    final MemoryLoggerGraphView mGraphView;
    private final LayoutParams mParams;
    private final OnWidgetRefreshListener mListener;

    public WidgetView(final Context context, final OnWidgetRefreshListener listener) {
        super(context);
        mParams = new LayoutParams(indp(450), indp(450), 1);

        setBackgroundColor(Color.parseColor("#252E3D"));
        setOrientation(LinearLayout.VERTICAL);

        setLayoutParams(mParams);
        mListener = listener;

        // Title Bar
        final LinearLayout titleBar = new LinearLayout(context);
        titleBar.setId(WidgetView.TITLEBAR_VIEW_ID);
        titleBar.setOrientation(LinearLayout.HORIZONTAL);
        titleBar.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        titleBar.setBackgroundColor(Color.parseColor("#344055"));
        titleBar.setMinimumWidth(indp(WidgetView.BUTTON_SIZE * WidgetView.NB_BUTTON));

        final LayoutParams buttonParams = new LayoutParams(indp(WidgetView.BUTTON_SIZE), indp(WidgetView.BUTTON_SIZE));

        // Add the close button
        final Button closeButton = new Button(getContext(), null, android.R.attr.buttonStyleSmall);
        closeButton.setId(WidgetView.CLOSE_BUTTON_ID);
        closeButton.setMinHeight(0);
        closeButton.setMinWidth(0);
        closeButton.setText("X");
        closeButton.setTextSize(8);
        closeButton.setLayoutParams(buttonParams);
        titleBar.addView(closeButton);

        // Add the resize button
        final Button resizeButton = new Button(getContext(), null, android.R.attr.buttonStyleSmall);
        resizeButton.setMinHeight(0);
        resizeButton.setMinWidth(0);
        resizeButton.setText("+");
        resizeButton.setTextSize(8);
        resizeButton.setLayoutParams(buttonParams);
        resizeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                resize();
                mListener.onRefresh();
            }
        });
        titleBar.addView(resizeButton);

        // Add the graphView
        mGraphView = new MemoryLoggerGraphView(context);
        mGraphView.setLayoutParams(mParams);
        mGraphView.setId(WidgetView.GRAPH_VIEW_ID);

        addView(titleBar);
        addView(mGraphView);
        resize();
    }

    private int indp(final int pixels) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, getResources().getDisplayMetrics());
    }

    private void resize() {
        WidgetView.size += 1;
        switch (WidgetView.size) {
            case 0:
                break;
            case 1:
                mParams.width = indp(250);
                mParams.height = indp(150);
                mGraphView.setShowLegend(false);
                break;
            case 2:
                mParams.width = indp(750);
                mParams.height = indp(550);
                mGraphView.setShowLegend(true);
                break;
            default:
                mParams.width = indp(WidgetView.BUTTON_SIZE * WidgetView.NB_BUTTON);
                mParams.height = indp(WidgetView.BUTTON_SIZE * 2);
                mGraphView.setShowLegend(false);
                WidgetView.size = 0;
        }

    }
}
