package com.example.patryk.materialdesign.view.WeatherView

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.patryk.materialdesign.R
import com.example.patryk.materialdesign.WeatherInfo

class WeatherRecycleViewListItem:LinearLayout, WeatherInfoView {

    var description:TextView? = null
    var city:TextView? = null
    var temp:TextView? = null
    var image:ImageView? = null

    var weatherInfo:WeatherInfo? = null
        set(value) {
            field = value
            if(value != null) {
                description?.text = value.weatherDescription
                city?.text = value?.cityName
                temp?.text = "%.1f".format(value.currentTemp)
                image?.setImageBitmap(BitmapFactory.decodeResource(context.resources, value?.veatherType.resID))
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

    override fun onDraw(canvas: Canvas?) {
        //layoutParams.width = LayoutParams.MATCH_PARENT
        super.onDraw(canvas)
    }
    private fun init(attrs: AttributeSet?, defStyle: Int) {
        inflate(context, R.layout.mini_weather_list_item, this)
       // layoutParams =LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT)
        description = findViewById<TextView>(R.id.textView_description)
        city = findViewById<TextView>(R.id.textView_CityName)
        temp = findViewById<TextView>(R.id.textView_temperature)
        image = findViewById<ImageView>(R.id.imageView_current)
    }

    override fun onAttachedToWindow() {
        layoutParams.width = LayoutParams.MATCH_PARENT
        requestLayout()
        super.onAttachedToWindow()
    }




    override fun getInfo(): WeatherInfo {
        return when(weatherInfo != null){
            true -> weatherInfo!!
            false ->WeatherInfo()
        }
    }
}