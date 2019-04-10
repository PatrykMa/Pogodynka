package com.example.patryk.materialdesign.view.WeatherView

import android.content.Context
import android.net.Uri
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.patryk.materialdesign.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WeatherInfoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WeatherInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherInfoFragment : Fragment(),
    DetailedWeatherInformationFragment.OnFragmentInteractionListener {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null
    //todo change to normal context
    private var myContext:Context?=null
    private var defoultDataToDisplay = arrayOf<DayWeather>()
    private var fliper: OneMeassureFlipper? = null
    private var otherCity= arrayOf<WeatherInfo>()
        set(value) {
            if (context != null) {
                val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView_info)
                val linearLayoutManager = LinearLayoutManager(context)
                linearLayoutManager.orientation = RecyclerView.VERTICAL
                recyclerView?.setLayoutManager(linearLayoutManager)
                //recyclerView?.setLayoutManager(LinearLayoutManager())
                val adapter = SmallWeatherCardAdapter(
                    value,
                    context!!,
                    RecyclerViewObjectOnClickListener()
                )
                recyclerView?.adapter= adapter
            }
        }
    var mainCity = arrayOf<DayWeather>()
        set(value) {
            field = value
            fliper?.mainCitys = value
        }
    private fun setMainCity(weather:WeatherInfo)
    {
        mainCity = arrayOf(DayWeather().apply { day=weather })
        //toDo update data
    }

    fun onBackPressed():Boolean
    {
        if(!isDefoultdataDisplay()) {
            mainCity = defoultDataToDisplay
            return true
        }
        return false
    }
    private fun isDefoultdataDisplay():Boolean
    {
        return mainCity[0].day?.cityName == defoultDataToDisplay[0].day?.cityName
    }


    override fun onResume() {
        fliper = view?.findViewById(R.id.viewFlipper_detalFliper)
        super.onResume()

        WeatherManager(context!!).let {
           // var pager = view?.findViewById<ViewPager>(R.id.viewPager)
            //pager?.adapter = DetalWeathrViewAdapter(context!!, arrayOf(it.mainCity,it.mainCity))
            //mainCity= it.mainCity
            doAsync {
                val res = it.obserwableCities
                val mainCity = it.mainCity
                uiThread {
                    otherCity = res
                    this@WeatherInfoFragment.mainCity = mainCity
                    defoultDataToDisplay = mainCity
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fliper = view?.findViewById(R.id.viewFlipper_detalFliper)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //detailedFragment= DetailedWeatherInformationFragment()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weaher_info, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            myContext=context
        } else {
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WeatherInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            WeatherInfoFragment().apply {
                arguments = Bundle()
            }
    }


    inner class RecyclerViewObjectOnClickListener():View.OnClickListener
    {
        override fun onClick(v: View?) {
            setMainCity((v as WeatherInfoView).getInfo())
        }
    }
}
/*
class FlipperOnTouchListener(var context:Context?):View.OnTouchListener
{
    var mainCity = arrayOf<DayWeather>()
    private var initialX: Float = 0.toFloat()
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

            val fliper = v as ViewFlipper
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {initialX = event.x; return true}
                MotionEvent.ACTION_UP -> {
                    val finalX = event.x
                    fliper.getChildAt(0).elevation = 7f
                    fliper.getChildAt(1).elevation = 7f
                    if (initialX > finalX) {

                            fliper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.in_from_right))
                            fliper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.out_to_back))

                        fliper.showNext()
                    } else {

                            fliper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.in_from_back))
                            fliper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.out_from_right))


                        fliper.showPrevious()
                    }
                    fliper.getChildAt(fliper.displayedChild).elevation = 8f
                }
            }
            return false
    }
}

class DetalWeathrViewAdapter(var context : Context,var daysInfo:Array<DayWeather>):PagerAdapter()
{

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var view =MyView(context)
        view.data= daysInfo[position]
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view == `object`
    }

    override fun getCount(): Int {
        return daysInfo.size
    }
}*/
