package com.swipebox.converter.data.remote

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class ConversionResponse(
    val result: String,
    val documentation: String,
    val terms_of_use: String,
    val conversion_rates: Map<String, Double>
)

interface CurrencyConverterService {

    @GET("v6/{api_key}/latest/EUR")
    fun getExchangeRates(@Path("api_key") apiKey: String): Call<ConversionResponse>


}
