package com.kunminx.viabus.repertory.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kunminx.viabus.repertory.bean.NoteBean;

import java.util.List;

/**
 * @author KunMinX
 * Create at 2018/8/22
 */
@Dao
public interface NoteDao {

    @Query("SELECT * FROM NoteBean")
    List<NoteBean> getBeans();

    @Query("SELECT * FROM NoteBean WHERE id=:id")
    NoteBean getBean(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBean(NoteBean bean);

    @Update
    int update(NoteBean bean);

    @Delete
    int delete(NoteBean bean);
}
