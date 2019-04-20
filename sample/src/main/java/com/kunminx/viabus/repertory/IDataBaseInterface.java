package com.kunminx.viabus.repertory;

import android.content.Context;

import java.util.List;

/**
 * @author KunMinX
 * Create at 2018/8/22
 */
public interface IDataBaseInterface<T> {

    void init(Context context);

    List<T> getList();

    List<T> getList(String tag, String type);

    T getEntity(String uuid);

    long insertEntity(T t);

    int updateEntity(T t);

    int deleteEntity(T t);

    boolean insertEntities(T... ts);

    boolean updateEntities(T... ts);

    boolean deleteEntities(T... ts);

    boolean insertEntities(List<T> ts);

    boolean updateEntities(List<T> ts);

    boolean deleteEntities(List<T> ts);
}
