package com.kunminx.viabus.ui.adapter;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.kunminx.viabus.R;
import com.kunminx.viabus.bean.NoteBean;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.databinding.AdapterTestListBinding;

import java.util.UUID;

/**
 * @author KunMinX
 * @date 2018/9/20
 */
public class NoteListAdapter extends BaseBindingAdapter<NoteBean, AdapterTestListBinding> {

    public NoteListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.adapter_test_list;
    }

    @Override
    protected void onBindItem(final AdapterTestListBinding binding, final NoteBean item, final int position) {
        binding.tvTitle.setText(item.getTitle());
        Glide.with(mContext).load(item.getImgUrl()).into(binding.ivThumb);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTitle(UUID.randomUUID().toString());
                notifyDataSetChanged();
                NoteBus.note().update(item);
            }
        });
        binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getList().remove(position);
                notifyItemRemoved(position);
                NoteBus.note().delete(item);
                return true;
            }
        });
    }
}
