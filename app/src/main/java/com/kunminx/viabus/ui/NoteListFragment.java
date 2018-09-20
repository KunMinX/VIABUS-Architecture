package com.kunminx.viabus.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.kunminx.viabus.R;
import com.kunminx.viabus.bean.NoteBean;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.business.constant.NoteResultCode;
import com.kunminx.viabus.databinding.AdapterTestListBinding;
import com.kunminx.viabus.databinding.FragmentNoteListBinding;
import com.kunminx.viabus.ui.adapter.BaseBindingAdapter;

import java.util.List;
import java.util.UUID;

/**
 * @author KunMinX
 * @date 2018/8/21
 */
public class NoteListFragment extends Fragment implements IResponse {

    private FragmentNoteListBinding mBinding;
    private BaseBindingAdapter<NoteBean, AdapterTestListBinding> mAdapter;

    public static NoteListFragment newInstance() {
        NoteListFragment fragment = new NoteListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        mBinding = FragmentNoteListBinding.bind(view);
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
        mAdapter = new BaseBindingAdapter<NoteBean, AdapterTestListBinding>(getContext()) {
            @Override
            protected int getLayoutResId(int viewType) {
                return R.layout.adapter_test_list;
            }

            @Override
            protected void onBindItem(final AdapterTestListBinding binding, final NoteBean item, final int position) {
                binding.tvTitle.setText(item.getTitle());
                Glide.with(getContext()).load(item.getImgUrl()).into(binding.ivThumb);
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*getActivity().getSupportFragmentManager().beginTransaction()
                                .add(R.id.fragment_container, NoteDetailFragment.newInstance(item.getTitle()))
                                .addToBackStack(null).commit();*/
                        item.setTitle(UUID.randomUUID().toString());
                        notifyDataSetChanged();
                        NoteBus.note().update(item);
                    }
                });
                binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle(R.string.tip_delete).setMessage(R.string.tip_sure_to_delete)
                                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        notifyItemRemoved(position);
                                        NoteBus.note().delete(item);
                                    }
                                }).setNegativeButton(R.string.cancel, null).create().show();
                        return true;
                    }
                });
            }
        };
        mBinding.rv.setAdapter(mAdapter);
        NoteBus.note().queryList();
    }

    public class ClickProxy {
        public void newNote() {
            /*getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, NoteDetailFragment.newInstance(""))
                    .addToBackStack(null).commit();*/
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
            case NoteResultCode.UPDATED:
                break;
            case NoteResultCode.DELETED:
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
