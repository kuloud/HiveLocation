package com.kuloud.android.location.google

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import androidx.annotation.MainThread
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.kuloud.android.location.common.LocationBackend
import com.kuloud.android.location.common.utils.hasPermission
import java.util.concurrent.TimeUnit
import com.kuloud.android.location.common.LocationRequest as HiveLocationRequest


private const val TAG = "GLocationReceived"

/**
 * @author kuloud
 * @date 2023/5/26
 */
class GoogleLocationBackend(context: Context) : LocationBackend(
    context
) {

    private val innerClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationRequest: LocationRequest = LocationRequest().apply {
        // Sets the desired interval for active location updates. This interval is inexact. You
        // may not receive updates at all if no location sources are available, or you may
        // receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        //
        // IMPORTANT NOTE: Apps running on "O" devices (regardless of targetSdkVersion) may
        // receive updates less frequently than this interval when the app is no longer in the
        // foreground.
        interval = TimeUnit.SECONDS.toMillis(60)

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        fastestInterval = TimeUnit.SECONDS.toMillis(30)

        // Sets the maximum time when batched location updates are delivered. Updates may be
        // delivered sooner than this interval.
        maxWaitTime = TimeUnit.MINUTES.toMillis(2)

        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    @Throws(SecurityException::class)
    @MainThread
    override fun requestLocationUpdates(
        hiveLocationRequest: HiveLocationRequest, locationUpdatePendingIntent: PendingIntent
    ) {
        if (!context.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) return
        try {
            innerClient.requestLocationUpdates(locationRequest, locationUpdatePendingIntent)
        } catch (permissionRevoked: SecurityException) {
            throw permissionRevoked
        }

    }

    override fun setAgreePrivacy(agree: Boolean) {
        // ignore
    }

    override fun removeLocationUpdates(locationUpdatePendingIntent: PendingIntent) {
        innerClient.removeLocationUpdates(locationUpdatePendingIntent)
    }
}