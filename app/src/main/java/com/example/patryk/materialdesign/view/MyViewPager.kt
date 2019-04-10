package com.example.patryk.materialdesign.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.patryk.materialdesign.R

/**
 * TODO: document your custom view class.
 */
class MyViewPager : ViewPager {


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var height = 0
        //this.childre

        for (i in 0 until this.childCount) {
            val child = this.getChildAt(i)
            child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            val h = child.measuredHeight
            if (h > height) height = h
        }

         var z = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)

        super.onMeasure(widthMeasureSpec, z)
    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}
