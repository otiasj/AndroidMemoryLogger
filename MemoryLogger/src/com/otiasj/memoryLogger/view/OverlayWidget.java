package com.otiasj.memoryLogger.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

/**
 * Create the widget, which is composed of an overlay, a widgetView linearlayout and some listeners
 */
public class OverlayWidget extends SystemOverlay {

    private final SystemOverlay mWidget;
    public WidgetView mWidgetView;

    public OverlayWidget(final Context context) {
        super(context);
        mWidget = new SystemOverlay(context);
        mWidgetView = new WidgetView(context, mRefreshListener);
        mWidgetView.findViewById(WidgetView.TITLEBAR_VIEW_ID).setOnTouchListener(mTouchListener);
        mWidgetView.findViewById(WidgetView.CLOSE_BUTTON_ID).setOnClickListener(mOnCloseButtonClickListener);
        mWidget.showWindow(mWidgetView);
    }

    private final OnWidgetRefreshListener mRefreshListener = new OnWidgetRefreshListener() {
        @Override
        public void onRefresh() {
            mWidget.removeWindow();
            mWidget.showWindow(mWidgetView);
        }
    };

    /**
     * OnTouchListener for moving the widget
     */
    private final OnTouchListener mTouchListener = new OnTouchListener() {
        private int mOffsetX, mOffsetY;

        @Override
        public boolean onTouch(final View v, final MotionEvent event) {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mOffsetX = x;
                mOffsetY = y;
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                final int diffX = mOffsetX - x;
                final int diffY = mOffsetY - y;

                mWidget.moveBy(diffX, diffY);
                mOffsetX = x;
                mOffsetY = y;
                return true;
            }
            return false;
        }
    };

    /**
     * OnclickListener for closing the widget
     */
    private final OnClickListener mOnCloseButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(final View v) {
            mWidget.removeWindow();
        }
    };

    public View findViewById(final int id) {
        return mWidgetView.findViewById(id);
    }

}
