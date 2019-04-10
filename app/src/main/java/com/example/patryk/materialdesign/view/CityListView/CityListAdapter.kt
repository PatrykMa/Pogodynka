package com.example.patryk.materialdesign.view.CityListView

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.patryk.materialdesign.R
import com.example.patryk.materialdesign.WeatherInfo


class CityListAdapter(val items : Array<String>, val context: Context, var listener: View.OnLongClickListener):
    RecyclerView.Adapter<CityListAdapter.Holder>() {

    inner class Holder(var view: View): RecyclerView.ViewHolder(view)
    {


        var city=view.findViewById<TextView>(R.id.textView_cityName)


        fun bindData(name: String)
        {
           city.text = name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Holder {
        //return Holder(LayoutInflater.from(context).inflate(R.layout.mini_weather_list_item, parent, false))
        return Holder(LayoutInflater.from(context).inflate(R.layout.list_city_view, parent, false).apply {
            setOnLongClickListener(listener)})

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(items[position])
    }

}