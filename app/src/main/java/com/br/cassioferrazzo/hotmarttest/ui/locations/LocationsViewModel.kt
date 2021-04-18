package com.br.cassioferrazzo.hotmarttest.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.domain.locations.LocationsServiceUseCase
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.toUiModel
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val locationsService: LocationsServiceUseCase
) : ViewModel() {

    private val locationsMutableLiveData = MutableLiveData<List<LocationUiModel>>()
    val locationsLiveData: LiveData<List<LocationUiModel>>
        get() = locationsMutableLiveData

    private val locationsErrorRequestLiveData = MutableLiveData<ResponseError>()
    val locationErrorLiveData: LiveData<ResponseError>
        get() = locationsErrorRequestLiveData

    fun getLocations() = viewModelScope.launch(Dispatchers.IO) {
        when (val locationWrap = locationsService.getLocations()) {
            is ResultWrapper.Success -> {
                locationsMutableLiveData.postValue(
                    locationWrap.value.map {
                        it.toUiModel()
                    }
                )
            }
            is ResultWrapper.Error ->
                locationsErrorRequestLiveData.postValue(locationWrap.responseError)
        }
    }

}