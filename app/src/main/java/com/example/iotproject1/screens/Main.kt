package com.example.iotproject1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.iotproject1.model.Visits
import com.example.iotproject1.ui.theme.lightGreen
import com.example.iotproject1.viewmodels.OnAppViewModel
import kotlinx.coroutines.delay

@Composable
fun ScreenWithScaffold(navHostController: NavHostController, onAppViewModel: OnAppViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = lightGreen,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "ToiletMonitor",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }


            }
        },
        content = {
            it
            MainScreenComp(
                navHostController = navHostController,
                onAppViewModel = onAppViewModel
            )
        })

}

@Composable
fun MainScreenComp(navHostController: NavHostController, onAppViewModel: OnAppViewModel) {
    LaunchedEffect(Unit) {
        while (true) {
            onAppViewModel.update()
            delay(5000)

        }
    }
    val lazyColumnScrollState = rememberLazyListState()
    val appState by onAppViewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        DisplayBox(text = "Temperature is: " + appState.tempMean + " degrees")
        DisplayBox(text = "Humidity is: " + appState.humidityMean + "%")
        SmallDisplayBox(text = appState.latestUpdate)

        Text(text = "Toilet Usage: ")

        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnScrollState,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(items = appState.toiletVisits, itemContent = { item ->
                VisitDisplay(visits = item)
            })
        }


    }

}

@Composable
fun DisplayBox(text: String) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .clip(shape = RectangleShape)
            .background(color = Color.White)
            .padding(bottom = 20.dp)
            .border(width = 2.dp, color = lightGreen, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center

    ) {
        Text(text = text)

    }


}

@Composable
fun SmallDisplayBox(text: String) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(80.dp)
            .clip(shape = RectangleShape)
            .background(color = Color.White)
            .padding(bottom = 20.dp)
            .border(width = 2.dp, color = lightGreen, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Measured at: ")
            Text(text = text)

        }

    }


}

@Composable
fun VisitDisplay(visits: Visits) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .border(width = 2.dp, color = lightGreen)
            .padding(2.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = "Visit at: " + visits.created_at)

    }

}