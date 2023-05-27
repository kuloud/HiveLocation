package com.kuloud.android.location.baidu

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.kuloud.android.location.common.FusedLocationClient
import com.kuloud.android.location.common.LocationRequest
import com.kuloud.android.location.common.LocationResult
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.ACTION_PROCESS_UPDATES
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.EXTRA_LOCATION_AVAILABILITY
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.EXTRA_LOCATION_RESULT

/**
 * @author kuloud
 * @date 2023/5/26
 */
class BaiduFusedLocationClient(context: Context) : FusedLocationClient(
    context
) {

    private val innerClient: LocationClient = LocationClient(context)

    init {
        innerClient.registerLocationListener(object : BDAbstractLocationListener() {
            override fun onReceiveLocation(location: BDLocation?) {
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
                    intent.putExtra(EXTRA_LOCATION_AVAILABILITY, 1)
                    intent.putExtra(EXTRA_LOCATION_RESULT, LocationResult())
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

    override fun removeLocationUpdates(locationUpdatePendingIntent: PendingIntent) {
        innerClient.stop()
    }
}