package com.example.figma_replicate.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.figma_replicate.R

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController){


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
    Box() {
        Text("John Doe", fontWeight = FontWeight.SemiBold)
    }
    Box() {
        Text("Lead UI/UX Developer", fontWeight = FontWeight.SemiBold)
    }
        Box() {
            Button(
                onClick = { navController.navigate("edit_profile")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 127, blue = 80)
                ), shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)


            ) {
                Text(text = "Edit Profile", fontWeight = FontWeight.Bold)
            }


        }
        Column(){
            Box() {
                Button(
                    onClick = { navController.navigate("edit_profile")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(red = 255, green = 127, blue = 80)
                    ), shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp)


                ) {
                    Text(text = "Edit Profile", fontWeight = FontWeight.Bold)
                }


            }
            Box() {
                Button(
                    onClick = { navController.navigate("edit_profile")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(red = 255, green = 127, blue = 80)
                    ), shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp)


                ) {
                    Text(text = "Edit Profile", fontWeight = FontWeight.Bold)
                }


            }
            Box() {
                Button(
                    onClick = { navController.navigate("edit_profile")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(red = 255, green = 127, blue = 80)
                    ), shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp)


                ) {
                    Text(text = "Edit Profile", fontWeight = FontWeight.Bold)
                }


            }
            Box() {
                Button(
                    onClick = { navController.navigate("edit_profile")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(red = 255, green = 127, blue = 80)
                    ), shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp)


                ) {
                    Text(text = "Edit Profile", fontWeight = FontWeight.Bold)
                }


            }
        }


}
}
