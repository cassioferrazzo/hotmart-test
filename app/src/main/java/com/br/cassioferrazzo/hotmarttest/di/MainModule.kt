package com.br.cassioferrazzo.hotmarttest.di

import LocationsServiceUseCase
import com.br.cassioferrazzo.hotmarttest.data.api.Service
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepository
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepositoryImpl
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationsApi
import com.br.cassioferrazzo.hotmarttest.domain.locations.LocationsService
import org.koin.dsl.module

val mainModule = module {
    single {
        Service().createService(LocationsApi::class.java)
    }

    single<LocationRepository> {
        LocationRepositoryImpl(get())
    }

    single<LocationsServiceUseCase> {
        LocationsService(get())
    }
}