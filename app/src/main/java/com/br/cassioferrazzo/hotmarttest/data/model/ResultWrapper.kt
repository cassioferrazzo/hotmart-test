package com.br.cassioferrazzo.hotmarttest.data.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val responseError: ResponseError) : ResultWrapper<Nothing>()
    companion object {
        val networkError = Error(
            ResponseError(statusCode = NETWORK_ERROR)
        )

        val emptyResponseError = Error(
            ResponseError(statusCode = EMPTY_BODY_ERROR)
        )

        val unknownError = Error(
            ResponseError(statusCode = UNKNOWN_ERROR)
        )
    }
}
