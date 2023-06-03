package com.kuloud.android.location.common.data.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.Date
import java.util.UUID

/**
 * @author kuloud
 * @date 2023/5/24
 */
@Entity(tableName = "location_table")
data class LocationEntity(
    @PrimaryKey var id: UUID = UUID.randomUUID(),
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var foreground: Boolean = true,
    var date: Date = Date()
) {
    @Ignore
    constructor() : this(UUID.randomUUID())

    override fun toString(): String {
        val appState = if (foreground) {
            "in app"
        } else {
            "in BG"
        }

        return "$latitude, $longitude $appState on " + "${
            DateFormat.getDateTimeInstance().format(date)
        }.\n"
    }
}