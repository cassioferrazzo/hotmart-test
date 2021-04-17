package com.br.cassioferrazzo.hotmarttest.data

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val statusCode: Int, val message: String) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
    object EmptyResponseError : ResultWrapper<Nothing>()
    object UnknownError : ResultWrapper<Nothing>()
}