package com.example.patryk.materialdesign.view.WeatherView

import android.content.Context
import android.graphics.BitmapFactory
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.example.patryk.materialdesign.DayWeather
import com.example.patryk.materialdesign.R
import com.example.patryk.materialdesign.WeatherInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * TODO: document your custom view class.
 */
class MyView : LinearLayout {

    companion object {
        private var isHide=true
    }
    private var miniListener: SmalVievClickListener? = null

    //internal var selected=0
    var data: DayWeather?=null
        set(value) {
            field=value
            if (value!=null) {
                //selected = 0
                displayedData = value.day
                hoursData=value.houers
            }
        }

    private var displayedData: WeatherInfo?=null
        set(value) {
            field=value
            if (value==null) return
            findViewById<TextView>(R.id.TextView_cloudy)?.text=
              resources.getString(R.string.detailedWeatherInfo_cloudy).replace("%s",value.cloud.toString())
            findViewById<TextView>(R.id.textView_temperature)?.text=
              resources.getString(R.string.detailedWeatherInfo_temperature).replace("%s","%.1f".format(value.currentTemp))
            findViewById<TextView>(R.id.textView_name)?.text=
              resources.getString(R.string.detailedWeatherInfo_cityName).replace("%s",value.cityName)
            findViewById<TextView>(R.id.textView_description)?.text=
              resources.getString(R.string.detailedWeatherInfo_description).replace("%s",value.weatherDescription)
            findViewById<TextView>(R.id.textView_hum)?.text=
              resources.getString(R.string.detailedWeatherInfo_humidity).replace("%s",value.humidity.toString())
            findViewById<TextView>(R.id.textView_time)?.text=
              resources.getString(R.string.detailedWeatherInfo_time).replace("%s",value.date + " " +value.time)
            findViewById<TextView>(R.id.textView_wind)?.text=
                    resources.getString(R.string.detailedWeatherInfo_wind).replace("%s","%.1f".format(value.windSpeed))
            findViewById<ImageView>(R.id.imageView_icco).setImageBitmap(BitmapFactory.decodeResource(resources, value.veatherType.resID))
        }
    private var hoursData:Array<WeatherInfo> = arrayOf()
        set(value) {
            field=value
            val contener=findViewById<LinearLayout>(R.id.linearLayout_hourData)
            contener.removeAllViews()
            value.forEach {
                contener.addView(MiniWeatherView(context).apply {
                    data=it
                    setOnClickListener(miniListener)
                })
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
        inflate(context, R.layout.my_view, this)

        findViewById<HorizontalScrollView>(R.id.horizontal_container).isHorizontalScrollBarEnabled = false
        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val scroll =findViewById<HorizontalScrollView>(R.id.horizontal_container)
            TransitionManager.beginDelayedTransition(scroll)
                changeHourViewVisibility()


        }
        miniListener = SmalVievClickListener()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        updateHourViewVisibility()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun updateHourViewVisibility()
    {
        val scroll =findViewById<HorizontalScrollView>(R.id.horizontal_container)
        scroll.visibility = when(isHide)
        {
            true -> View.GONE
            else -> View.VISIBLE
        }
    }

    private fun changeHourViewVisibility()
    {
        isHide = isHide.not()
        updateHourViewVisibility()
    }
    inner class SmalVievClickListener:View.OnClickListener
    {
        override fun onClick(v: View?) {
            displayedData= (v as MiniWeatherView).data
        }
    }

}
