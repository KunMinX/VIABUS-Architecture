package com.kunminx.viabus.repertory;

import android.content.Context;

import com.kunminx.viabus.bean.NoteBean;
import com.kunminx.viabus.business.constant.Configs;
import com.kunminx.viabus.repertory.model.AppDatabase;

import java.util.List;

/**
 * @author KunMinX
 * @date 2018/8/22
 */
public class DataBaseAdapter implements IDataBaseInterface<NoteBean> {
    @Override
    public void init(Context context) {
        AppDatabase.init(context, Configs.DB_PATH);
    }

    @Override
    public List<NoteBean> getList() {
        return AppDatabase.getInstance().testDao().getBeans();
    }

    @Override
    public List<NoteBean> getList(String tag, String type) {
        return null;
    }

    @Override
    public NoteBean getEntity(String uuid) {
        return AppDatabase.getInstance().testDao().getBean(0);
    }

    @Override
    public long insertEntity(NoteBean t) {
        return 0;
    }

    @Override
    public int updateEntity(NoteBean t) {
        return 0;
    }

    @Override
    public int deleteEntity(NoteBean t) {
        return 0;
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
