package com.example.workmanagerforapi

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanagerforapi.api.ApiInterface
import com.example.workmanagerforapi.api.ApiService
import com.example.workmanagerforapi.database.ResponseDB
import com.example.workmanagerforapi.model.DataResponse
import com.example.workmanagerforapi.worker.ApiWorker
import com.example.workmanagerforapi.worker.RoomWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result : List<ResponseDB>? = null
        val apiWorker = OneTimeWorkRequestBuilder<ApiWorker>().setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        ).build()

        val workManager = WorkManager.getInstance(applicationContext)

        val data = Data.Builder()

        data.putString("file_path", "put_file_path_here")

        val roomWorker = OneTimeWorkRequestBuilder<RoomWorker>().build()


        workManager.beginUniqueWork("apiCall",
        ExistingWorkPolicy.KEEP,
            apiWorker).then(roomWorker).enqueue()



            val workInfos =  workManager.getWorkInfoByIdLiveData(apiWorker.id).observe(this, Observer {
                    if (it.state.isFinished){
                        Log.d(TAG, "onCreate output: ${ it.outputData.getString("result")}")
                    }
            })
        //





    }
}