package com.zerowater.presentation.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class VerticalItemDecoration
/**
 * 가로 아이템 구분 선
 *
 * @param space     간격
 */(
    /**
     * 간격
     */
    private val space: Int
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        val position = parent.getChildAdapterPosition(view)
        outRect.top = if (position == 0) 0 else space
        outRect.bottom = if (position + 1 < itemCount) space else 0
    }
}