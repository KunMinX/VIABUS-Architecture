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

public abstract class BaseFragment extends SupportFragment implements IResponse {

    protected boolean mIsFragmentAnimationEnd = false;
    /**
     * 用于临时存放初始化数据，当数据获取到、而载入动画还未完毕时。
     */
    protected List<Result> mInitResult = new ArrayList<>();

    /**
     * 用于防止一定时间内重复点击某按钮
     */
    private long enableTime = 0;


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsFragmentAnimationEnd = false;
    }

    /**
     * 动画载入成功，加载初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        loadInitData();
        mIsFragmentAnimationEnd = true;
    }

    /**
     * 提取初始化数据，更新ui展示
     * <p>
     * (//TODO 如果正在执行load的过程中又加入了一些事件到list怎么办。虽说一般init事件只有一个)
     */
    private void loadInitData() {
        if (mInitResult.size() > 0) {
            for (int i = 0; i < mInitResult.size(); i++) {
                onResultHandle(mInitResult.get(i));
            }
            mInitResult.clear();
        }
    }

    /**
     * 分发普通事件，或暂存粘性事件
     * 本方法不允许被重写，之类在onResultHandle中响应
     * <p>
     * （//TODO 粘性事件的处理有没更好的办法？当前事件本身并没有粘性，是base类在内部控制的暂存）
     *
     * @param testResult
     */
    @Override
    public final void onResult(Result testResult) {
        //载入动画未结束，初始数据先压入临时库。
        if (!mIsFragmentAnimationEnd && testResult != null) {
            mInitResult.add(testResult);
            return;
        }
        onResultHandle(testResult);
    }

    /**
     * 之类实际实现的onResult
     *
     * @param testResult
     */
    public abstract void onResultHandle(Result testResult);

    /**
     * 在一定时间内是否允许重复点击
     *
     * @param duration 间隔时间,毫秒
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
