package com.br.cassioferrazzo.hotmarttest.domain.locations

import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepository
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.toLocationDetail
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.toLocations
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.LocationDetail

class LocationsService(
    private val locationRepository: LocationRepository
) : LocationsServiceUseCase {

    override suspend fun getLocations(): ResultWrapper<List<Location>> {
        return when (val locationWrap = locationRepository.getLocations()) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(
                    locationWrap.value.toLocations()
                )
            }
            is ResultWrapper.Error -> locationWrap
        }
    }

    override suspend fun getLocationDetail(locationId: Long): ResultWrapper<LocationDetail> {
        return when (val locationDetail = locationRepository.getLocationDetail(locationId)) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(
                    locationDetail.value.toLocationDetail()
                )
            }
            is ResultWrapper.Error -> locationDetail
        }
    }
}