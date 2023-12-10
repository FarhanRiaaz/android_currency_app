package com.swipebox.converter.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ProgressBar
import com.swipebox.converter.R

class LoaderDialog(private val context: Context) {

    private var alertDialog: AlertDialog? = null

    fun showLoader() {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false) // disable dismiss by tapping outside of the dialog

        val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog_layout, null)
        builder.setView(view)

        alertDialog = builder.create()
        alertDialog?.show()
    }

    fun dismissLoader() {
        alertDialog?.dismiss()
    }
}
