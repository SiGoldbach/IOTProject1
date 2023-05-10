package com.example.iotproject1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iotproject1.model.PrimaryModel
import com.example.iotproject1.model.TempAndHumidityResponse
import com.example.iotproject1.model.ToiletVisit
import com.example.iotproject1.model.changeToCorrectTime
import com.example.iotproject1.network.httpGetRequest
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnAppViewModel : ViewModel() {
    private val model = MutableStateFlow(PrimaryModel())
    val state: StateFlow<PrimaryModel> = model.asStateFlow()


    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            val response: String =
                httpGetRequest("https://api.thingspeak.com/channels/2087340/feeds.json?api_key=1Y3EW91KEJWE5GSG&results=1")
            val gson = Gson()
            val tempAndHumidityResponse =
                gson.fromJson(response, TempAndHumidityResponse::class.java)
            model.update { t ->
                t.copy(
                    testString = tempAndHumidityResponse.feeds.toString(),
                    tempMean = tempAndHumidityResponse.feeds[0].field1,
                    humidityMean = tempAndHumidityResponse.feeds[0].field2,
                    latestUpdate = changeToCorrectTime(tempAndHumidityResponse.feeds[0].created_at)
                )
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val response: String =
                httpGetRequest("https://api.thingspeak.com/channels/2114812/feeds.json?api_key=XZJGPKWX4KT1VT4H&results=20")
            val gson = Gson()
            val toiletVisitsResponse = gson.fromJson(response, ToiletVisit::class.java)
            for (i in toiletVisitsResponse.feeds) {
                i.created_at = (changeToCorrectTime(i.created_at))
            }
            model.update { t -> t.copy(toiletVisits = toiletVisitsResponse.feeds.reversed()) }
        }
    }
}