package com.kuloud.android.location.common

import android.app.PendingIntent
import android.content.Context

/**
 * @author kuloud
 * @date 2023/5/24
 */
abstract class FusedLocationProviderClient(val context: Context) {

    abstract fun requestLocationUpdates(
        locationRequest: LocationRequest,
        locationUpdatePendingIntent: PendingIntent
    )

    abstract fun removeLocationUpdates(locationUpdatePendingIntent: PendingIntent)

}