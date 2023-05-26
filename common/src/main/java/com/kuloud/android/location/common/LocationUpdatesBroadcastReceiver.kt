package com.kuloud.android.location.common

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kuloud.android.location.common.data.LocationRepository
import com.kuloud.android.location.common.data.db.LocationEntity
import java.util.Date
import java.util.concurrent.Executors

private const val TAG = "LUBroadcastReceiver"

/**
 * @author kuloud
 * @date 2023/5/24
 */
class LocationUpdatesBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() context:$context, intent:$intent")

        if (intent.action == ACTION_PROCESS_UPDATES) {

            // Checks for location availability changes.
//            LocationAvailability.extractLocationAvailability(intent)?.let { locationAvailability ->
//                if (!locationAvailability.isLocationAvailable) {
//                    Log.d(TAG, "Location services are no longer available!")
//                }
//            }
//
//            LocationResult.extractResult(intent)?.let { locationResult ->
//                val locations = locationResult.locations.map { location ->
//                    LocationEntity(
//                        latitude = location.latitude,
//                        longitude = location.longitude,
//                        foreground = isAppInForeground(context),
//                        date = Date(location.time)
//                    )
//                }
//                if (locations.isNotEmpty()) {
//                    LocationRepository.getInstance(context, Executors.newSingleThreadExecutor())
//                        .addLocations(locations)
//                }
//            }
        }
    }

    // Note: This function's implementation is only for debugging purposes. If you are going to do
    // this in a production app, you should instead track the state of all your activities in a
    // process via android.app.Application.ActivityLifecycleCallbacks's
    // unregisterActivityLifecycleCallbacks(). For more information, check out the link:
    // https://developer.android.com/reference/android/app/Application.html#unregisterActivityLifecycleCallbacks(android.app.Application.ActivityLifecycleCallbacks
    private fun isAppInForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses ?: return false

        appProcesses.forEach { appProcess ->
            if (appProcess.importance ==
                ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                appProcess.processName == context.packageName) {
                return true
            }
        }
        return false
    }

    companion object {
        const val ACTION_PROCESS_UPDATES =
            "com.google.android.gms.location.sample.locationupdatesbackgroundkotlin.action." +
                    "PROCESS_UPDATES"
    }
}