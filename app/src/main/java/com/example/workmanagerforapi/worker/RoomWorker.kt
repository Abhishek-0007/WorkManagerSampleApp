package com.example.workmanagerforapi.worker

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerforapi.database.ResponseDatabase

class RoomWorker(private val context: Context,
                 params: WorkerParameters) : CoroutineWorker(context, params){
    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork get Data: ${ApiWorker.list}")
        val list = ApiWorker.list
        val db = ResponseDatabase.getDatabase(context).responseDao()

        if(list.isEmpty()) return Result.failure()
        else{
            db.addData(list)
            return Result.success()
        }

    }
}