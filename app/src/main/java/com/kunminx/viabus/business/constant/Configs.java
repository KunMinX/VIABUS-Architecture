package com.kunminx.viabus.business.constant;

import android.os.Environment;

import java.io.File;

/**
 * @author KunMinX
 * @date 2018/8/22
 */
public class Configs {
    public final static String DB_PATH = Environment.getExternalStorageState() + File.separator + "test.db";
}
