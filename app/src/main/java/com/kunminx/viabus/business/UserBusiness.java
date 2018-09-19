package com.kunminx.viabus.business;

import com.kunminx.architecture.business.BaseBusiness;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.viabus.business.bus.IUserRequest;
import com.kunminx.viabus.business.bus.NoteBus;

import java.io.IOException;

import io.reactivex.ObservableEmitter;

/**
 * @author xmj
 * @date 2018/9/19
 */
public class UserBusiness extends BaseBusiness<NoteBus> implements IUserRequest {


    @Override
    public void signIn(String phonenumber, String password) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }

    @Override
    public void signUp(String phonenumber, String password) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }

    @Override
    public void signOut() {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }

    @Override
    public void forgetPassword(String phonenumber) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return null;
            }
        });
    }
}
