package com.kuloud.android.location.baidu

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.BDLocation.TYPE_CLOSE_LOCATION_SERVICE_SWITCH_FAIL
import com.baidu.location.BDLocation.TYPE_NO_PERMISSION_LOCATION_FAIL
import com.baidu.location.BDLocation.TypeCriteriaException
import com.baidu.location.BDLocation.TypeGnssLocation
import com.baidu.location.BDLocation.TypeGpsLocation
import com.baidu.location.BDLocation.TypeNetWorkException
import com.baidu.location.BDLocation.TypeOffLineLocation
import com.baidu.location.BDLocation.TypeOffLineLocationFail
import com.baidu.location.BDLocation.TypeOffLineLocationNetworkFail
import com.baidu.location.LocationClient
import com.kuloud.android.location.common.HiveLocation
import com.kuloud.android.location.common.LocationAvailability
import com.kuloud.android.location.common.LocationBackend
import com.kuloud.android.location.common.LocationRequest
import com.kuloud.android.location.common.LocationResult
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.ACTION_PROCESS_UPDATES
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.EXTRA_LOCATION_AVAILABILITY
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.EXTRA_LOCATION_RESULT


private const val TAG = "BDLocationReceived"

/**
 * @author kuloud
 * @date 2023/5/26
 */
class BaiduLocationBackend(context: Context) : LocationBackend(
    context
) {

    private val innerClient: LocationClient = LocationClient(context)

    init {
        Log.d(TAG, "registerLocationListener ${innerClient.accessKey}")

        innerClient.registerLocationListener(object : BDAbstractLocationListener() {
            override fun onReceiveLocation(location: BDLocation?) {
                Log.d(TAG, "onReceiveLocation: $location")
                location?.apply {
                    val intent = Intent(ACTION_PROCESS_UPDATES)

                    /*
                    https://lbsyun.baidu.com/index.php?title=android-locsdk/guide/addition-func/error-code

                    public static final int TypeNone = 0;
                    public static final int TypeGpsLocation = 61;
                    public static final int TypeGnssLocation = 61;
                    public static final int TypeCriteriaException = 62;
                    public static final int TypeNetWorkException = 63;
                    public static final int TypeOffLineLocation = 66;
                    public static final int TypeOffLineLocationFail = 67;
                    public static final int TypeOffLineLocationNetworkFail = 68;
                    public static final int TYPE_CLOSE_LOCATION_SERVICE_SWITCH_FAIL = 69;
                    public static final int TYPE_NO_PERMISSION_LOCATION_FAIL = 70;
                    public static final int TYPE_NO_PERMISSION_AND_CLOSE_SWITCH_FAIL = 71;
                     */
                    val locationAvailability = LocationAvailability()
                    val status = when (locType) {
                        TypeGpsLocation, TypeGnssLocation, TypeOffLineLocation -> LocationAvailability.STATUS_SUCCESSFUL
                        TypeCriteriaException, TypeNetWorkException, TypeOffLineLocationFail, TypeOffLineLocationNetworkFail, TYPE_CLOSE_LOCATION_SERVICE_SWITCH_FAIL, TYPE_NO_PERMISSION_LOCATION_FAIL -> LocationAvailability.STATUS_UNSUCCESSFUL
                        else -> LocationAvailability.STATUS_UNKNOWN
                    }
                    locationAvailability.status = status
                    intent.putExtra(EXTRA_LOCATION_AVAILABILITY, locationAvailability)
                    val locationResult = LocationResult()
                    locationResult.locations = arrayListOf(location.toFusedLocation())
                    intent.putExtra(EXTRA_LOCATION_RESULT, locationResult)
                    intent.setPackage(context.packageName)
                    context.sendBroadcast(intent)
                }


            }
        })
    }

    override fun requestLocationUpdates(
        locationRequest: LocationRequest, locationUpdatePendingIntent: PendingIntent
    ) {
        innerClient.locOption = locationRequest.convertToLocationClientOption()
        innerClient.start()
    }

    override fun setAgreePrivacy(agree: Boolean) {
        LocationClient.setAgreePrivacy(agree)
    }

    override fun removeLocationUpdates(locationUpdatePendingIntent: PendingIntent) {
        innerClient.stop()
    }
}