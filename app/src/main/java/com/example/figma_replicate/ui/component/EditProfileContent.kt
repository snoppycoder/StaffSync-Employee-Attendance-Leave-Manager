package com.example.figma_replicate.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api

import ListOfActivity
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.figma_replicate.navigation.Routes
import com.example.figma_replicate.ui.component.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun EditProfileContent(innerPadding: PaddingValues){
    var fullName: String by mutableStateOf("")
    var designation: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var EmployeeType:String by  mutableStateOf("")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),

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
        Box(){
            Text("John Doe", fontWeight = FontWeight.SemiBold)
        }
        Box(){
            Text("Lead UI/UX Developer", fontWeight = FontWeight.SemiBold)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            OutlinedTextField(
                value=fullName,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { fullName = it },
                label= { Text("Full Name") }


            )
            OutlinedTextField(
                value=email,
                shape = RoundedCornerShape(8.dp),
                onValueChange = {email = it},
                label= { Text("Email Address") }

            )
            OutlinedTextField(
                value=designation,
                onValueChange= { designation = it},
                shape = RoundedCornerShape(8.dp),
                label= { Text("Designation") }

            )
            OutlinedTextField(
                value=EmployeeType,
                shape = RoundedCornerShape(8.dp),
                onValueChange= { EmployeeType = it},
                label= { Text("Employee Type") }

            )
        }


    }

}
