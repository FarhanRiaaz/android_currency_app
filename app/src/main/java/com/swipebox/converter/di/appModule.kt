package com.swipebox.converter.di// AppModule.kt
import com.swipebox.converter.data.remote.RetrofitClient
import com.swipebox.converter.data.repository.CurrencyConverterRepository
import com.swipebox.converter.viewModel.CurrencyConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitClient.createService() }
    single { CurrencyConverterRepository(get()) }
    viewModel { CurrencyConverterViewModel(get()) }
}

