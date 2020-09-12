package com.illume.pensieve.utils

import android.content.Context
import android.location.Location
import android.location.LocationManager

class Utils{
    fun getCurrentLocation(context: Context?): Location? {
        var loc = Location("")
        if(context == null) return loc

        val mLocationManager =
            context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = mLocationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            try {
                val l = mLocationManager.getLastKnownLocation(provider) ?: continue
//                Log.e("$l.accuracy", "$bestLocation?.accuracy")
                if (bestLocation == null || l.getAccuracy() < bestLocation.accuracy
                    || provider.equals("network") // it's possible that gps doesn't have an up to date fix
                ) {
                    // Found best last known location: %s", l);
                    bestLocation = l
                }
            } catch (e: SecurityException) {
            }
        }
        if (bestLocation != null) {
            loc = bestLocation
        }
        return loc
    }
}