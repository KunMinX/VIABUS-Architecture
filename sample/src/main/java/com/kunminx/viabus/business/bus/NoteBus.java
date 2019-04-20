package com.kunminx.viabus.business.bus;

import com.kunminx.architecture.business.bus.BaseBus;

/**
 * author：KunMinX
 * Create at 2018/9/18
 */
public class NoteBus extends BaseBus {

    public static INoteRequest note() {
        return (INoteRequest) getRequest(INoteRequest.class);
    }

    public static IMapRequest map() {
        return (IMapRequest) getRequest(IMapRequest.class);
    }

    public static IUserRequest user() {
        return (IUserRequest) getRequest(IUserRequest.class);
    }
}
