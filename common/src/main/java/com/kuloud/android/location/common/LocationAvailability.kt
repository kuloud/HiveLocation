package com.kuloud.android.location.common

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import com.kuloud.android.location.common.LocationUpdatesBroadcastReceiver.Companion.EXTRA_LOCATION_AVAILABILITY
import kotlinx.parcelize.Parcelize

/**
 * @author kuloud
 * @date 2023/5/27
 */
@Parcelize
data class LocationAvailability(var status: Int = STATUS_UNKNOWN) : Parcelable {
    var isLocationAvailable: Boolean = status == STATUS_SUCCESSFUL


    companion object {
        val STATUS_UNKNOWN = 0
        val STATUS_SUCCESSFUL = 1
        val STATUS_UNSUCCESSFUL = 2

        fun extractLocationAvailability(intent: Intent): LocationAvailability? {
            return if (!hasLocationAvailability(intent)) {
                null
            } else {
                val locationAvailability =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(
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

    override fun toString(): String {
        return "LocationAvailability(status=$status)"
    }
}