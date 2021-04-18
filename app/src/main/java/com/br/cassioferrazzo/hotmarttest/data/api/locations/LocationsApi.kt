package com.br.cassioferrazzo.hotmarttest.data.api.locations

import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationDetailResponse
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationResponse
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationsApi {

    @GET("locations")
    suspend fun getLocations(): Response<LocationsResponse>

    @GET("locations/{locationId}")
    suspend fun getLocation(@Path("locationId") locationId: Long): Response<LocationDetailResponse>
}