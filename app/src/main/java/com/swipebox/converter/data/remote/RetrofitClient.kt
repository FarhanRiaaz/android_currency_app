package com.swipebox.converter.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://v6.exchangerate-api.com/"

        fun createService(): CurrencyConverterService {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(CurrencyConverterService::class.java)
        }
    }
}