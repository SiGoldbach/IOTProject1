package com.example.iotproject1

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.iotproject1.model.changeToCorrectTime
import com.example.iotproject1.network.httpGetRequest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.iotproject1", appContext.packageName)
    }

    @Test
    fun apiTest() {
        val string: String =
            httpGetRequest("https://api.thingspeak.com/channels/2087340/feeds.json?api_key=1Y3EW91KEJWE5GSG&results=1")
        println(string)

    }

    @Test
    fun testTimeChanger() {
        val result: String = changeToCorrectTime("2023-03-29T19:42:08Z")
        println(result)
        assert(result == "2023-03-29T21:42:08Z")
    }
}