package com.example.patryk.materialdesign.view.WeatherView

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.patryk.materialdesign.R
import com.example.patryk.materialdesign.WeatherInfo

class SmallWeatherCardAdapter(val items : Array<WeatherInfo>, val context: Context,var listener:View.OnClickListener):
    RecyclerView.Adapter<SmallWeatherCardAdapter.Holder>() {

    inner class Holder(var view:View):RecyclerView.ViewHolder(view)
    {
        var weatherInfo:WeatherInfo? = null
            set(value) {
                field = value
                if(value != null) {
                    description.text = value.weatherDescription
                    city.text = value?.cityName
                    temp.text = "%.1f".format(value.currentTemp)
                    image.setImageBitmap(BitmapFactory.decodeResource(context.resources, value?.veatherType.resID))
                }
            }


        var description=view.findViewById<TextView>(R.id.textView_description)
        var city=view.findViewById<TextView>(R.id.textView_CityName)
        var temp=view.findViewById<TextView>(R.id.textView_temperature)
        var image = view.findViewById<ImageView>(R.id.imageView_current)

        fun bindData(info: WeatherInfo)
        {
            //weatherInfo = info
            (view as WeatherRecycleViewListItem).weatherInfo = info
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //return Holder(LayoutInflater.from(context).inflate(R.layout.mini_weather_list_item, parent, false))
        return Holder(WeatherRecycleViewListItem(context).apply {
            setOnClickListener ( listener )
        })
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(items[position])
    }
}