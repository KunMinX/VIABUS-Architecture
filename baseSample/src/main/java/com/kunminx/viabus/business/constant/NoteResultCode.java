package com.kunminx.viabus.business.constant;

import com.kunminx.architecture.business.bus.ResultCode;

/**
 * @author KunMinX
 * @date 2018/8/28
 */
public class NoteResultCode extends ResultCode {

    public static final int QUERY_LIST = 0x0001;
    public static final int QUERY_ENTITY = 0x0002;
    public static final int INSERTED = 0x0004;
    public static final int UPDATED = 0x0008;
    public static final int DELETED = 0x0010;

}
