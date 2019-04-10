package com.example.patryk.materialdesign.view


import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import com.example.patryk.materialdesign.DatabaseManager
import com.example.patryk.materialdesign.GPSPermissionManager
import com.example.patryk.materialdesign.R
import kotlinx.android.synthetic.main.fragment_gps_settings.*
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 *
 */
class GpsSettingsFragment() : Fragment() {
    var activity:Activity? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_gps_settings, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onResume() {
        val switch = view?.findViewById<Switch>(R.id.switch1)
        switch?.setOnCheckedChangeListener { buttonView, isChecked ->
            if(context != null)
                if(isChecked)
                {
                    if(GPSPermissionManager.hasGpsPermission(context!!))
                    {
                        DatabaseManager.usingLocalization = true
                    }
                    else
                    {
                        if (activity ==  null)
                            throw Exception("should set Activity")
                        GPSPermissionManager.askGpsPermission(activity!!)
                        buttonView.isChecked = false
                    }
                }
                else
                {
                    DatabaseManager.usingLocalization = false
                }

        }
        switch?.isChecked = DatabaseManager.usingLocalization
        val myCity = view?.findViewById<EditText>(R.id.editText)
        myCity?.setText(DatabaseManager.mainCity)
        super.onResume()
    }

    override fun onPause() {
        val textInTextView = view?.findViewById<EditText>(R.id.editText)?.text.toString()
        if (view?.findViewById<Switch>(R.id.switch1)?.isChecked?.not()!!)
            if(textInTextView != "")
                DatabaseManager.mainCity = textInTextView
        super.onPause()
    }


}
