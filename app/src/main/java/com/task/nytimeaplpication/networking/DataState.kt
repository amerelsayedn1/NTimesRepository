package com.task.nytimeaplpication.networking

sealed class DataState<out R> {
    class Success<out T>(val data: T) : DataState<T>()
    class Error(val message: String,val code:String="") : DataState<Nothing>()
    object Loading : DataState<Nothing>()

}