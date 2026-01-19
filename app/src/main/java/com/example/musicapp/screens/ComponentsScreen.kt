package com.example.musicapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicapp.ui.components.MusicCard
import com.example.musicapp.ui.components.RatingBar
import com.example.musicapp.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentSample(
    sharedViewModel: SharedViewModel,
    onBack: () -> Unit,
    onNavHome: () -> Unit
) {
    val tracks by sharedViewModel.tracks.collectAsState()

    val primaryColor = Color(0xFF460219)
    val backgroundColor = Color(0xFFF6F6FA)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Music Tracks",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)

        )
        {

            // 🎵 Section Header
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Recommended Tracks",
                    style = MaterialTheme.typography.titleMedium,
                    color = primaryColor
                )

                HorizontalDivider(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(top = 4.dp),
                    thickness = 2.dp,
                    color = primaryColor.copy(alpha = 0.6f)
                )
            }

            // 🎧 Track List
            items(tracks) { track ->
                MusicCard(
                    track = track,
                    onClick = {
                        sharedViewModel.selectTrack(track)
                        onNavHome()
                    }
                ) {
                    // ✅ SAME AS YOUR WORKING PROJECT
                    RatingBar(
                        rating = track.rating,
                        onRatingChanged = { newRating ->
                            sharedViewModel.updateRating(track.id, newRating)
                        }
                    )
                }
            }
        }
    }
}



//fun ComponentSample(
//    sharedViewModel: SharedViewModel,
//    onBack: () -> Unit,
//    onNavhome1: ()->Unit,
//
//    ) {
//    val tracks by sharedViewModel.tracks.collectAsState()
//
//    Scaffold(topBar = {
//        TopAppBar(
//            title = { Text(text = "Music tracks") },
//            navigationIcon = {
//                IconButton(onClick = onBack) {
//                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
//                }
//            }
//        )
//    }) { padding ->
//
//
//        Column(
//            modifier = Modifier
//                .padding(top =50.dp, start = 16.dp, end = 16.dp )
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Text(text = "Music Card")
//            HorizontalDivider(
//                Modifier.width(10.dp),
//                DividerDefaults.Thickness,
//                DividerDefaults.color
//            )
//
//            tracks.forEach { track ->
//
//                MusicCard(
//                    track = track,
//                    onClick = {
//                        sharedViewModel.selectTrack(track)
//                        onNavhome1()
//                    }
//                )
//                {
//                    RatingBar(
//                        rating = track.rating,
//                        modifier = Modifier.width(100.dp),
//                        onRatingChanged = { newRating ->
//                            sharedViewModel.updateRating(track.id, newRating)
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
