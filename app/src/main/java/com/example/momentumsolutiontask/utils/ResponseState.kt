package com.example.momentumsolutiontask.utils

sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error<T>(val message: String) : ResponseState<T>()
    object Loading : ResponseState<Nothing>()
}
