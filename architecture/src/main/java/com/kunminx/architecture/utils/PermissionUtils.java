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

import com.kunminx.architecture.R;

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

    public static void onRequestPermissionsResult(Context context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                StringBuilder sb = new StringBuilder();
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            sb.append(getGroupNameByPermissionName(context, permissions[i])).append(",");
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
     * get group name by danger private permission
     *
     * @param permissionName
     * @return
     */
    public static String getGroupNameByPermissionName(Context context, String permissionName) {
        String groupName = "";
        switch (permissionName) {
            //by public permission
            case Manifest.permission.BODY_SENSORS:
                groupName = context.getString(R.string.permission_group_sensor);
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                groupName = context.getString(R.string.permission_group_location);
                break;
            case Manifest.permission.CAMERA:
                groupName = context.getString(R.string.permission_group_camera);
                break;
            case Manifest.permission.RECORD_AUDIO:
                groupName = context.getString(R.string.permission_group_audio);
                break;

            //by private permission
            case Manifest.permission.READ_PHONE_STATE:
            case Manifest.permission.CALL_PHONE:
            case Manifest.permission.READ_CALL_LOG:
            case Manifest.permission.WRITE_CALL_LOG:
            case Manifest.permission.ADD_VOICEMAIL:
            case Manifest.permission.USE_SIP:
            case Manifest.permission.PROCESS_OUTGOING_CALLS:
                groupName = context.getString(R.string.permission_group_phone);
                break;
            case Manifest.permission.SEND_SMS:
            case Manifest.permission.RECEIVE_SMS:
            case Manifest.permission.READ_SMS:
            case Manifest.permission.RECEIVE_WAP_PUSH:
            case Manifest.permission.RECEIVE_MMS:
                groupName = context.getString(R.string.permission_group_sms);
                break;
            case Manifest.permission.READ_CONTACTS:
            case Manifest.permission.WRITE_CONTACTS:
            case Manifest.permission.GET_ACCOUNTS:
                groupName = context.getString(R.string.permission_group_contact);
                break;
            case Manifest.permission.READ_CALENDAR:
            case Manifest.permission.WRITE_CALENDAR:
                groupName = context.getString(R.string.permission_group_calendar);
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                groupName = context.getString(R.string.permission_group_extenal_storage);
                break;
            default:
        }
        return groupName;
    }

}
