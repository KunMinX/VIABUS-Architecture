package com.kunminx.viabus.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunminx.architecture.business.bus.Result;
import com.kunminx.common.base.BaseBusFragment;
import com.kunminx.viabus.R;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.business.constant.NoteResultCode;
import com.kunminx.viabus.databinding.FragmentTodoDetailBinding;

/**
 * @author KunMinX
 * @date 2018/8/21
 */
public class TodoDetailFragment extends BaseBusFragment {

    private FragmentTodoDetailBinding mBinding;
    private final static String TITLE = "TITLE";
    private String mTitle;
    private boolean mIsNew;

    public static TodoDetailFragment newInstance(String title) {
        TodoDetailFragment fragment = new TodoDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_detail, container, false);
        mBinding = FragmentTodoDetailBinding.bind(view);
        mBinding.setClickProxy(new ClickProxy());
        setHasOptionsMenu(true);
        NoteBus.registerResponseObserver(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mTitle = getArguments().getString(TITLE);
        if (!TextUtils.isEmpty(mTitle)) {
            mBinding.et.setText(mTitle);
        } else {
            mIsNew = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoteBus.unregisterResponseObserver(this);
    }

    public class ClickProxy {
        public void save() {
            if (!mBinding.et.getText().toString().equals(mTitle)) {
                if (mIsNew) {
                    NoteBus.note().insert(null);
                } else {
                    NoteBus.note().update(null);
                }
            }
        }

        public void delete() {
            NoteBus.note().delete(null);
        }
    }

    @Override
    public void onResultHandle(Result testResult) {
        String resultCode = (String) testResult.getResultCode();
        switch (resultCode) {
            case NoteResultCode.INSERTED:
                break;
            case NoteResultCode.UPDATED:
                break;
            case NoteResultCode.DELETED:
                break;
            case NoteResultCode.FAILURE:
                break;
            case NoteResultCode.CANCELED:
                break;
            default:
        }
    }
}
