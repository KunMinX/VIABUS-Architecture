package com.kunminx.viabus.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xmj
 * @date 2018/6/30
 */
public abstract class BaseBindingAdapter<M, B extends ViewDataBinding> extends RecyclerView.Adapter {

    protected Context mContext;
    protected List<M> mList;
    /**
     * 是否开启了筛选模式
     */
    protected boolean mFilterMode;

    public void setFilterMode(boolean filterMode) {
        mFilterMode = filterMode;
    }

    public boolean isFilterMode() {
        return mFilterMode;
    }

    public List<M> getList() {
        return mList;
    }

    /**
     * 完整的列表。相对于可能被筛选的list来说。
     */
    private List<M> mOriginalList = new ArrayList<>();

    public List<M> getOriginalList() {
        return mOriginalList;
    }

    public BaseBindingAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
    }

    public void setList(List<M> list) {
        if (list != null) {
            mList.clear();
            mList.addAll(list);
            if (!mFilterMode) {
                mOriginalList.clear();
                mOriginalList.addAll(mList);
            }
        }
    }

    @Override
    public int getItemCount() {
        return null == this.mList ? 0 : this.mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext), this.getLayoutResId(viewType), parent, false);
        return new BaseBindingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        this.onBindItem(binding, this.mList.get(position), position);
    }

    protected abstract @LayoutRes
    int getLayoutResId(int viewType);

    protected abstract void onBindItem(B binding, M item, int position);

    public class BaseBindingViewHolder extends RecyclerView.ViewHolder {
        BaseBindingViewHolder(View itemView) {
            super(itemView);
        }
    }
}