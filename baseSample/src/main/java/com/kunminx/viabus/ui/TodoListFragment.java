package com.kunminx.viabus.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kunminx.architecture.business.bus.Result;
import com.kunminx.common.base.BaseBusFragment;
import com.kunminx.viabus.R;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.business.constant.NoteResultCode;
import com.kunminx.viabus.databinding.FragmentTodoListBinding;
import com.kunminx.viabus.repertory.bean.NoteBean;
import com.kunminx.viabus.ui.adapter.NoteListAdapter;

import java.util.List;
import java.util.UUID;

/**
 * @author KunMinX
 * Create at 2018/8/21
 */
public class TodoListFragment extends BaseBusFragment {

    private FragmentTodoListBinding mBinding;
    private NoteListAdapter mAdapter;

    public static TodoListFragment newInstance() {
        TodoListFragment fragment = new TodoListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteBus.registerResponseObserver(this);
        NoteBus.note().queryList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        mBinding = FragmentTodoListBinding.bind(view);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoteBus.unregisterResponseObserver(this);
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
    public void onResultHandle(Result testResult) {
        String resultCode = (String) testResult.getResultCode();
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
}
