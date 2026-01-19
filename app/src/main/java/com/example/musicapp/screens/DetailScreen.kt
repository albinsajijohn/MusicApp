package com.example.musicapp.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.viewmodel.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(sharedViewModel: SharedViewModel, onBack: () -> Unit) {

    // FIX: selectedTrack instead of currentMusic
    val music = sharedViewModel.selectedTrack.collectAsState().value

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Now Playing") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                }
            }
        )
    }) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (music != null) {
                Text(
                    "Now playing \n \n Music : ${music.name} \n Duration : ${music.duration} \n Singer : ${music.singer}",
                    style = MaterialTheme.typography.headlineLarge
                )
            } else {
                Text(
                    "Music not found",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailScreen(sharedViewModel: SharedViewModel, onBack: () -> Unit) {
//    val music=sharedViewModel.currentMusic.value
//
//    Scaffold(topBar = {
//        TopAppBar(
//            title = { Text(text = "Now Playing") },
//            navigationIcon = {
//                IconButton(onClick = onBack) {
//                    Icon(Icons.AutoMirrored.Filled.ArrowBack,null)
//                }
//            }
//        )
//    }) {
//            padding ->
//        Box (modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
//            if(music != null){
//                Text("Detail Screen Received Id is ${music?.name} ${music?.duration} ${music?.singer}",
//                    style = MaterialTheme.typography.headlineLarge)
//            }
//            else{
//                Text("Music not found", style = MaterialTheme.typography.headlineLarge)
//            }
//        }
//    }
//}
//fun DetailScreen(sharedViewModel: SharedViewModel, onBack: () -> Unit) {
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Now Playing", fontSize = 20.sp)
//            Spacer(modifier = Modifier.height(16.dp))
//
////            Text(text = "Name: $name", fontSize = 22.sp)
////            Spacer(modifier = Modifier.height(8.dp))
////
////            Text(text = "Duration: $duration")
////            Spacer(modifier = Modifier.height(8.dp))
////
////            Text(text = "Singer: $singer")
////            Spacer(modifier = Modifier.height(32.dp))
//
//            Button(onClick = onBack) {
//                Text(text = "Back to Home")
//            }
//        }
//    }
//}
