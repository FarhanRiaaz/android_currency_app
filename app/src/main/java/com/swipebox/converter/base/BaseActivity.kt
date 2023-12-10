package com.swipebox.converter.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.swipebox.converter.util.LoaderDialog


abstract class BaseActivity : AppCompatActivity() {

    val loaderDialog = LoaderDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        observeErrorLiveData()
    }

    abstract fun getLayoutResourceId(): Int

    private fun observeErrorLiveData() {
        getViewModel()?.errorLiveData?.observe(this, Observer { errorMessage ->
            handleError(errorMessage)
        })
        getViewModel()?.progressLiveData?.observe(this) {
            if (it) {
                loaderDialog.showLoader()

            } else {
                loaderDialog.dismissLoader()
            }
        }
    }

    abstract fun getViewModel(): BaseViewModel?

    open fun handleError(errorMessage: String?) {
    }

}
