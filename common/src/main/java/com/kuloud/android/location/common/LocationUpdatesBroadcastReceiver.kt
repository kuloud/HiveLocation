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
        if (intent.action == ACTION_PROCESS_UPDATES) {

            // Checks for location availability changes.
            LocationAvailability.extractLocationAvailability(intent)?.let { locationAvailability ->
                Log.d(TAG, "locationAvailability: $locationAvailability")
                if (!locationAvailability.isLocationAvailable) {
                    Log.d(TAG, "Location services are no longer available!")
                }
            }

            LocationResult.extractResult(intent)?.let { locationResult ->
                Log.d(TAG, "locationResult: $locationResult")
                val locations = locationResult.locations.map { location ->
                    LocationEntity(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        foreground = isAppInForeground(context),
                        date = Date(location.timeMs)
                    )
                }
                if (locations.isNotEmpty()) {
                    LocationRepository.getInstance(context, Executors.newSingleThreadExecutor())
                        .addLocations(locations)
                }
            }
        }
    }

    /**
     * TODO Application.ActivityLifecycleCallbacks
     */
    private fun isAppInForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses ?: return false

        appProcesses.forEach { appProcess ->
            if (appProcess.importance ==
                ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                appProcess.processName == context.packageName
            ) {
                return true
            }
        }
        return false
    }

    companion object {
        const val ACTION_PROCESS_UPDATES = "com.kuloud.android.location.action.PROCESS_UPDATES"
        const val EXTRA_LOCATION_AVAILABILITY =
            "com.kuloud.android.location.EXTRA_LOCATION_AVAILABILITY"
        const val EXTRA_LOCATION_RESULT = "com.kuloud.android.location.EXTRA_LOCATION_RESULT"
    }
}