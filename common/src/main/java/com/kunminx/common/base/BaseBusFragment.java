package com.kunminx.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.common.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author KunMinX
 * @date 2018/7/5
 */

public abstract class BaseBusFragment extends SupportFragment implements IResponse {

    protected boolean mIsFragmentAnimationEnd = false;
    /**
     * for saving temp init data.
     */
    protected List<Result> mInitResult = new ArrayList<>();

    /**
     * for forbidden repeated click in one times
     */
    private long enableTime = 0;


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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(getContext(), requestCode, permissions, grantResults);
    }

}
