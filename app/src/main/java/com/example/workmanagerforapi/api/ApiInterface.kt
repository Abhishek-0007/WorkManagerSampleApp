package com.example.workmanagerforapi.api

import com.example.workmanagerforapi.database.ResponseDB
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("/posts")
     suspend fun getData() : Response<List<ResponseDB>>

}
object ApiService {
    val apiInterface: ApiInterface
    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()


        apiInterface = retrofit.create(ApiInterface::class.java)
    }
}
