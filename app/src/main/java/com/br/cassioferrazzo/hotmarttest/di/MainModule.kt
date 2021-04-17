package com.br.cassioferrazzo.hotmarttest.di

import com.br.cassioferrazzo.hotmarttest.data.api.Service
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepository
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepositoryImpl
import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationsApi
import com.br.cassioferrazzo.hotmarttest.domain.locations.LocationsService
import com.br.cassioferrazzo.hotmarttest.domain.locations.LocationsServiceUseCase
import com.br.cassioferrazzo.hotmarttest.ui.locations.LocationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
    viewModel {
        LocationsViewModel(get())
    }
}