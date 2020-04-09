package com.yl.markremember.callback

import androidx.recyclerview.widget.RecyclerView
import com.standards.library.adapter.BaseItemDraggableAdapter
import com.standards.library.adapter.BaseViewHolder
import com.standards.library.adapter.callback.ItemDragAndSwipeCallback

class HomeItemDragAndSwipeCallback<T, K : BaseViewHolder>(adapter: BaseItemDraggableAdapter<T,K>) : ItemDragAndSwipeCallback(adapter) {
    override fun onMove(recyclerView: RecyclerView, source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return true
    }
}