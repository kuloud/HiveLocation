package com.kuloud.android.location.baidu

import android.app.PendingIntent
import android.content.Context
import com.baidu.location.LocationClient
import com.kuloud.android.location.common.FusedLocationProviderClient
import com.kuloud.android.location.common.LocationRequest

/**
 * @author kuloud
 * @date 2023/5/26
 */
class BaiduFusedLocationProviderClient(context: Context) :
    FusedLocationProviderClient(
        context
    ) {

    private val innerClient: LocationClient = LocationClient(context)

    override fun requestLocationUpdates(
        locationRequest: LocationRequest,
        locationUpdatePendingIntent: PendingIntent
    ) {
        // TODO
    }

    override fun removeLocationUpdates(locationUpdatePendingIntent: PendingIntent) {
        // TODO
    }
}