package com.example.workmanagerforapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "response_data")
public class DataResponse {
@PrimaryKey(autoGenerate = true)
    var sid:Int = 0

    @SerializedName("userId")
    @Expose
    var postId = 0

    @SerializedName("it")
    @Expose
    var id = 0

    @SerializedName("title")
    @Expose
    var name: String? = null

    @SerializedName("body")
    @Expose
    var email: String? = null
}