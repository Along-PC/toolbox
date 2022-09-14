package com.dragon.toolbox.blur

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BlurView : View {

    private val paint = Paint()

    private val rectF = RectF(100f, 100f, 300f, 300f)

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID)
        paint.color = Color.parseColor("#00ff00")
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(rectF, paint)
        canvas.drawCircle(800f,800f,300f,paint)
    }

}