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

const val KEY_RESULT = "result"
class ApiWorker(private val context: Context,
                params:WorkerParameters) : CoroutineWorker(context, params){

    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork: ")
        val responseDatabase = ResponseDatabase.getDatabase(context)
        val response = ApiService.apiInterface.getData()
        var data : List<ResponseDB>? = null
        response.body()?.let {
            data = it
        }

        if (data != null) {
            val output: Data = workDataOf(KEY_RESULT to Gson().toJson(data))
            responseDatabase.responseDao().addData(data!!)
            return Result.success(output)
        }
        return Result.failure()
    }
}