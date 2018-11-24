package com.kunminx.common.base;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.common.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author KunMinX
 * Create at 2018/7/5
 */
public abstract class BaseBusActivity extends SupportActivity implements IResponse {

    /**
     * save init data to temp list.
     */
    private List<Result> mInitResult = new ArrayList<>();

    private boolean isResumed = false;

    /**
     * avoid multi times click in a time.
     */
    private long enableTime = 0;

    /**
     * exit time, to judge if is sure to exit
     */
    private long exitTime = 0;

    @Override
    protected void onResume() {
        super.onResume();
        loadInitData();
        isResumed = true;
    }

    /**
     * get init data ,to handle ui logic
     * <p>
     */
    private void loadInitData() {
        if (mInitResult.size() > 0) {
            for (int i = 0; i < mInitResult.size(); i++) {
                onResultHandle(mInitResult.get(i));
            }
            mInitResult.clear();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //for "if srceen span, need load init data again"
        isResumed = false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(getApplicationContext(), requestCode, permissions, grantResults);
    }

    @Override
    public final void onResult(Result testResult) {
        //before resumed, add init data to temp list
        if (!isResumed && testResult != null) {
            mInitResult.add(testResult);
            return;
        }
        onResultHandle(testResult);
    }

    public abstract void onResultHandle(Result testResult);

    /**
     * allow multi click during a time
     *
     * @param duration
     * @return
     */
    protected boolean isEnableToClick(long duration) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - enableTime > duration) {
            enableTime = currentTime;
            return true;
        } else {
            return false;
        }
    }

    /**
     * is sure to exit after double click
     *
     * @return
     */

    protected boolean isSureToExit(View listView, String tip, int duration) {
        if ((System.currentTimeMillis() - exitTime) > duration) {
            Snackbar.make(listView, tip, Snackbar.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }
}
