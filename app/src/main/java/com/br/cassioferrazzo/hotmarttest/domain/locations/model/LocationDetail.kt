package com.br.cassioferrazzo.hotmarttest.domain.locations.model

data class LocationDetail(
    val id: String,
    val name: String,
    val review: Float,
    val type: String,
    val about: String,
    val schedules: List<Schedule>,
    val phone: String,
    val address: String
)
