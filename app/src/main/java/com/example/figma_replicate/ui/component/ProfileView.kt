package com.example.figma_replicate.ui.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.figma_replicate.R

@Composable
fun ProfileView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        , horizontalArrangement = Arrangement.spacedBy(40.dp)

    ) {

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