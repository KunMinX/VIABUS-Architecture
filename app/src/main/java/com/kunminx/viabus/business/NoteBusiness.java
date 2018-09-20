package com.kunminx.viabus.business;

import android.content.Context;

import com.kunminx.architecture.business.BaseBusiness;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.viabus.R;
import com.kunminx.viabus.bean.NoteBean;
import com.kunminx.viabus.business.bus.INoteRequest;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.business.constant.NoteResultCode;
import com.kunminx.viabus.repertory.DataBaseAdapter;

import java.io.IOException;
import java.util.List;

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
                List<NoteBean> list = mDataBase.getList();
                return new Result(NoteResultCode.QUERY_LIST, list);
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
    public void insert(final NoteBean bean) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                long id = mDataBase.insertEntity(bean);
                if (id >= 0) {
                    bean.setId(id);
                    return new Result(NoteResultCode.INSERTED, bean);
                } else {
                    return new Result(NoteResultCode.FAILURE, mContext.getString(R.string.tip_insert_error));
                }
            }
        });
    }

    @Override
    public void update(final NoteBean bean) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                int count = mDataBase.updateEntity(bean);
                if (count >= 0) {
                    return new Result(NoteResultCode.UPDATED, bean);
                } else {
                    return new Result(NoteResultCode.FAILURE, mContext.getString(R.string.tip_update_error));
                }
            }
        });
    }

    @Override
    public void delete(final NoteBean bean) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                int count = mDataBase.deleteEntity(bean);
                if (count >= 0) {
                    return new Result(NoteResultCode.DELETED, bean);
                } else {
                    return new Result(NoteResultCode.FAILURE, mContext.getString(R.string.tip_delete_error));
                }
            }
        });
    }
}
