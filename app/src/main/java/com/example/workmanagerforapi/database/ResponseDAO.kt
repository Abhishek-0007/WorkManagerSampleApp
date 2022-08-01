package com.example.workmanagerforapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResponseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addData(responseDB: List<ResponseDB>)

    @Query("SELECT * FROM response_data")
        suspend fun getData() : List<ResponseDB>

}