package com.swipebox.converter.data.repository

import com.swipebox.converter.data.remote.ConversionResponse
import com.swipebox.converter.data.remote.CurrencyConverterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrencyConverterRepository(private val service: CurrencyConverterService) {

    fun getExchangeRates(apiKey: String, callback: (ResultState<ConversionResponse>) -> Unit) {
        service.getExchangeRates(apiKey).enqueue(object : Callback<ConversionResponse> {
            override fun onResponse(
                call: Call<ConversionResponse>, response: Response<ConversionResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(ResultState.Success(it))
                    } ?: callback(ResultState.Error(Exception("Empty response body")))
                } else {
                    callback(ResultState.Error(Exception("Failed to fetch exchange rates")))
                }
            }

            override fun onFailure(call: Call<ConversionResponse>, t: Throwable) {
                callback(ResultState.Error(t))
            }
        })
    }
}

sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class Error(val exception: Throwable) : ResultState<Nothing>()
}





