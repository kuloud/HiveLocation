package com.kuloud.android.location.common

import android.app.PendingIntent
import android.content.Context

/**
 * @author kuloud
 * @date 2023/5/24
 */
abstract class LocationBackend(val context: Context) {

    /**
     * 隐私政策设置
     */
    abstract fun setAgreePrivacy(agreed: Boolean)

    abstract fun requestLocationUpdates(
        locationRequest: LocationRequest,
        locationUpdatePendingIntent: PendingIntent
    )

    abstract fun removeLocationUpdates(locationUpdatePendingIntent: PendingIntent)

}