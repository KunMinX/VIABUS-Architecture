package com.kunminx.viabus.ui;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;

import com.kunminx.core.bus.Result;
import com.kunminx.architecture.base.BaseBusActivity;
import com.kunminx.architecture.utils.PermissionUtils;
import com.kunminx.viabus.R;
import com.kunminx.viabus.business.NoteBusiness;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.databinding.ActivityMainBinding;

/**
 * @author KunMinX
 * Create at 2018/8/21
 */
public class MainActivity extends BaseBusActivity {

    private ActivityMainBinding mBinding;
    private NoteBusiness mNoteBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initBusiness();
    }

    private void initBusiness() {
        PermissionUtils.requestPermissionInActivity(new PermissionUtils.IPermissionCallback() {
            @Override
            public void onResult(String msg) {
                if (TextUtils.isEmpty(msg)) {
                    mNoteBusiness = new NoteBusiness();
                    mNoteBusiness.init(MainActivity.this.getApplicationContext());
                    NoteBus.registerRequestHandler(mNoteBusiness);
                    loadRootFragment(R.id.fragment_container, TodoListFragment.newInstance());
                } else {
                    //TODO
                }
            }
        }, this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NoteBus.unregisterRequestHandler(mNoteBusiness);
        mNoteBusiness = null;
    }


    @Override
    public void onResultHandle(Result testResult) {

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }
}
