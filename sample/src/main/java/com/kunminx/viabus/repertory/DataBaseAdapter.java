package com.kunminx.viabus.repertory;

import android.content.Context;

import com.kunminx.viabus.business.constant.Configs;
import com.kunminx.viabus.repertory.bean.NoteBean;
import com.kunminx.viabus.repertory.model.AppDatabase;

import java.util.List;

/**
 * @author KunMinX
 * Create at 2018/8/22
 */
public class DataBaseAdapter implements IDataBaseInterface<NoteBean> {

    private Context mContext;
    private AppDatabase mAppDatabase;

    @Override
    public void init(Context context) {
        mContext = context;
        AppDatabase.init(Configs.DB_PATH);
        mAppDatabase = AppDatabase.getInstance(mContext);
    }

    @Override
    public List<NoteBean> getList() {
        return mAppDatabase.noteDao().getBeans();
    }

    @Override
    public List<NoteBean> getList(String tag, String type) {
        return null;
    }

    @Override
    public NoteBean getEntity(String uuid) {
        return mAppDatabase.noteDao().getBean(0);
    }

    @Override
    public long insertEntity(NoteBean t) {
        return mAppDatabase.noteDao().insertBean(t);
    }

    @Override
    public int updateEntity(NoteBean t) {
        return mAppDatabase.noteDao().update(t);
    }

    @Override
    public int deleteEntity(NoteBean t) {
        return mAppDatabase.noteDao().delete(t);
    }

    @Override
    public boolean insertEntities(NoteBean... ts) {
        return false;
    }

    @Override
    public boolean updateEntities(NoteBean... ts) {
        return false;
    }

    @Override
    public boolean deleteEntities(NoteBean... ts) {
        return false;
    }

    @Override
    public boolean insertEntities(List<NoteBean> ts) {
        return false;
    }

    @Override
    public boolean updateEntities(List<NoteBean> ts) {
        return false;
    }

    @Override
    public boolean deleteEntities(List<NoteBean> ts) {
        return false;
    }
}
