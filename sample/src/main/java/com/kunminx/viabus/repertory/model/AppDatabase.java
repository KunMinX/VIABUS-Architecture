package com.kunminx.viabus.repertory.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.kunminx.viabus.repertory.bean.NoteBean;

/**
 * @author KunMinX
 * Create at 2018/6/30
 */
@Database(entities = {NoteBean.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static String sDbPath = null;

    private static AppDatabase INSTANCE = null;

    public static void init(String dbPath) {
        sDbPath = dbPath;
        if (INSTANCE != null) {
            INSTANCE.close();
            INSTANCE = null;
        }
    }

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, sDbPath)
                            .allowMainThreadQueries()
                            .build();
                }

            }
        }
        return INSTANCE;
    }

    public static RoomDatabase getDatabase(@NonNull Context context, String dbPath) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbPath)
                .allowMainThreadQueries()
                .build();
    }

    public abstract NoteDao noteDao();

}
