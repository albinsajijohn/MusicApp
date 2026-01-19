package com.example.musicapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    openComp: () -> Unit,     // back to components
    onNavPlayer: () -> Unit   // go to player
) {
    val selectedTrack by sharedViewModel.selectedTrack.collectAsState()
    val primaryColor = Color(0xFF460219)
    val backgroundColor = Color(0xFFF6F6FA)

    Scaffold(
        containerColor = backgroundColor,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Music Player",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = openComp) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 🎵 Album Art
            Image(
                painter = painterResource(id = R.drawable.music),
                contentDescription = "Album art",
                modifier = Modifier.size(220.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🎶 Selected Track Name
            Text(
                text = selectedTrack?.name ?: "No track selected",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ▶️ Play Button
            Button(
                onClick = {
                    if (selectedTrack != null) {
                        onNavPlayer()
                    }
                },
                enabled = selectedTrack != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                        colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF460219),
                contentColor = Color.White
            )


            ) {
                Text("Play Music", style = MaterialTheme.typography.titleLarge)
            }


        }
    }
}
