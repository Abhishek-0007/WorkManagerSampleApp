package com.example.workmanagerforapi.worker

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanagerforapi.api.ApiService
import com.example.workmanagerforapi.database.ResponseDB
import com.example.workmanagerforapi.database.ResponseDatabase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiWorker(private val context: Context,
                params:WorkerParameters) : CoroutineWorker(context, params){

    override suspend fun doWork(): Result {
            withContext(Dispatchers.IO){
                val response = ApiService.apiInterface.getData()
                response.body()?.let {
                   list  =  it

                    return@let Result.success(workDataOf(
                        "response" to "success"
                    ))
                }
                if(response.isSuccessful){
                    Result.failure(
                        workDataOf(
                            "error" to "not success"
                        )
                    )
                }else{
                    Result.failure(
                        workDataOf(
                            "server" to "failed"
                        )
                    )
                }
            }
        return Result.success()
    }

    companion object{
         var list  = listOf<ResponseDB>()
    }
}