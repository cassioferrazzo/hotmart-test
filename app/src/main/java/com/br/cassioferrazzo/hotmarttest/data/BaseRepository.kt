package com.br.cassioferrazzo.hotmarttest.data

import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.google.gson.JsonSyntaxException
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
                    return ResultWrapper.emptyResponseError
                }
            }
        } catch (exception: Exception) {
            return when (exception) {
                is IOException -> ResultWrapper.networkError
                is HttpException -> ResultWrapper.Error(exception.toResponseError())
                is JsonSyntaxException -> ResultWrapper.parsingError
                else -> ResultWrapper.unknownError
            }
        }
        return ResultWrapper.unknownError
    }
}

fun HttpException.toResponseError() = ResponseError(
    statusCode = this.code(),
    message = this.message()
)