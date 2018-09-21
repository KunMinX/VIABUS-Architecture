package com.kunminx.viabus.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.viabus.R;
import com.kunminx.viabus.bean.NoteBean;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.business.constant.NoteResultCode;
import com.kunminx.viabus.databinding.FragmentNoteListBinding;
import com.kunminx.viabus.ui.adapter.NoteListAdapter;

import java.util.List;
import java.util.UUID;

/**
 * @author KunMinX
 * @date 2018/8/21
 */
public class NoteListFragment extends Fragment implements IResponse {

    private FragmentNoteListBinding mBinding;
    private NoteListAdapter mAdapter;

    public static NoteListFragment newInstance() {
        NoteListFragment fragment = new NoteListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteBus.registerResponseObserver(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        mBinding = FragmentNoteListBinding.bind(view);
        mBinding.setClickProxy(new ClickProxy());
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mAdapter = new NoteListAdapter(getContext());
        mBinding.rv.setAdapter(mAdapter);
        NoteBus.note().queryList();
    }

    public class ClickProxy {
        public void newNote() {
            NoteBean bean = new NoteBean(
                    UUID.randomUUID().toString(),
                    String.valueOf(System.currentTimeMillis()),
                    "https://upload-images.jianshu.io/upload_images/57036-fb9653da874d2447.jpg"
            );
            NoteBus.note().insert(bean);
        }
    }

    @Override
    public void onResult(Result testResult) {
        int resultCode = testResult.getResultCode();
        switch (resultCode) {
            case NoteResultCode.QUERY_LIST:
                if (testResult.getResultObject() != null) {
                    mAdapter.setList((List<NoteBean>) testResult.getResultObject());
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case NoteResultCode.INSERTED:
                if (testResult.getResultObject() != null) {
                    mAdapter.getList().add(0, (NoteBean) testResult.getResultObject());
                    mAdapter.notifyItemInserted(0);
                }
                break;
            case NoteResultCode.FAILURE:
                Toast.makeText(getContext(), testResult.getResultObject().toString(), Toast.LENGTH_SHORT).show();
                break;
            case NoteResultCode.CANCELED:
                Toast.makeText(getContext(), testResult.getResultObject().toString(), Toast.LENGTH_SHORT).show();
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
