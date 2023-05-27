package com.kuloud.android.location.common

import android.content.Intent
import android.os.Build
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.EXTRA_LOCATION_AVAILABILITY

/**
 * @author kuloud
 * @date 2023/5/27
 */
class LocationAvailability {
    var isLocationAvailable: Boolean = false

    companion object {
        fun extractLocationAvailability(intent: Intent): LocationAvailability? {
            return if (hasLocationAvailability(intent)) {
                null
            } else {
                val locationAvailability =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intent.extras?.getParcelable(
                            EXTRA_LOCATION_AVAILABILITY, LocationAvailability::class.java
                        )
                    } else {
                        intent.extras?.getParcelable(EXTRA_LOCATION_AVAILABILITY)
                    }
                locationAvailability
            }
        }

        private fun hasLocationAvailability(intent: Intent): Boolean {
            return intent.hasExtra(EXTRA_LOCATION_AVAILABILITY)
        }
    }
}