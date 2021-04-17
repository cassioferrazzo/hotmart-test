package com.br.cassioferrazzo.hotmarttest.domain.locations

import LocationsServiceUseCase
import com.br.cassioferrazzo.hotmarttest.data.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepository
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location

class LocationsService(
    private val locationRepository: LocationRepository
) : LocationsServiceUseCase {

    override suspend fun getLocations(): ResultWrapper<List<Location>> {
        when (val locationWrap = locationRepository.getLocations()) {
            is ResultWrapper.Success -> {
                return ResultWrapper.Success(
                    locationWrap.value.locationResponses.map {
                        Location(
                            it.id,
                            it.name,
                            it.review,
                            it.type
                        )
                    }
                )
            }
            is ResultWrapper.Error -> return locationWrap
            is ResultWrapper.EmptyResponseError -> return locationWrap
            is ResultWrapper.UnknownError -> return locationWrap
        }
        return ResultWrapper.UnknownError
    }
}