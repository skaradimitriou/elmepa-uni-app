package com.stathis.core.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(
    private val top: Int = 30,
    private val start: Int = 30,
    private val end: Int = 30,
    private val bottom: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = top
        outRect.left = start
        outRect.right = end
        outRect.bottom = bottom
    }
}