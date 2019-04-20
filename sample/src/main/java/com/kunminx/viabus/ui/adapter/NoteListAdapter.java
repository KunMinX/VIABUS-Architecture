package com.kunminx.viabus.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.kunminx.viabus.R;
import com.kunminx.viabus.repertory.bean.NoteBean;
import com.kunminx.viabus.business.bus.NoteBus;
import com.kunminx.viabus.databinding.AdapterTestListBinding;

import java.util.UUID;

/**
 * @author KunMinX
 * Create at 2018/9/20
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
    protected void onBindItem(AdapterTestListBinding binding, final NoteBean item, final RecyclerView.ViewHolder holder) {
        binding.tvTitle.setText(item.getTitle());
        Glide.with(mContext).load(item.getImgUrl()).into(binding.ivThumb);
        binding.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTitle(UUID.randomUUID().toString());
                notifyItemChanged(holder.getAdapterPosition());
                NoteBus.note().update(item);
            }
        });
        binding.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getList().remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                NoteBus.note().delete(item);
                return true;
            }
        });
    }
}
