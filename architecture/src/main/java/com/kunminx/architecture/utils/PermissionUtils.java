package com.kunminx.architecture.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

/**
 * @author KunMinX
 * @date 2018/7/6
 */
public class PermissionUtils {

    private static final int REQUEST_PERMISSIONS = 0x0001;

    public static void requestPermissionInActivity(IPermissionCallback iPermissionCallback, Activity activity, String... permissions) {
        sIPermissionCallback = iPermissionCallback;
        for (String permission : permissions) {
            if (needPermission(activity, permission)) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_PERMISSIONS);
            }
        }
    }

    public static void requestPermissionInFragment(IPermissionCallback iPermissionCallback, Fragment fragment, String... permissions) {
        sIPermissionCallback = iPermissionCallback;
        for (String permission : permissions) {
            if (needPermission(fragment.getActivity(), permission)) {
                fragment.requestPermissions(new String[]{permission}, REQUEST_PERMISSIONS);
            }
        }
    }

    public static boolean needPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                StringBuilder sb = new StringBuilder();
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            sb.append(getGroupNameByPermissionName(permissions[i])).append(",");
                        }
                    }
                    String str = sb.toString();
                    if (str.contains(",") && str.endsWith(",")) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
                if (TextUtils.isEmpty(sb.toString())) {
                    if (sIPermissionCallback != null) {
                        sIPermissionCallback.onAllowedPermissions();
                    }
                } else {
                    if (sIPermissionCallback != null) {
                        sIPermissionCallback.onDeniedPermissions(sb.toString());
                    }
                }
                break;
            default:
        }
    }

    private static IPermissionCallback sIPermissionCallback;

    public interface IPermissionCallback {
        void onAllowedPermissions();

        void onDeniedPermissions(String msg);
    }

    /**
     * 通过危险权限名获取危险权限组名（中文）
     *
     * @param permissionName
     * @return
     */
    public static String getGroupNameByPermissionName(String permissionName) {
        String groupName = "";
        switch (permissionName) {
            //通过元器件调取实时隐私
            case Manifest.permission.BODY_SENSORS:
                groupName = "传感器";
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                groupName = "定位";
                break;
            case Manifest.permission.CAMERA:
                groupName = "摄像头";
                break;
            case Manifest.permission.RECORD_AUDIO:
                groupName = "录音";
                break;

            //通过持久化数据调取现成隐私
            case Manifest.permission.READ_PHONE_STATE:
            case Manifest.permission.CALL_PHONE:
            case Manifest.permission.READ_CALL_LOG:
            case Manifest.permission.WRITE_CALL_LOG:
            case Manifest.permission.ADD_VOICEMAIL:
            case Manifest.permission.USE_SIP:
            case Manifest.permission.PROCESS_OUTGOING_CALLS:
                groupName = "手机";
                break;
            case Manifest.permission.SEND_SMS:
            case Manifest.permission.RECEIVE_SMS:
            case Manifest.permission.READ_SMS:
            case Manifest.permission.RECEIVE_WAP_PUSH:
            case Manifest.permission.RECEIVE_MMS:
                groupName = "短信";
                break;
            case Manifest.permission.READ_CONTACTS:
            case Manifest.permission.WRITE_CONTACTS:
            case Manifest.permission.GET_ACCOUNTS:
                groupName = "联系人";
                break;
            case Manifest.permission.READ_CALENDAR:
            case Manifest.permission.WRITE_CALENDAR:
                groupName = "日历";
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                groupName = "外部存储";
                break;
            default:
        }
        return groupName;
    }

}
