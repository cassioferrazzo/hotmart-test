package com.br.cassioferrazzo.hotmarttest.data.api.locations.model

import com.br.cassioferrazzo.hotmarttest.domain.locations.model.LocationDetail
import com.google.gson.annotations.SerializedName

data class LocationDetailResponse(
    val id: String,
    val name: String,
    val review: Float,
    val type: String,
    val about: String,
    val schedule: ScheduleResponse,
    val phone: String,
    @SerializedName("adress")
    val address: String
)