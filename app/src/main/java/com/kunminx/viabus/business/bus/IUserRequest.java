package com.kunminx.viabus.business.bus;

import com.kunminx.architecture.business.bus.IRequest;

/**
 * @author xmj
 * @date 2018/9/19
 */
public interface IUserRequest extends IRequest {

    void signIn(String phonenumber, String password);

    void signUp(String phonenumber, String password);

    void signOut();

    void forgetPassword(String phonenumber);
}
