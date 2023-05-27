package com.kuloud.android.location.common

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author kuloud
 * @date 2023/5/27
 */
@Parcelize
class FusedLocation(
    val provider: String,
    val timeMs: Long, // timestamp in milliseconds
    val latitude: Double,
    val longitude: Double,
    val altitude: Float,
    val speed: Float,
    val bearing: Float,
    val extra: Bundle
) : Parcelable {
    override fun toString(): String {
        return "FusedLocation(provider='$provider', timeMs=$timeMs, latitude=$latitude, longitude=$longitude, altitude=$altitude, speed=$speed, bearing=$bearing, extra=$extra)"
    }
}
