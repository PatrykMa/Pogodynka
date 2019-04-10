package com.example.patryk.materialdesign.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.patryk.materialdesign.R
import com.example.patryk.materialdesign.view.CityListView.CityListFragment
import com.example.patryk.materialdesign.view.WeatherView.DetailedWeatherInformationFragment
import com.example.patryk.materialdesign.view.WeatherView.WeatherInfoFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*


class MainActivity : AppCompatActivity(), WeatherInfoFragment.OnFragmentInteractionListener,
    DetailedWeatherInformationFragment.OnFragmentInteractionListener,NavigationView.OnNavigationItemSelectedListener  {

    private var weatherFragment: WeatherInfoFragment?=null
    private var gpsSettingsFragment : GpsSettingsFragment? = null
    private var observalbeCitysFragment : CityListFragment? = null

    //toDo how do it better
    private var isWeatherFragmentDisplay = true

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentManager = supportFragmentManager.beginTransaction()
        isWeatherFragmentDisplay = false
        when (item.itemId) {

            R.id.nav_weather -> {
                fragmentManager.replace(R.id.main, weatherFragment as Fragment).commit()
                isWeatherFragmentDisplay = true
            }
            R.id.nav_gps -> {
                if(gpsSettingsFragment == null ){
                    gpsSettingsFragment = GpsSettingsFragment().apply { activity = this@MainActivity }
                }
                fragmentManager.replace(R.id.main, gpsSettingsFragment as Fragment).commit()
            }
            R.id.nav_city ->{
                if(observalbeCitysFragment == null ){
                    observalbeCitysFragment = CityListFragment()
                }
                fragmentManager.replace(R.id.main, observalbeCitysFragment as Fragment).commit()
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onBackPressed() {

        if (isWeatherFragmentDisplay)
        {
            if(weatherFragment?.onBackPressed()!!)
                return
        }

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        if (weatherFragment==null)
            weatherFragment = WeatherInfoFragment()
        if(savedInstanceState==null) {
            val fragmentManager = supportFragmentManager.beginTransaction()
            fragmentManager.add(R.id.main, weatherFragment as Fragment).commit()
        }
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_weather)

    }

    override fun onStart() {
        super.onStart()

    }



}
