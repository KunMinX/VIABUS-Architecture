package com.kunminx.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.common.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author KunMinX
 * Create at 2018/7/5
 */

public abstract class BaseBusFragment extends SupportFragment implements IResponse {

    protected boolean mIsFragmentAnimationEnd = false;
    /**
     * for saving temp init data.
     */
    private List<Result> mInitResult = new ArrayList<>();

    /**
     * for forbidden repeated click in one times
     */
    private long enableTime = 0;

    /**
     * exit time, to judge if is sure to exit
     */
    private long exitTime = 0;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsFragmentAnimationEnd = false;
    }

    /**
     * load animation success. and then load init data
     *
     * @param savedInstanceState
     */
    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        loadInitData();
        mIsFragmentAnimationEnd = true;
    }

    private void loadInitData() {
        if (mInitResult.size() > 0) {
            for (int i = 0; i < mInitResult.size(); i++) {
                onResultHandle(mInitResult.get(i));
            }
            mInitResult.clear();
        }
    }

    /**
     * if init animation not loaded, save init data into temp list instead of handle immediately.
     * in order to smoothly load init data instead of blunt.
     *
     * @param testResult
     */
    @Override
    public final void onResult(Result testResult) {
        if (!mIsFragmentAnimationEnd && testResult != null) {
            mInitResult.add(testResult);
            return;
        }
        onResultHandle(testResult);
    }

    /**
     * handle result
     *
     * @param testResult
     */
    public abstract void onResultHandle(Result testResult);

    /**
     * for forbidden repeated click in one times
     *
     * @param duration duration for sure
     * @return isEnableToClick
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
     * @param listView
     * @param tip
     * @param duration
     * @return isSureToExit
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(getContext(), requestCode, permissions, grantResults);
    }

}
