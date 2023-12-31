package com.swipebox.converter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swipebox.converter.data.repository.CurrencyConverterRepository

class ViewModelFactory(private val repository: CurrencyConverterRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyConverterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrencyConverterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
