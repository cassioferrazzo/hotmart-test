package com.br.cassioferrazzo.hotmarttest.domain.locations

import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepository
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationResponse
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.LocationsResponse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationsTest {
    private val locationResponse = LocationResponse(
        id = 1,
        name = "Sample Location",
        review = 3.9f,
        type = "UNKNOWN",
    )

    @Mock
    private lateinit var repository: LocationRepository

    @Test
    fun getLocations_Success() = runBlocking {
        Mockito.`when`(repository.getLocations()).thenReturn(getLocationsSuccess())
        val service = LocationsService(repository)
        assertTrue(service.getLocations() is ResultWrapper.Success)
    }

    @Test
    fun getLocations_Success_map_test() = runBlocking {
        Mockito.`when`(repository.getLocations()).thenReturn(getLocationsSuccess())
        val locations = LocationsService(repository).getLocations()
        assertTrue(
            when (locations) {
                is ResultWrapper.Success -> {
                    locations.value.firstOrNull()?.id == locationResponse.id
                }
                else -> false
            }
        )
    }

    @Test
    fun simulateAnErrorForCITest(){
        assertTrue(false)
    }

    private fun getLocationsSuccess(): ResultWrapper<LocationsResponse> {
        return ResultWrapper.Success(LocationsResponse(listOf(locationResponse)))
    }
}