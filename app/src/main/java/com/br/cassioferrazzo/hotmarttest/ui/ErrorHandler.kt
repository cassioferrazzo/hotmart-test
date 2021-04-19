package com.br.cassioferrazzo.hotmarttest.ui

import android.content.Context
import com.br.cassioferrazzo.hotmarttest.R
import com.br.cassioferrazzo.hotmarttest.data.model.NETWORK_ERROR
import com.br.cassioferrazzo.hotmarttest.data.model.PARSING_ERROR
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ErrorHandler(private val context: Context) {

    fun handleError(
            responseError: ResponseError,
            onPositiveClick: () -> Unit
    ) {
        when (responseError.statusCode) {
            in NETWORK_ERROR downTo PARSING_ERROR -> {
                displayError(
                        getMessageGeneralApiError(),
                        onPositiveClick
                )
            }
            else -> {
                displayError(
                        getMessageResponseError(responseError),
                        onPositiveClick
                )
            }
        }
    }

    fun handleError(
            responseError: ResponseError,
            onPositiveClick: () -> Unit,
            navigationClick: () -> Unit
    ) {
        when (responseError.statusCode) {
            in NETWORK_ERROR downTo PARSING_ERROR -> {
                displayError(
                        getMessageGeneralApiError(),
                        onPositiveClick,
                        navigationClick
                )
            }
            else -> {
                displayError(
                        getMessageResponseError(responseError),
                        onPositiveClick,
                        navigationClick
                )
            }
        }
    }

    private fun getMessageGeneralApiError() = context.resources.getString(R.string.api_general_error)

    private fun getMessageResponseError(responseError: ResponseError): String {
        return context.resources.getString(
                R.string.response_error,
                responseError.statusCode,
                responseError.message)
    }

    private fun displayError(message: String, onPositiveClick: () -> Unit) {
        MaterialAlertDialogBuilder(context)
                .setTitle(context.resources.getString(R.string.error))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.resources.getString(R.string.try_again)) { dialog, which ->
                    onPositiveClick.invoke()
                }
                .show()
    }

    private fun displayError(message: String, onPositiveClick: () -> Unit, onReturnClick: () -> Unit) {
        MaterialAlertDialogBuilder(context)
                .setTitle(context.resources.getString(R.string.error))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.resources.getString(R.string.try_again)) { dialog, which ->
                    onPositiveClick.invoke()
                }
                .setNeutralButton(context.resources.getString(R.string.return_string)) { dialog, which ->
                    onReturnClick.invoke()
                }
                .show()
    }
}