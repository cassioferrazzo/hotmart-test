package com.br.cassioferrazzo.hotmarttest.ui

import android.content.Context
import com.br.cassioferrazzo.hotmarttest.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoadingHandler(private val context: Context) {
    private val loadingDialog by lazy {
        MaterialAlertDialogBuilder(context)
            .setView(R.layout.layout_loading_dialog)
            .show()
    }

    fun showLoading(){
        if(!loadingDialog.isShowing) loadingDialog.show()
    }

    fun hideLoading(){
        loadingDialog.dismiss()
    }
}