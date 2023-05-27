package com.kuloud.android.location.common

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author kuloud
 * @date 2023/5/27
 */
@Parcelize
data class LocationResult(var locations: List<FusedLocation> = emptyList()) : Parcelable {

    companion object {

        fun extractResult(intent: Intent): LocationResult? {
            return if (!hasResult(intent)) {
                null
            } else {
                val result =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(
                            LocationUpdatesBroadcastReceiver.EXTRA_LOCATION_RESULT,
                            LocationResult::class.java
                        )
                    } else {
                        intent.extras?.getParcelable(LocationUpdatesBroadcastReceiver.EXTRA_LOCATION_RESULT)
                    }
                result
            }
        }

        private fun hasResult(intent: Intent): Boolean {
            return intent.hasExtra(LocationUpdatesBroadcastReceiver.EXTRA_LOCATION_RESULT)
        }
    }

    override fun toString(): String {
        return "LocationResult(locations=$locations)"
    }
}