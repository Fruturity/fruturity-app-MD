package com.ardine.fruturity.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ardine.fruturity.data.local.FruitBookmark
import com.ardine.fruturity.data.local.FruitDao

@Database(
    entities = [FruitBookmark::class],
    version = 1,
    exportSchema = false
)
abstract class FruitDatabase : RoomDatabase() {

    abstract fun FruitDao(): FruitDao

    companion object {
        @Volatile
        private var INSTANCE: FruitDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FruitDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FruitDatabase::class.java, "fruits_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}