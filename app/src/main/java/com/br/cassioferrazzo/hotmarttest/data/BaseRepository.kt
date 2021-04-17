package com.br.cassioferrazzo.hotmarttest.data

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    protected suspend fun <T> wrapResponse(apiCall: suspend () -> Response<T>): ResultWrapper<T> {
        try {
            with(apiCall.invoke()) {
                if (isSuccessful) {
                    val body = body()
                    if (body != null)
                        return ResultWrapper.Success(body)
                } else {
                    return ResultWrapper.EmptyResponseError
                }
            }
        } catch (exception: Exception) {
            return when (exception) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> ResultWrapper.Error(exception.code(), exception.message())
                else -> ResultWrapper.UnknownError
            }
        }
        return ResultWrapper.UnknownError
    }


}