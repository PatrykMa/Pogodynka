package com.example.patryk.materialdesign.view.WeatherView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper
import com.example.patryk.materialdesign.DayWeather
import com.example.patryk.materialdesign.R
import java.lang.Exception

class OneMeassureFlipper:ViewFlipper {
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs) {
    }
    var mainCitys = arrayOf<DayWeather>()
    set(value) {
        field = value
        removeAllViews()
        value.forEach {
            addView(MyView(context).apply { data=it })
        }
        if (value.isNotEmpty()) {
            showNext()
            showPrevious()
        }
        var x =0
    }
    private var initialX: Float = 0.toFloat()
    var distanceToSlide = 210

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        try {
            getChildAt(displayedChild).measure(
                widthMeasureSpec,
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            val activeChildHeight = getChildAt(displayedChild).measuredHeight
            if (activeChildHeight != 0) {
                var z = View.MeasureSpec.makeMeasureSpec(activeChildHeight, View.MeasureSpec.EXACTLY) + 9
                super.onMeasure(widthMeasureSpec, z)
            } else super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }catch (e:Exception)
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            Log.e("measure error","co kurde")
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {initialX = event.x; return true}
            MotionEvent.ACTION_UP -> {
                val finalX = event.x
                if(Math.abs(initialX - finalX) > distanceToSlide)
                try {


                    if (childCount == 0) return false
                    if (initialX > finalX) {
                        val nextNumber = if (displayedChild == childCount - 1) 0 else displayedChild + 1
                        getChildAt(displayedChild).elevation = 7f
                        getChildAt(nextNumber).elevation = 8f
                        setInAnimation(AnimationUtils.loadAnimation(context,
                            R.anim.in_from_right
                        ))
                        setOutAnimation(AnimationUtils.loadAnimation(context,
                            R.anim.out_to_back
                        ))

                        showNext()
                    } else {
                        val prevNumber = if (displayedChild == 0) childCount - 1 else displayedChild - 1
                        getChildAt(displayedChild).elevation = 8f
                        getChildAt(prevNumber).elevation = 7f
                        setInAnimation(AnimationUtils.loadAnimation(context,
                            R.anim.in_from_back
                        ))
                        setOutAnimation(AnimationUtils.loadAnimation(context,
                            R.anim.out_from_right
                        ))


                        showPrevious()
                    }
                }
                catch (e:Exception){
                    return false
                }//getChildAt(displayedChild).elevation = 8f
            }
        }
        return false
    }
}