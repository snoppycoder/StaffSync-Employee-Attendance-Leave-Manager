package com.example.figma_replicate.ui.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.figma_replicate.R


@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun EditProfileScreen(navController: NavController) {
    var fullName: String by remember {mutableStateOf("")}
    var designation: String by remember {mutableStateOf("")}
    var email: String by remember {mutableStateOf("")}
    var EmployeeType:String by remember {mutableStateOf("")}


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
            .verticalScroll(rememberScrollState()),

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
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 127, blue = 80)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp)


            ) {
                Text(text = "Submit", fontWeight = FontWeight.Bold)
            }


        }

    }

}

