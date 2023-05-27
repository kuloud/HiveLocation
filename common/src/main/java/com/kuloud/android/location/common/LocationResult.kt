package com.kuloud.android.location.common

import android.content.Intent
import android.location.Location
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author kuloud
 * @date 2023/5/27
 */
@Parcelize
class LocationResult: Parcelable {
    var locations: List<Location> = emptyList()

    companion object {

        fun extractResult(intent: Intent): LocationResult? {
            TODO("Not yet implemented")
        }
    }
}