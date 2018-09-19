package com.kunminx.architecture.utils;

import com.kunminx.architecture.business.bus.Result;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author KunMinX
 * @date 2018/8/22
 */
public class RxUtils<T extends Result> {

    private static RxUtils sRxUtils;

    public static RxUtils getInstance() {
        if (sRxUtils == null) {
            sRxUtils = new RxUtils();
        }
        return sRxUtils;
    }

    private RxUtils() {
    }


    /**
     * @param iAsnycTask
     */
    public void executeAsnycTask(final IAsnycTask iAsnycTask) {
        Observable.create(new ObservableOnSubscribe<Object[]>() {
            @Override
            public void subscribe(ObservableEmitter<Object[]> e) {
                try {
                    if (iAsnycTask != null) {
                        e.onNext(iAsnycTask.onExecute(e));
                    }
                } catch (Exception e1) {
                    e.onError(e1);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object[] value) {
                        if (iAsnycTask != null) {
                            iAsnycTask.onResult(null, value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iAsnycTask != null) {
                            iAsnycTask.onResult(e, null);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IAsnycTask {
        /**
         * @return 返回object数组，数据成员的约定，目前定为1.list、entity等实体，2.状态消息
         * @throws IOException
         */
        Object[] onExecute(ObservableEmitter<Object[]> e) throws IOException;

        void onResult(Throwable throwable, Object[] o);
    }
}
