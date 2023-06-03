package com.kuloud.android.location.amap

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.kuloud.android.location.common.LocationAvailability
import com.kuloud.android.location.common.LocationBackend
import com.kuloud.android.location.common.LocationRequest
import com.kuloud.android.location.common.LocationResult
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver

private const val TAG = "AMapFusedLocationClient"

/**
 * @author kuloud
 * @date 2023/5/26
 */
class AMapLocationBackend(context: Context) : LocationBackend(
    context
) {

    private val innerClient: AMapLocationClient = AMapLocationClient(context)

    init {

        innerClient.setLocationListener { location ->
            Log.d(TAG, "onReceiveLocation: $location")
            location?.apply {
                val intent = Intent(LocationUpdatesBroadcastReceiver.ACTION_PROCESS_UPDATES)

                /**
                 * https://lbs.amap.com/api/android-location-sdk/guide/utilities/errorcode
                 */
                val locationAvailability = LocationAvailability()
                val status = when (errorCode) {
                    AMapLocation.LOCATION_SUCCESS -> LocationAvailability.STATUS_SUCCESSFUL
                    AMapLocation.ERROR_CODE_UNKNOWN -> LocationAvailability.STATUS_UNKNOWN
                    else -> LocationAvailability.STATUS_UNSUCCESSFUL
                }
                locationAvailability.status = status
                intent.putExtra(
                    LocationUpdatesBroadcastReceiver.EXTRA_LOCATION_AVAILABILITY,
                    locationAvailability
                )
                val locationResult = LocationResult()
                locationResult.locations = arrayListOf(location.toFusedLocation())
                intent.putExtra(
                    LocationUpdatesBroadcastReceiver.EXTRA_LOCATION_RESULT,
                    locationResult
                )
                intent.setPackage(context.packageName)
                context.sendBroadcast(intent)
            }
        }
    }

    override fun requestLocationUpdates(
        locationRequest: LocationRequest, locationUpdatePendingIntent: PendingIntent
    ) {
        innerClient.setLocationOption(locationRequest.convertToLocationClientOption())
        innerClient.startLocation()
    }

    override fun setAgreePrivacy(boolean: Boolean) {
        AMapLocationClient.updatePrivacyShow(context, true, true)
        AMapLocationClient.updatePrivacyAgree(context, true)
    }

    override fun removeLocationUpdates(locationUpdatePendingIntent: PendingIntent) {
        innerClient.stopLocation()
    }
}