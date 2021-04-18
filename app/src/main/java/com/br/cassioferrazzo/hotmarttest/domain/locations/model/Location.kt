package com.br.cassioferrazzo.hotmarttest.domain.locations.model

import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationUiModel
import java.text.DecimalFormat

data class Location(
    val id: Int,
    val name: String,
    val review: Float,
    val type: String
)

fun Location.toUiModel() = LocationUiModel(
    id = this.id,
    name = this.name,
    review = this.review,
    reviewText = DecimalFormat("#.#").format(this.review),
    type = this.type
)