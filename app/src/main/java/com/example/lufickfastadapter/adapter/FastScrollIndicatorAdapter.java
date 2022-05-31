package com.example.lufickfastadapter.adapter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;

import java.util.List;

public class FastScrollIndicatorAdapter <Item extends IItem> extends RecyclerView.Adapter  {

    FastAdapter mFastAdapter;

    public FastScrollIndicatorAdapter<Item> wrap(FastAdapter fastAdapter) {
        //this.mParentAdapter = abstractAdapter;
        this.mFastAdapter = fastAdapter;
        return this;
    }


    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        if (mFastAdapter != null) {
            mFastAdapter.registerAdapterDataObserver(observer);
        }
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        if (mFastAdapter != null) {
            mFastAdapter.unregisterAdapterDataObserver(observer);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mFastAdapter.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return mFastAdapter.getItemId(position);
    }

    public FastAdapter<Item> getFastAdapter() {
        return mFastAdapter;
    }

    public Item getItem(int position) {
        return (Item) mFastAdapter.getItem(position);
    }

    @Override
    public int getItemCount() {
        return mFastAdapter.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mFastAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mFastAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        mFastAdapter.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        mFastAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        mFastAdapter.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return mFastAdapter.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mFastAdapter.onViewDetachedFromWindow(holder);
    }

      @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mFastAdapter.onViewAttachedToWindow(holder);
    }
   @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mFastAdapter.onAttachedToRecyclerView(recyclerView);
    }

     @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mFastAdapter.onDetachedFromRecyclerView(recyclerView);
    }
}
