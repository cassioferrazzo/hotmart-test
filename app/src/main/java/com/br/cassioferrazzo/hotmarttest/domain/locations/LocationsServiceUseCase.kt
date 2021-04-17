package com.br.cassioferrazzo.hotmarttest.domain.locations

import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location

interface LocationsServiceUseCase {
    suspend fun getLocations(): ResultWrapper<List<Location>>
}