package com.kunminx.viabus.business;

import android.content.Context;

import com.kunminx.architecture.business.BaseBusiness;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.viabus.bean.NoteBean;
import com.kunminx.viabus.business.bus.INoteRequest;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.repertory.DataBaseAdapter;

import java.io.IOException;

import io.reactivex.ObservableEmitter;

/**
 * @author xmj
 * @date 2018/9/19
 */
public class NoteBusiness extends BaseBusiness<NoteBus> implements INoteRequest {

    private DataBaseAdapter mDataBase;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
        mDataBase = new DataBaseAdapter();
        mDataBase.init(context);
    }

    @Override
    public void queryList() {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }

    @Override
    public void queryEntity(long id) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }

    @Override
    public void insert(NoteBean bean) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }

    @Override
    public void update(NoteBean bean) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }

    @Override
    public void delete(NoteBean bean) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }
}
