package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        val TAG = CompressWorker::class.java.simpleName
    }

    override fun doWork(): Result {
        // Do the work here--in this case, compress the stored images.
        // In this example no parameters are passed; the task is
        // assumed to be "compress the whole library."
        Log.d(TAG, "doWork()")

        // Indicate success or failure with your return value:
        return Result.success()
        // (Returning Result.retry() tells WorkManager to try this task again
        // later; Result.failure() says not to try again.)
    }

}