package com.example.musicapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicapp.R
import com.example.musicapp.viewmodel.SharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(UnstableApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(
    onBack: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    val selectedMusic by sharedViewModel.selectedTrack.collectAsState()

    val player = remember { ExoPlayer.Builder(context).build() }
    var isPlaying by remember { mutableStateOf(false) }

    val rawRes = when (selectedMusic?.id) {
        1 -> R.raw.premavathi
        2 -> R.raw.dummyaudio
        else -> null
    }

    // Prepare media (NO autoplay)
    LaunchedEffect(rawRes) {
        if (rawRes != null) {
            val uri = RawResourceDataSource.buildRawResourceUri(rawRes)
            val mediaItem = MediaItem.fromUri(uri)
            player.setMediaItem(mediaItem)
            player.prepare()
            isPlaying = false
        }
    }

    DisposableEffect(Unit) {
        onDispose { player.release() }
    }

    val primaryColor = Color(0xFF460219)
    val backgroundColor = Color(0xFFF6F6FA)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Now Playing",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        player.pause()
                        onBack()
                    }) {
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
                contentDescription = "Album Art",
                modifier = Modifier.size(240.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🎶 Track Name
            Text(
                text = selectedMusic?.name ?: "No Music Selected",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(40.dp))

            // ▶️ PLAY / PAUSE BUTTON (FIXED)
            FilledIconButton(
                onClick = {
                    if (isPlaying) {
                        player.pause()
                    } else {
                        player.play()
                    }
                    isPlaying = !isPlaying
                },
                modifier = Modifier.size(80.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = if (isPlaying) Color.Black else primaryColor ,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = if (isPlaying)
                        Icons.Filled.Lock      // pause placeholder
                    else
                        Icons.Filled.PlayArrow,
                    contentDescription = "Play Pause",
                    modifier = Modifier.size(42.dp)
                )
            }
        }
    }
}
