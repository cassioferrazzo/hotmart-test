package com.br.cassioferrazzo.hotmarttest.domain.locations.model

import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.ScheduleResponse

data class LocationDetail(
    val id: String,
    val name: String,
    val review: Float,
    val type: String,
    val about: String,
    val schedule: ScheduleResponse,
    val phone: String,
    val address: String
)
