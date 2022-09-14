package com.dragon.toolbox.event

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.View.MeasureSpec.AT_MOST
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLayoutManager : LinearLayoutManager {

    private lateinit var context: Context

    var maxHeight: Int = 0

    constructor(context: Context, orientation: Int, reverse: Boolean) : super(
        context,
        orientation,
        reverse
    ) {
        this.context = context
    }

    constructor(context: Context) : super(context) {
        this.context = context
    }

    override fun setMeasuredDimension(childrenBounds: Rect?, wSpec: Int, hSpec: Int) {
        if (maxHeight == 0) {
            super.setMeasuredDimension(
                childrenBounds,
                wSpec,
                hSpec
            )
        } else {
            super.setMeasuredDimension(
                childrenBounds,
                View.MeasureSpec.makeMeasureSpec(maxHeight, AT_MOST),
                hSpec
            )
        }
    }
}