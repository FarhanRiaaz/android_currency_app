package com.swipebox.converter.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel(), ViewModelProvider.Factory {

    protected val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    protected val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errorLiveData.postValue(throwable.message)
    }

    protected val viewModelScope = CoroutineScope(Dispatchers.Main + Job() + exceptionHandler)

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }


}