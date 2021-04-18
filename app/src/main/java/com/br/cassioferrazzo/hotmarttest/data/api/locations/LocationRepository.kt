package com.br.cassioferrazzo.hotmarttest.data.api.locations

import com.br.cassioferrazzo.hotmarttest.data.BaseRepository
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationDetailResponse
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationsResponse

abstract class LocationRepository: BaseRepository() {
    abstract suspend fun getLocations(): ResultWrapper<LocationsResponse>
    abstract suspend fun getLocationDetail(id: Long): ResultWrapper<LocationDetailResponse>
}