package com.zerowater.presentation

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


/**
 * 어답터 아이템 설정
 *
 * @param recyclerView RecyclerView
 * @param adapter      RecyclerView.Adapter
 * @param dataList     설정할 아이템
 */
@BindingAdapter("recyclerViewAdapter", "items")
fun recyclerViewbindItem(
    recyclerView: RecyclerView,
    adapter: ListAdapter<*, *>?,
    dataList: List<*>?
) {

    recyclerView.adapter = adapter
    dataList?.let {
        adapter?.submitList(it as List<Nothing>?)
    }
}

/**
 * 아이템 데코레이션 설정
 *
 * @param recyclerView   RecyclerView
 * @param itemDecoration 설정할 데코레이션
 */
@BindingAdapter("itemDecoration")
fun addItemDecoration(recyclerView: RecyclerView, itemDecoration: RecyclerView.ItemDecoration?) {
    itemDecoration?.let { recyclerView.addItemDecoration(it) }
}