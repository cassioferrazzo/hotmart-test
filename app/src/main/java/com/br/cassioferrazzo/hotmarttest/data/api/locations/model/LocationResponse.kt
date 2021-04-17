package com.br.cassioferrazzo.hotmarttest.data.api.locations.model

import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location

data class LocationResponse(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
)

fun LocationResponse.toLocation() = Location(
    id = this.id,
    name = this.name,
    review = this.review,
    type = this.type
)