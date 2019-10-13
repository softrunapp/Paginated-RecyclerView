package com.softrunapps.paginatedrecyclerview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PaginatedAdapter<ITEM, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<ITEM> mDataSet = new ArrayList<>();
    private OnPaginationListener mListener;
    private int mFirstPage = 1;
    private int mItemCount = 10;

    @NonNull
    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(@NonNull VH holder, int position);

    public int getItemCount() {
        return mDataSet.size();
    }

    public void submitItems(Collection<? extends ITEM> collection) {
        mDataSet.addAll(collection);
        notifyDataSetChanged();
        if (mListener != null) {
            if (collection.size() == mItemCount) {
                mListener.onNextPage(++mFirstPage);
            } else {
                mListener.onFinish();
            }
        }
    }

    protected ITEM getItem(int position) {
        return mDataSet.get(position);
    }

    public void setFirstPage(int mFirstPage) {
        this.mFirstPage = mFirstPage;
    }

    public int getFirstPage() {
        return mFirstPage;
    }

    public void setItemCount(int mItemCount) {
        this.mItemCount = mItemCount;
    }

    public void setOnPaginationListener(OnPaginationListener onPaginationListener) {
        this.mListener = onPaginationListener;
    }

    public interface OnPaginationListener {
        void onNextPage(int page);

        void onFinish();
    }
}
