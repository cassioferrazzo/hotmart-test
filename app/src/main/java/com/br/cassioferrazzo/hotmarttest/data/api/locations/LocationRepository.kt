package com.br.cassioferrazzo.hotmarttest.data.api.locations

import com.br.cassioferrazzo.hotmarttest.data.BaseRepository
import com.br.cassioferrazzo.hotmarttest.data.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationsResponse

abstract class LocationRepository: BaseRepository() {
    abstract suspend fun getLocations(): ResultWrapper<LocationsResponse>
    //todo
    //abstract suspend fun getLocation(id: Int): Location
}