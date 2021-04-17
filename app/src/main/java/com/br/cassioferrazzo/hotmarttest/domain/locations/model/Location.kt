package com.br.cassioferrazzo.hotmarttest.domain.locations.model

import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationUiModel

data class Location(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String
)

fun Location.toUiModel() = LocationUiModel(
    id = this.id,
    name = this.name,
    review = this.review,
    type = this.type
)