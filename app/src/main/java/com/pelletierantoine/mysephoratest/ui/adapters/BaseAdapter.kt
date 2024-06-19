package com.pelletierantoine.mysephoratest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class GenericAdapter<O, Binding : ViewBinding>(
    private val list: MutableList<O>
) : RecyclerView.Adapter<GenericAdapter.MyViewHolder<Binding>>() {

    abstract fun onBindData(model: O, position: Int, bindingAdapter: Binding)
    abstract fun onItemClick(model: O, position: Int, bindingAdapter: Binding)
    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder<Binding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder<Binding>, position: Int) {
        onBindData(list[position], position, holder.binding)

        holder.binding.root.setOnClickListener {
            onItemClick(list[position], position, holder.binding)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(items: List<O>, clear: Boolean = false) {
        if (clear) {
            list.clear()
        }

        val previous: Int = list.size
        list.addAll(items)
        if (clear) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(previous, list.size)
        }
    }

    fun addOrUpdateItem(item: O, predicate: (O) -> Boolean) {
        if (list.all(predicate)) {
            addItem(item)
        } else {
            val index: Int = list.indexOfFirst { !predicate(it) }

            if (index != -1) {
                updateItem(item, index)
            }
        }
    }

    fun addItem(item: O, predicate: (O) -> Boolean) {
        if (list.all(predicate)) {
            addItem(item)
        }
    }

    fun addItem(item: O, index: Int, predicate: (O) -> Boolean) {
        if (list.all(predicate)) {
            addItem(item, index)
        }
    }

    fun addItem(item: O, index: Int = list.size, clear: Boolean = false) {
        if (clear) {
            list.clear()
        }

        list.add(if (index <= list.size && index >= 0) index else 0, item)
        notifyItemInserted(index)
    }

    fun updateItem(predicate: (O) -> Boolean, item: O) {
        val index = list.indexOfFirst { predicate(it) }

        if (index != -1) {
            updateItem(item, index)
        }
    }

    open fun updateItem(item: O, index: Int) {
        list[index] = item
        notifyItemChanged(index)
    }

    fun removeItem(item: O) {
        val index = list.indexOfFirst { it == item }

        if (index != -1) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun removeItem(index: Int) {
        if (index < list.size && index >= 0) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    class MyViewHolder<Binding : ViewBinding>(val binding: Binding) : RecyclerView.ViewHolder(binding.root)
}