package com.kunminx.viabus.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.viabus.R;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.business.constant.NoteResultCode;
import com.kunminx.viabus.databinding.FragmentNoteDetailBinding;

/**
 * @author KunMinX
 * @date 2018/8/21
 */
public class NoteDetailFragment extends Fragment implements IResponse {

    private FragmentNoteDetailBinding mBinding;
    private final static String TITLE = "TITLE";
    private String mTitle;
    private boolean mIsNew;

    public static NoteDetailFragment newInstance(String title) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);
        mBinding = FragmentNoteDetailBinding.bind(view);
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
    public void onResult(Result testResult) {
        int resultCode = testResult.getResultCode();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoteBus.unregisterResponseObserver(this);
    }
}
