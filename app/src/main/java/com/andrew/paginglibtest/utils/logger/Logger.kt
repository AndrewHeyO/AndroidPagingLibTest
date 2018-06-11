package com.andrew.paginglibtest.utils.logger

/**
 * Created by Andrew on 11.06.2018.
 */

interface Logger {

    fun log(msg: String)

    fun log(tag: String, msg: String)

    fun log(error: Throwable)
}