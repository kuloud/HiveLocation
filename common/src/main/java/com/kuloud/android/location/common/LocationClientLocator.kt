package com.kuloud.android.location.common

/**
 * @author kuloud
 * @date 2023/5/26
 */
class LocationClientLocator {
    var locationBackend: LocationBackend? = null

    companion object {
        @Volatile
        private var INSTANCE: LocationClientLocator? = null

        fun getInstance(): LocationClientLocator {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationClientLocator()
                    .also { INSTANCE = it }
            }
        }
    }
}