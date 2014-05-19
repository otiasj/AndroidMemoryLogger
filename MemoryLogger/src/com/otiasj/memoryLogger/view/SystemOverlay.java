package com.otiasj.memoryLogger.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;

/**
 * A system overlay holding a view, this requires additional permission in the manifest :
 * 
 * <pre>
 * {@code
 * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
 * }
 * </pre>
 */
public class SystemOverlay {
    private WindowManager.LayoutParams mWMParams;
    private final Context mContext;
    private View mView;

    public SystemOverlay(final Context context) {
        mContext = context;
    }

    /**
     * Show the layout
     */
    public void showWindow(final View view) {
        mView = view;
        mWMParams = new WindowManager.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, PixelFormat.TRANSLUCENT);
        final WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.addView(mView, mWMParams);
        moveTo(0, 0);
    }

    /**
     * hide the layout
     */
    public void removeWindow() {
        final WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.removeView(mView);
    }

    /**
     * Move the window to given coordinates
     * 
     * @param x
     * @param y
     */
    public void moveTo(final int x, final int y) {
        mWMParams.x = x;
        mWMParams.y = y;
        final WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.updateViewLayout(mView.getRootView(), mWMParams);
    }

    /**
     * translate the window by given values
     * 
     * @param tx
     * @param ty
     */
    public void moveBy(final int tx, final int ty) {
        mWMParams.x -= tx;
        mWMParams.y -= ty;
        final WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.updateViewLayout(mView.getRootView(), mWMParams);
    }

}
