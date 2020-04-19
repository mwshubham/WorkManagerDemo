package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class DownloadImageWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    companion object {
        const val KEY_IMAGE_URL = "image_url"
        val TAG: String = DownloadImageWorker::class.java.simpleName
    }

    override fun doWork(): Result {
        makeStatusNotification(applicationContext, "Downloading Image...")
        sleep()
        val imageUrl = inputData.getString(KEY_IMAGE_URL)
        Log.i(TAG, "imageUrl: $imageUrl")
        val output = workDataOf(KEY_IMAGE_URL to imageUrl)
        makeStatusNotification(applicationContext, "Image Downloaded Successfully.")
        return Result.success(output)
    }

}