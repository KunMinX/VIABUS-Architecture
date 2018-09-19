package com.kunminx.architecture.utils;

/**
 * @author KunMinX
 * @date 2018/7/22
 */
public class StatusUtils {

    //TODO 要测试一下
    public static boolean isEnabledStatus(int statuses, int... statusABC) {
        boolean enabled = false;
        for (int status : statusABC) {
            enabled |= ((statuses & status) != 0);
        }
        return enabled;
    }

    //TODO 要测试一下
    public static int modifyStatus(int statuses, boolean add, int status) {
        if (add) {
            if ((statuses & status) == 0) {
                statuses = statuses | status;
            }
        } else {
            statuses = (statuses & ~status);
        }
        return statuses;
    }
}
