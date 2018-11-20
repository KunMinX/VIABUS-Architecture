package com.kunminx.viabus.repertory.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author KunMinX
 * Create at 2018/8/22
 * <p>
 * 实体类
 */
@Entity
public class NoteBean {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private String date;
    private String imgUrl;

    @Ignore
    public NoteBean(String title, String date, String imgUrl) {
        this.title = title;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    public NoteBean(Long id, String title, String date, String imgUrl) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
