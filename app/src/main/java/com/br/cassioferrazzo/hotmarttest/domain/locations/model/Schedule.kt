package com.br.cassioferrazzo.hotmarttest.domain.locations.model

data class Schedule(
    val open: String,
    val close: String,
    val dayType: DayOfWeekType
)

enum class DayOfWeekType(val id: Int) {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),
}