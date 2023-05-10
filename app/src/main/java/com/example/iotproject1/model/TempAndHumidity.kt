package com.example.iotproject1.model


data class TempAndHumidityResponse(val channel: Channel, val feeds: List<Feed>)

data class Channel(val id: Int)
data class Feed(
    val created_at: String,
    val entry_id: Int,
    val field1: Float,
    val field2: Float
)

data class ToiletVisit(val channel: Channel, val feeds: List<Visits>)

data class Visits(
    var created_at: String,
    val entry_id: Int,
    val field1: String,
)

fun changeToCorrectTime(oldTime: String): String {
    val str: String = oldTime.substring(11, 13)
    val int: Int = str.toInt()
    val middle: Int = int + 2
    val first: String = oldTime.substring(0, 11)
    val last: String = oldTime.substring(13)
    val stringBuilder: StringBuilder = java.lang.StringBuilder()
    stringBuilder.append(first)
    stringBuilder.append(middle)
    stringBuilder.append(last)
    return stringBuilder.toString()


}