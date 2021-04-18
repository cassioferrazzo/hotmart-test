package com.br.cassioferrazzo.hotmarttest.domain.locations.model

data class Schedule(
    val sunday: Sunday?,
    val monday: Monday?,
    val tuesday: Tuesday?,
    val wednesday: Wednesday?,
    val thursday: Thursday?,
    val friday: Friday?,
    val saturday: Saturday?
)

open class DayOfWeek(
    val open: String,
    val close: String
)

class Sunday(open: String, close: String) : DayOfWeek(open, close)
class Monday(open: String, close: String) : DayOfWeek(open, close)
class Tuesday(open: String, close: String) : DayOfWeek(open, close)
class Wednesday(open: String, close: String) : DayOfWeek(open, close)
class Thursday(open: String, close: String) : DayOfWeek(open, close)
class Friday(open: String, close: String) : DayOfWeek(open, close)
class Saturday(open: String, close: String) : DayOfWeek(open, close)