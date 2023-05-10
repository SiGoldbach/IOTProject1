package com.example.iotproject1.model

import java.util.Calendar
import java.util.Date

data class PrimaryModel(
    val statisticsFromDate: Date = Calendar.getInstance().time,
    val tempMean: Float = 0.0F,
    val humidityMean: Float = 0.0F,
    val visitorAmount: Int = 0,
    val visitorTime: List<Time> = listOf(),
    val testString: String = "NULL",
    val latestUpdate: String = "",
    val toiletVisits: List<Visits> = listOf()
)

data class Time(
    val date: Date = Calendar.getInstance().time,
    val second: Int = 0,
    val minute: Int = 0,
    val hour: Int = 0,

    )