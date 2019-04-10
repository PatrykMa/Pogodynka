package com.example.patryk.materialdesign.view.WeatherView

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.patryk.materialdesign.R
import com.example.patryk.materialdesign.WeatherInfo

/**
 * TODO: document your custom view class.
 */
class MiniWeatherView : LinearLayout {


    var data: WeatherInfo?=null
        set(value) {field=value
            if(value!=null) {
                //todo replce with resource
                findViewById<TextView>(R.id.textView_hour).text = value.time
                findViewById<ImageView>(R.id.imageView_ico).setImageBitmap(BitmapFactory.decodeResource(resources, value.veatherType.resID))
                findViewById<TextView>(R.id.textView_temp).text=resources.getString(R.string.detailedWeatherInfo_temperature).replace("%s","%.1f".format(value.currentTemp))
                findViewById<ImageView>(R.id.imageView_ico).setImageBitmap(BitmapFactory.decodeResource(resources, value.veatherType.resID))
            }
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LinearLayout.inflate(context, R.layout.view_mini, this)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}
