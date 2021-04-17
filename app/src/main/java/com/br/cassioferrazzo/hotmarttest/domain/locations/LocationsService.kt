package com.br.cassioferrazzo.hotmarttest.domain.locations

import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepository
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.toLocations
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location

class LocationsService(
    private val locationRepository: LocationRepository
) : LocationsServiceUseCase {

    override suspend fun getLocations(): ResultWrapper<List<Location>> {
        when (val locationWrap = locationRepository.getLocations()) {
            is ResultWrapper.Success -> {
                return ResultWrapper.Success(
                    locationWrap.value.toLocations()
                )
            }
            is ResultWrapper.Error -> return locationWrap
        }
        return ResultWrapper.unknownError
    }
}