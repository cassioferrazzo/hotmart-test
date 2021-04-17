import com.br.cassioferrazzo.hotmarttest.data.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location

interface LocationsServiceUseCase {
    suspend fun getLocations(): ResultWrapper<List<Location>>
}