package com.pelletierantoine.mysephoratest.ui.adapters

import androidx.recyclerview.widget.DiffUtil

internal class DiffUtilCallback<O>(
    private val oldItems: List<O>,
    private val newItems: List<O>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}