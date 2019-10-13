package com.softrunapps.paginatedrecyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaginatedRecyclerView extends RecyclerView {
    private Context mContext;
    private OnPaginationListener onPaginationListener;
    private boolean loadingNewItems = true;
    private int mLoadingPage = 1;

    public PaginatedRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PaginatedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PaginatedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        setDefaultValues();
        initPaginating();

    }

    private void setDefaultValues() {
        setLayoutManager(new LinearLayoutManager(mContext));
        setHasFixedSize(true);
    }

    private void initPaginating() {
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean endHasBeenReached = lastVisible + 2 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    if (onPaginationListener != null) {
                        if (!loadingNewItems) {
                            loadingNewItems = true;
                            onPaginationListener.onNextPage(mLoadingPage);
                        }
                    }
                }
            }
        });
    }

    private void setAdapterListener() {
        PaginatedAdapter paginatedAdapter = (PaginatedAdapter) getAdapter();
        paginatedAdapter.setOnPaginationListener(new PaginatedAdapter.OnPaginationListener() {
            @Override
            public void onNextPage(int page) {
                if (onPaginationListener != null) {
                    mLoadingPage = page;
                    loadingNewItems = false;
                }
            }

            @Override
            public void onFinish() {
                if (onPaginationListener != null) {
                    onPaginationListener.onFinish();
                }
            }
        });
    }

    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        setAdapterListener();
    }

    public void setLayoutManager(@Nullable LayoutManager layout) {
        super.setLayoutManager(layout);
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        super.setHasFixedSize(hasFixedSize);
    }

    public void setOnPaginationListener(OnPaginationListener onPaginationListenre) {
        this.onPaginationListener = onPaginationListenre;
    }

    public interface OnPaginationListener {
        void onNextPage(int page);

        void onFinish();
    }


}
