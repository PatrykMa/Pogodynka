package com.example.patryk.materialdesign.view.CityListView


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.patryk.materialdesign.DatabaseManager
import com.example.patryk.materialdesign.R


class CityListFragment : Fragment() {

    var cityList:Array<String> = arrayOf()
        set(value) {
            field = value
            val recycleView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = RecyclerView.VERTICAL
            recycleView?.setLayoutManager(linearLayoutManager)
            //recyclerView?.setLayoutManager(LinearLayoutManager())
            val adapter = CityListAdapter(
                value,
                context!!,
                RecycleViewLongClick()
            )
            recycleView?.adapter= adapter
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onResume() {
        view?.findViewById<Button>(R.id.button_addCity)?.setOnClickListener {
            val cityNameToAdd = view?.findViewById<EditText>(R.id.editText_cityName)?.text.toString()
            if (cityNameToAdd != "" ) {
                DatabaseManager.addCity(cityNameToAdd)
                cityList = DatabaseManager.citys
            }
        }
        cityList = DatabaseManager.citys
        super.onResume()
    }


    inner class RecycleViewLongClick():View.OnLongClickListener
    {
        override fun onLongClick(v: View?): Boolean {
            return true
        }
    }


}
