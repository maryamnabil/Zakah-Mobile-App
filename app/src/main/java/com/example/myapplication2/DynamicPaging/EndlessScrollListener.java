package com.example.myapplication2.DynamicPaging;

import android.util.Log;
import android.widget.AbsListView;

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
//
//
//    public EndlessScrollListener() {
//    }
//
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        int lastInScreen = firstVisibleItem + visibleItemCount;
//        Log.i("total",Integer.toString(totalItemCount));
//        Log.i("last",Integer.toString(lastInScreen));
//        Log.i("loading",Boolean.toString(loading));
//
//        if (lastInScreen == totalItemCount && totalItemCount!=0 && !loading) {
//            currentPage++;
//            onLoadMore(currentPage,totalItemCount);
//            loading = true;
//        }
//
//
//    }
//
//    // Defines the process for actually loading more data based on page
//    // Returns true if more data is being loaded; returns false if there is no more data to load.
//    public abstract boolean onLoadMore(int page, int totalItemsCount);
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        // Don't take any action on changed
//    }
}