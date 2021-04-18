package com.br.cassioferrazzo.hotmarttest.domain.locations

import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.LocationDetail

interface LocationsServiceUseCase {
    suspend fun getLocations(): ResultWrapper<List<Location>>
    suspend fun getLocationDetail(locationId: Long): ResultWrapper<LocationDetail>
}