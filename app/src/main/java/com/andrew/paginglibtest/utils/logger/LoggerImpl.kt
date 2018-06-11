package com.andrew.paginglibtest.utils.logger

import android.util.Log
import com.andrew.paginglibtest.BuildConfig
import javax.inject.Inject

/**
 * Created by Andrew on 11.06.2018.
 */
class LoggerImpl @Inject constructor(): Logger {

    val TAG = "PAGINATION_LIB_TEST"

    override fun log(msg: String) {
        if(BuildConfig.DEBUG) {
            Log.d(TAG, msg)
        }
    }

    override fun log(tag: String, msg: String) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    override fun log(error: Throwable) {
        if(BuildConfig.DEBUG) {
            error.printStackTrace()
        }
    }
}