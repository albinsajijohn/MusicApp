package com.example.musicapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.musicapp.R

@Composable
fun SplashScreen(onNavigateHome: () -> Unit) {

    val alpha = remember { Animatable(0f) }

    val backgroundColor = Color(0xFF460219)
    val primaryColor = Color(0xFFF6F6FA)

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800)
        )
        delay(1200)
        onNavigateHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🎵 Music Image Logo
        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .background(primaryColor)
                .graphicsLayer(alpha = alpha.value),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.musicapp),
                contentDescription = "Music Logo",
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🎶 App Name
        Text(
            text = "Rhythmix",
            style = MaterialTheme.typography.headlineLarge,

            color = primaryColor,
            modifier = Modifier.graphicsLayer(alpha = alpha.value)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // ✨ Subtitle
        Text(
            text = "Feel the rhythm",
            style = MaterialTheme.typography.bodyMedium,
            color = primaryColor.copy(alpha = 0.6f),
            modifier = Modifier.graphicsLayer(alpha = alpha.value)
        )
    }
}
