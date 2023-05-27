package com.kuloud.android.location.common

/**
 * @author kuloud
 * @date 2023/5/26
 */
class LocationRequest(
    val priority: Int = PRIORITY_BALANCED_POWER_ACCURACY,
    val interval: Long = 3600000L
) {

    companion object {
        val PRIORITY_HIGH_ACCURACY = 100
        val PRIORITY_BALANCED_POWER_ACCURACY = 102
        val PRIORITY_LOW_ACCURACY = 103


    }
}