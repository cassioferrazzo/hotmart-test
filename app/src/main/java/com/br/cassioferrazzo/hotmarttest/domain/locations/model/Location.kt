package com.br.cassioferrazzo.hotmarttest.domain.locations.model

data class Location(
    val id: Long,
    val name: String,
    val review: Float,
    val type: String
)