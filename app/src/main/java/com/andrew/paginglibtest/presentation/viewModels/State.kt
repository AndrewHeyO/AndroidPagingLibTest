package com.andrew.paginglibtest.presentation.viewModels

/**
 * Created by Andrew on 23.06.2018
 */

class State(val type: Type,
            val error: Throwable? = null) {

    enum class Type {
        NONE, LOADING, ERROR
    }
}