package com.br.cassioferrazzo.hotmarttest.data.api.locations.model

data class LocationsResponse(
    val listLocations: List<LocationResponse>
)
fun LocationsResponse.toLocations() = this.listLocations.map {
    it.toLocation()
}