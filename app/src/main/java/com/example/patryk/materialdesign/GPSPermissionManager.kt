package com.example.patryk.materialdesign

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.Exception


class GPSPermissionManager {
    companion object {
        private const val requestCode = 23

        fun hasGpsPermission(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        }

        fun askGpsPermission(activity: Activity) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
        }

        fun getLonAndLatCurLocalzaion(context: Context):Pair<Double,Double>
        {
            //ToDO implemet
            var lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if(hasGpsPermission(context)){
                try {
                    //val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                } catch (e:Exception)
                {

                }

            }
            return Pair(0.0,0.0)
        }
    }
}