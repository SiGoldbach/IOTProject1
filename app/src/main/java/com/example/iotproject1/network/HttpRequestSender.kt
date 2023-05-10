package com.example.iotproject1.network

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun sendHttpGetRequest(urlString: String): String {
    val url = URL(urlString)
    val urlConnection = url.openConnection() as HttpURLConnection

    //Getting the bytes with HTTPS request
    urlConnection.requestMethod = "GET"
    urlConnection.readTimeout = 10000
    urlConnection.connectTimeout = 15000
    urlConnection.doInput = true
    urlConnection.connect()

    //Reading the bytes with a buffered reader
    val stream = urlConnection.inputStream
    val bufferedReader = BufferedReader(InputStreamReader(stream))
    val stringBuilder = StringBuilder()
    var line: String? = bufferedReader.readLine()
    while (line != null) {
        stringBuilder.append(line)
        line = bufferedReader.readLine()
    }
    stream.close()
    urlConnection.disconnect()

    //Here a json string is returned
    return stringBuilder.toString()
}
