package com.br.cassioferrazzo.hotmarttest.data.api.locations

import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationDetailResponse
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationsResponse
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper

class LocationRepositoryImpl(private val locationsApi: LocationsApi) : LocationRepository() {

    override suspend fun getLocations(): ResultWrapper<LocationsResponse> =
        wrapResponse { locationsApi.getLocations() }

    override suspend fun getLocationDetail(id: Long): ResultWrapper<LocationDetailResponse> =
        wrapResponse { locationsApi.getLocation(id) }

}