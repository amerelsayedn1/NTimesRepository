package com.task.nytimeaplpication.networking.helpers

import com.task.nytimeaplpication.domain.ErrorResponse
import com.task.nytimeaplpication.networking.DataState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

object Helper {

    suspend fun <T> getResult(call: suspend () -> Response<T>): DataState<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return DataState.Success(body)
            }

            val type = object : TypeToken<ErrorResponse>() {}.type
            val errorResponse: ErrorResponse =
                Gson().fromJson(response.errorBody()!!.charStream(), type)

            return error(
                message = errorResponse.message,
                code = errorResponse.code.toInt()
            )
        } catch (e: Exception) {
            return error(message = e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String, code: Int = 0): DataState<T> {
        return DataState.Error(message = message,code=code)
    }

}