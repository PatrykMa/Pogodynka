package com.example.patryk.materialdesign.view.WeatherView

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.patryk.materialdesign.R
import com.example.patryk.materialdesign.WeatherInfo


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailedWeatherInformationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailedWeatherInformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DetailedWeatherInformationFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    var displayedWeather:WeatherInfo?=null
        set(value) {field=value
            if(value==null)
                return
            view?.findViewById<TextView>(R.id.TextView_cloudy)?.text=
                    resources.getString(R.string.detailedWeatherInfo_cloudy).replace("%s",value.cloud.toString())
            view?.findViewById<TextView>(R.id.textView_temperature)?.text=
                    resources.getString(R.string.detailedWeatherInfo_temperature).replace("%s",value.currentTemp.toString())
            view?.findViewById<TextView>(R.id.textView_name)?.text=
                    resources.getString(R.string.detailedWeatherInfo_cityName).replace("%s",value.cityName)
            view?.findViewById<TextView>(R.id.textView_description)?.text=
                    resources.getString(R.string.detailedWeatherInfo_description).replace("%s",value.weatherDescription)
            view?.findViewById<TextView>(R.id.textView_hum)?.text=
                    resources.getString(R.string.detailedWeatherInfo_humidity).replace("%s",value.humidity.toString())
            view?.findViewById<TextView>(R.id.textView_time)?.text=
                    resources.getString(R.string.detailedWeatherInfo_time).replace("%s",value.time)
            view?.findViewById<TextView>(R.id.textView_wind)?.text=
                    resources.getString(R.string.detailedWeatherInfo_wind).replace("%s",value.windSpeed.toString())

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_weather_information, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            var x=0
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment DetailedWeatherInformationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            DetailedWeatherInformationFragment()
    }
}
