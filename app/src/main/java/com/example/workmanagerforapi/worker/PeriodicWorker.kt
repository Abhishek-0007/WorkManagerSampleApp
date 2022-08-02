package com.example.workmanagerforapi.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanagerforapi.R
import com.example.workmanagerforapi.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeriodicWorker(private val context: Context,
                     params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT))
        val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_baseline_notifications_icon)
            .setAutoCancel(true)
            .setContentTitle("test")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        NotificationManagerCompat.from(context).notify(1,builder.build())


        withContext(Dispatchers.IO){
            val response = ApiService.apiInterface.getData()
            Log.d(TAG, "apiCall: ${response.code()}")
            response.body()?.let {
                return@let Result.success(workDataOf(
                    "response" to "success"
                ))
            }
            if(!response.isSuccessful){
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


}