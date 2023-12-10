package com.swipebox.converter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swipebox.converter.base.BaseViewModel
import com.swipebox.converter.data.remote.ConversionResponse
import com.swipebox.converter.data.repository.CurrencyConverterRepository
import com.swipebox.converter.data.repository.ResultState

class CurrencyConverterViewModel(private val repository: CurrencyConverterRepository) :
    BaseViewModel() {

    private val _exchangeRateData = MutableLiveData<ConversionResponse>()
    val exchangeRateData: LiveData<ConversionResponse> get() = _exchangeRateData

    fun fetchExchangeRates(apiKey: String) {
        _progressLiveData.postValue(true)
        _errorLiveData.postValue("true")
        repository.getExchangeRates(apiKey) { result ->

            when (result) {
                is ResultState.Success -> {
                    _exchangeRateData.value = result.data
                    _progressLiveData.postValue(false)
                }

                is ResultState.Error -> {
                    _errorLiveData.value = result.exception.message
                    _progressLiveData.postValue(false)
                }
            }
        }
    }
}
