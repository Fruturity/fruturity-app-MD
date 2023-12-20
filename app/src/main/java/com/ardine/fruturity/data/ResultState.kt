package com.ardine.fruturity.data

sealed class ResultState<out T: Any?> {

    data class Success<out T: Any>(val data: T) : ResultState<T>()
    data class Error(val errorMessage: Exception) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}