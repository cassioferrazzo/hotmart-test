package com.br.cassioferrazzo.hotmarttest.data.model

const val NETWORK_ERROR = -1
const val UNKNOWN_ERROR = -2
const val EMPTY_BODY_ERROR = -3
const val PARSING_ERROR = -4

data class ResponseError(
    val statusCode: Int,
    val message: String = ""
)

