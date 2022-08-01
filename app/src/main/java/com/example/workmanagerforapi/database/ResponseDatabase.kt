package com.example.workmanagerforapi.database

import android.content.Context
import androidx.room.*

@Database(entities = [ResponseDB::class], version = 1, exportSchema = false)
abstract class ResponseDatabase : RoomDatabase() {

    abstract fun responseDao() : ResponseDAO

    companion object{
        @Volatile
        private var INSTANCE : ResponseDatabase? = null

        fun getDatabase(context : Context): ResponseDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ResponseDatabase::class.java,
                    "database").build()

            }
            return INSTANCE!!
        }
    }
}