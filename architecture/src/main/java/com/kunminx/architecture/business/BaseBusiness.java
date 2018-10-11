package com.kunminx.architecture.business;

import com.kunminx.architecture.business.bus.BaseBus;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.architecture.business.bus.ResultCode;

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
public class BaseBusiness<B extends BaseBus> {

    /**
     * send message in main thread.
     *
     * @param e
     * @param result
     */
    protected void sendMessage(ObservableEmitter<Result> e, Result result) {
        e.onNext(result);
    }

    /**
     * send progress info in main thread.
     *
     * @param e
     * @param result
     */
    protected void onProgress(ObservableEmitter<Result> e, Result result) {
        sendMessage(e, result);
    }

    /**
     * handle request
     *
     * @param iAsync
     */
    protected void handleRequest(final IAsync iAsync) {
        Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(ObservableEmitter<Result> e) {
                try {
                    if (iAsync != null) {
                        e.onNext(iAsync.onExecute(e));
                    }
                } catch (Exception e1) {
                    e.onError(e1);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result value) {
                        try {
                            B.response(value);
                        } catch (Exception e1) {
                            throw new RuntimeException(this.toString() + " " + e1.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Result result = new Result(ResultCode.FAILURE, e.toString());
                        B.response(result);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    protected interface IAsync {
        /**
         * excuting request in io thread
         *
         * @param e
         * @return
         * @throws IOException
         */
        Result onExecute(ObservableEmitter<Result> e) throws IOException;
    }

}
