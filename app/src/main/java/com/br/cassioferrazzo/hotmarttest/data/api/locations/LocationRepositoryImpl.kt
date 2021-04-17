package com.br.cassioferrazzo.hotmarttest.data.api.locations

import com.br.cassioferrazzo.hotmarttest.data.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationsResponse

class LocationRepositoryImpl(private val locationsApi: LocationsApi) : LocationRepository() {

    override suspend fun getLocations(): ResultWrapper<LocationsResponse> =
        wrapResponse { locationsApi.getLocations() }

//    override suspend fun getLocation(id: Int): Location {
//        when(val wrappedResponse = wrapResponse { locationsApi.getLocation(id) }){
//            is ResultWrapper.Success -> return wrappedResponse.value
//            is ResultWrapper.Error -> throw ApiException(wrappedResponse.statusCode)
//        }
//    }
}