package com.kunminx.common.utils;

/**
 * @author KunMinX
 * Create at 2018/10/23
 */
public class StatusUtils {
    public static boolean isEnabledStatus(int statuses, int... statusABC) {
        boolean enabled = false;
        for (int status : statusABC) {
            enabled |= ((statuses & status) != 0);
        }
        return enabled;
    }

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

