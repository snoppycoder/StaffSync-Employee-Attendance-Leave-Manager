package com.example.figma_replicate.ui.component

import com.example.figma_replicate.R

import androidx.compose.ui.Alignment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items

import androidx.compose.ui.*
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon

import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.ButtonDefaults

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable

fun ListOfActivity() {
    val list = listOf("Item1", "Item1", "Item1", "Item1", "Item1")
    Text(text = "Today's Attendance", fontWeight = FontWeight.SemiBold)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // <- Add general padding here
        , verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list) { activity ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_checkin_),
                    contentDescription = "check In"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Check In", fontWeight = FontWeight.Bold)
                    Text("Apr 16 2025", fontWeight = FontWeight.Light, fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "10:00AM",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

        }
        item {


            Button(
                onClick = { },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 127, blue = 80)
                ), modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()

                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier.padding(vertical=16.dp)
                )
                Text(text = "Check in", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }


    }


}

