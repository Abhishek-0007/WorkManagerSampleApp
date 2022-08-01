package com.example.workmanagerforapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "response_data")
class ResponseDB (
    @PrimaryKey(autoGenerate = true)
    var sid:Int = 0,
    var userId:Int = 0,
    var it :Int= 0,
    var title: String? = null,
    var body: String? = null

    )