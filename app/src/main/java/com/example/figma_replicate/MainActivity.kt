package com.example.figma_replicate

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import java.time.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//import android.os.Bundle
//import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.figma_replicate.ui.theme.Figma_replicateTheme
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Figma_replicateTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.systemBars.asPaddingValues())
                        .padding(16.dp)
                ) {
                    HomeScreen()
                }

            }
        }
    }



    @Composable
    @RequiresApi(Build.VERSION_CODES.O)
    fun HomeScreen() {

        Column(
            modifier = Modifier
                .fillMaxSize()
            , verticalArrangement = Arrangement.spacedBy(30.dp)

        )

        {
            ProfileView()
            DaysOfTheWeek()
            Attendance()

        }


    }
    @Composable
    @RequiresApi(Build.VERSION_CODES.O)
    fun DaysOfTheWeek() {
        val curr = LocalDate.now()
        val currDay = curr.dayOfMonth
        val start = curr.with(DayOfWeek.MONDAY)
        val days = mutableListOf<String>()
        val month = mutableListOf<String>()
        for (i in 0..5) {
            val day = start.plusDays(i.toLong())
            days.add(day.format(DateTimeFormatter.ofPattern("dd")))
            month.add(day.format(DateTimeFormatter.ofPattern("MMM")))
        }
        val combinedList = days.zip(month)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)


        ) {
            items(combinedList) { (day, month) ->
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (day == currDay.toString()) Color(0xFFFF7F50) else Color.White)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                {
                    Text(
                        day,
                        fontWeight = FontWeight.Bold,
                        color = if (day == currDay.toString()) Color.White else Color.Black

                    )
                    Text(
                        month,
                        color = if (day == currDay.toString()) Color.White else Color.Black

                    )


                }
            }


        }

    }

    @Composable
    fun ProfileView() {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(40.dp)

        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape) //circle shape
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()


                )


            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "John Doe",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Lead UI/UX developer")
            }
            Column() {
                val context = LocalContext.current
                Spacer(modifier = Modifier.height(10.dp))
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification:icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            // implement the navigation to the notification
                            Toast
                                .makeText(
                                    context,
                                    "Clicked notification button",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }


                )

            }


        }
    }
    @Composable
    fun Attendance() {
        val checkIns = listOf("item1", "item2", "item3", "item4")

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid (
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()



            ) {
                items(checkIns) { check ->
                    Box(modifier =
                        Modifier.padding(15.dp)
                    )
                    {

                    }
                    Text(text=check)

                }



            }
        }
    }


}


