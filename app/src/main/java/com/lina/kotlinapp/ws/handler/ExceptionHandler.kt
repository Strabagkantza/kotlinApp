package com.lina.kotlinapp.ws.handler

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

object ExceptionHandler {

    val defaultHandler by lazy {
        CoroutineExceptionHandler { _, exception ->
            Log.e("ERROR", "${exception.message}")
        }
    }

}