package com.kuloud.android.location.common.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val DATABASE_NAME = "location-database"

/**
 * @author kuloud
 * @date 2023/5/24
 */
@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocationTypeConverters::class)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao

    companion object {
        // For Singleton instantiation
        @Volatile private var INSTANCE: LocationDatabase? = null

        fun getInstance(context: Context): LocationDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): LocationDatabase {
            return Room.databaseBuilder(
                context,
                LocationDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}