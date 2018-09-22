package com.kunminx.viabus.ui;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.kunminx.architecture.utils.PermissionUtils;
import com.kunminx.viabus.R;
import com.kunminx.viabus.business.NoteBusiness;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.databinding.ActivityMainBinding;

/**
 * @author KunMinX
 * @date 2018/8/21
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initModel();
    }

    private void initView() {
        NoteBusiness noteBusiness = new NoteBusiness();
        noteBusiness.init(this.getApplicationContext());
        NoteBus.registerRequestHandler(noteBusiness);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, NoteListFragment.newInstance())
                .addToBackStack(null).commit();
    }

    private void initModel() {
        if (PermissionUtils.needPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionUtils.requestPermissionInActivity(new PermissionUtils.IPermissionCallback() {
                @Override
                public void onAllowedPermissions() {
                    initView();
                }

                @Override
                public void onDeniedPermissions(String msg) {

                }
            }, this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            initView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(getApplicationContext(), requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }
}
