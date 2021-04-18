package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.br.cassioferrazzo.hotmarttest.R
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.domain.locations.LocationsServiceUseCase
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.DayOfWeekType
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Schedule
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationDetailUiModel
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class LocationsViewModel(
    application: Application,
    private val locationsService: LocationsServiceUseCase
) : AndroidViewModel(application) {

    private val locationsMutableLiveData = MutableLiveData<List<LocationUiModel>>()
    val locationsLiveData: LiveData<List<LocationUiModel>>
        get() = locationsMutableLiveData

    private val locationsErrorRequestLiveData = MutableLiveData<ResponseError>()
    val locationErrorLiveData: LiveData<ResponseError>
        get() = locationsErrorRequestLiveData

    private val locationDetailErrorRequestLiveData = MutableLiveData<ResponseError>()
    val locationDetailErrorLiveData: LiveData<ResponseError>
        get() = locationDetailErrorRequestLiveData

    private val locationDetailMutableLiveData = MutableLiveData<LocationDetailUiModel>()
    val locationDetailLiveData: LiveData<LocationDetailUiModel>
        get() = locationDetailMutableLiveData


    fun getLocations() = viewModelScope.launch(Dispatchers.IO) {
        when (val locationWrap = locationsService.getLocations()) {
            is ResultWrapper.Success -> {
                locationsMutableLiveData.postValue(
                    locationWrap.value.map {
                        LocationUiModel(
                            id = it.id,
                            name = it.name,
                            review = it.review,
                            reviewText = DecimalFormat("#.#").format(it.review),
                            type = it.type
                        )
                    }
                )
            }
            is ResultWrapper.Error ->
                locationsErrorRequestLiveData.postValue(locationWrap.responseError)
        }
    }

    fun getLocationDetail(idLocation: Long) = viewModelScope.launch(Dispatchers.IO) {
        when (val locationDetailWrap = locationsService.getLocationDetail(idLocation)) {
            is ResultWrapper.Success -> {
                with(locationDetailWrap.value) {
                    locationDetailMutableLiveData.postValue(
                        LocationDetailUiModel(
                            name = this.name,
                            review = this.review,
                            reviewText = DecimalFormat("#.#").format(this.review),
                            about = this.about,
                            schedules = convertScheduleToListString(this.schedules),
                            phone = this.phone,
                            address = this.phone
                        )
                    )
                }
            }
            is ResultWrapper.Error ->
                locationDetailErrorRequestLiveData.postValue(locationDetailWrap.responseError)
        }
    }

    private fun convertScheduleToListString(schedules: List<Schedule>): List<String> {
        val scheduleStrings = ArrayList<String>()
        val groupedSchedule = schedules.groupBy { "${it.open} às ${it.close}" }
        groupedSchedule.forEach {
            val firstDayStr = convertDayTypeToString(it.value.first().dayType)
            val lastDayStr = convertDayTypeToString(it.value.last().dayType)
            scheduleStrings.add("$firstDayStr a $lastDayStr: ${it.key}")
        }
        return scheduleStrings
    }

    private fun convertDayTypeToString(dayType: DayOfWeekType): String {
        val resources = getApplication<Application>().resources
        return when (dayType) {
            DayOfWeekType.MONDAY -> {
                resources.getString(R.string.monday_abbreviation_string)
            }
            DayOfWeekType.TUESDAY -> {
                resources.getString(R.string.tuesday_abbreviation_string)
            }
            DayOfWeekType.WEDNESDAY -> {
                resources.getString(R.string.wednesday_abbreviation_string)
            }
            DayOfWeekType.THURSDAY -> {
                resources.getString(R.string.thursday_abbreviation_string)
            }
            DayOfWeekType.FRIDAY -> {
                resources.getString(R.string.friday_abbreviation_string)
            }
            DayOfWeekType.SATURDAY -> {
                resources.getString(R.string.saturday_abbreviation_string)
            }
            DayOfWeekType.SUNDAY -> {
                resources.getString(R.string.sunday_abbreviation_string)
            }
        }
    }
}