package com.amunocis.novaera.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amunocis.novaera.model.local.dao.PhoneDao
import com.amunocis.novaera.model.local.entity.DetailEntity
import com.amunocis.novaera.model.local.entity.PhoneEntity

@Database(entities = [PhoneEntity::class, DetailEntity::class], version = 1, exportSchema = false)
abstract class PhoneDatabase: RoomDatabase() {
    abstract fun phoneDao(): PhoneDao

    companion object {
        @Volatile
        private var INSTANCE: PhoneDatabase? = null

        fun getDatabase(context: Context): PhoneDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDatabase::class.java,
                    "phone_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}