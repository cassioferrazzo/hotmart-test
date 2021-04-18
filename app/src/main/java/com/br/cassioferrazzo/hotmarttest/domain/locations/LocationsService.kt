package com.br.cassioferrazzo.hotmarttest.domain.locations

import com.br.cassioferrazzo.hotmarttest.data.api.locations.LocationRepository
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.ScheduleResponse
import com.br.cassioferrazzo.hotmarttest.data.api.locations.model.toLocations
import com.br.cassioferrazzo.hotmarttest.data.model.ResultWrapper
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.DayOfWeekType
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Location
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.LocationDetail
import com.br.cassioferrazzo.hotmarttest.domain.locations.model.Schedule

class LocationsService(
    private val locationRepository: LocationRepository
) : LocationsServiceUseCase {

    override suspend fun getLocations(): ResultWrapper<List<Location>> {
        return when (val locationWrap = locationRepository.getLocations()) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(
                    locationWrap.value.toLocations()
                )
            }
            is ResultWrapper.Error -> locationWrap
        }
    }

    override suspend fun getLocationDetail(locationId: Long): ResultWrapper<LocationDetail> {
        return when (val locationDetail = locationRepository.getLocationDetail(locationId)) {
            is ResultWrapper.Success -> {

                with(locationDetail.value) {
                    ResultWrapper.Success(
                        LocationDetail(
                            id = this.id,
                            name = this.name,
                            review = this.review,
                            type = this.type,
                            about = this.about,
                            schedules = this.schedule.convertScheduleResponseToSimpleSchedule(),
                            phone = this.phone,
                            address = this.address
                        )
                    )
                }
            }
            is ResultWrapper.Error -> locationDetail
        }
    }

    private fun ScheduleResponse.convertScheduleResponseToSimpleSchedule(): List<Schedule> {
        val schedules = ArrayList<Schedule>()
        this.monday?.let {
            schedules.add(
                Schedule(
                    open = it.open,
                    close = it.close,
                    DayOfWeekType.MONDAY
                )
            )
        }
        this.tuesday?.let {
            schedules.add(
                Schedule(
                    open = it.open,
                    close = it.close,
                    DayOfWeekType.TUESDAY
                )
            )
        }
        this.wednesday?.let {
            schedules.add(
                Schedule(
                    open = it.open,
                    close = it.close,
                    DayOfWeekType.WEDNESDAY
                )
            )
        }
        this.thursday?.let {
            schedules.add(
                Schedule(
                    open = it.open,
                    close = it.close,
                    DayOfWeekType.THURSDAY
                )
            )
        }
        this.friday?.let {
            schedules.add(
                Schedule(
                    open = it.open,
                    close = it.close,
                    DayOfWeekType.FRIDAY
                )
            )
        }
        this.saturday?.let {
            schedules.add(
                Schedule(
                    open = it.open,
                    close = it.close,
                    DayOfWeekType.SATURDAY
                )
            )
        }
        this.sunday?.let {
            schedules.add(
                Schedule(
                    open = it.open,
                    close = it.close,
                    DayOfWeekType.SUNDAY
                )
            )
        }
        return schedules.sortedWith(compareBy {it.dayType.id})
    }
}