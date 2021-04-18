package com.br.cassioferrazzo.hotmarttest.ui.locations.model

data class LocationDetailUiModel(
    val name: String,
    val review: Float,
    val reviewText: String,
    val about: String,
    val schedules: List<String>,
    val phone: String,
    val address: String
)