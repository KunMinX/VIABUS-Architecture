package com.kunminx.architecture.business.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KunMinX
 * @date 2018/8/22
 * <p>
 * 虽然ui和业务可以多对多，但从request和response关系上讲，不是对称的多对多。
 * 一个ui一次仅可以筛选并请求一个业务，但业务响应的结果可以遍历分发给多个ui。
 * 换个角度说，ui接口的实现者实例可以有多个，业务接口的实现者实例只有一个。
 * 因此，ui用list装载，遍历皆回调（反注册的反正都已不在list中，不再监听响应）。业务用map装载，针对性的请求。
 */
public class BaseBus {

    private static HashMap<String, IRequest> mIRequest = new HashMap<>();
    private static List<IResponse> mIResponses = new ArrayList<>();

    public static void registerRequestHandler(IRequest request) {
        String name = request.getClass().getSimpleName();
        if (mIRequest.get(name) == null) {
            mIRequest.put(name, request);
        }
    }

    public static void unregisterRequestHandler(IRequest request) {
        String name = request.getClass().getSimpleName();
        if (mIRequest.get(name) != null) {
            mIRequest.remove(name);
        }
    }

    public static void registerResponseObserver(IResponse response) {
        if (response != null && !mIResponses.contains(response)) {
            mIResponses.add(response);
        }
    }

    public static void unregisterResponseObserver(IResponse response) {
        if (response != null && mIResponses.contains(response)) {
            mIResponses.remove(response);
        }
    }

    public static void clearAllRegister() {
        mIRequest.clear();
        mIResponses.clear();
    }

    public static void response(Result result) {
        if (mIResponses != null && mIResponses.size() > 0) {
            for (IResponse response : mIResponses) {
                response.onResult(result);
            }
        }
    }

    protected static IRequest getRequest(Class iRequest) {
        IRequest iRequest1 = mIRequest.get(iRequest.getSimpleName());
        if (iRequest1 == null) {
            throw new RuntimeException("Error: Please register a request '" + iRequest.getSimpleName() + "' by bus");
        }
        return iRequest1;
    }
}
