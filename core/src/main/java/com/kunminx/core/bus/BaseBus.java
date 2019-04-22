package com.kunminx.core.bus;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KunMinX
 * Create at 2018/8/22
 * <p>
 */
public class BaseBus {

    private static HashMap<String, IRequest> mIRequest = new HashMap<>();
    private static List<IResponse> mIResponses = new ArrayList<>();
    private static List<IResponse> mKnockedOutIResponses = new ArrayList<>();

    public static void registerRequestHandler(IRequest request) {
        Class[] interfaces = request.getClass().getInterfaces();
        Class implementedInterface = null;
        for (Class inf : interfaces) {
            if (IRequest.class.isAssignableFrom(inf)) {
                implementedInterface = inf;
                break;
            }
        }
        if (implementedInterface == null) {
            throw new RuntimeException(
                    "Error: Please declare a request interface extends IRequest, "
                            + "and then make business class implement it.");
        }
        String name = implementedInterface.getSimpleName();
        if (!TextUtils.isEmpty(name) && mIRequest.get(name) == null) {
            mIRequest.put(name, request);
        }
    }

    public static void unregisterRequestHandler(IRequest request) {
        String name = request.getClass().getSimpleName();
        if (mIRequest.get(name) != null) {
            request.onDestroy();
            mIRequest.remove(name);
        }
    }

    public static void registerResponseObserver(IResponse response) {
        if (response != null && !mIResponses.contains(response)) {
            mIResponses.add(response);
        }
    }

    public static void unregisterResponseObserver(IResponse response) {
        if (response != null) {
            mKnockedOutIResponses.add(response);
        }
    }

    public static void clearAllRegister() {
        mIRequest.clear();
        mIResponses.clear();
        mKnockedOutIResponses.clear();
    }

    public synchronized static void response(Result result) {
        if (result == null) {
            return;
        }
        if (mIResponses != null && mIResponses.size() > 0) {
            for (int i = 0; i < mIResponses.size(); i++) {
                IResponse iResponse = mIResponses.get(i);
                if (!mKnockedOutIResponses.contains(iResponse)) {
                    iResponse.onResult(result);
                }
            }
        }

        for (IResponse iResponse : mKnockedOutIResponses) {
            if (mIResponses != null) {
                mIResponses.remove(iResponse);
            }
        }
        mKnockedOutIResponses.clear();
    }

    protected static IRequest getRequest(Class iRequest) {
        IRequest iRequest1 = mIRequest.get(iRequest.getSimpleName());
        if (iRequest1 == null) {
            throw new RuntimeException(
                    "Error: Please register a request '" + iRequest.getSimpleName() + "' by bus");
        }
        return iRequest1;
    }
}
