package com.example.musicapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val starColor = Color(0xFFFFC107)
    Row(modifier = modifier) {
        val stars = 3
        for (i in 1..stars) {
            val filled = i <= rating
            IconButton(
                onClick = { onRatingChanged(i.toFloat()) },
                modifier = Modifier.size(44.dp)
            ) {
                if (filled) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "STAR $i",
                        tint = if (filled) starColor else starColor.copy(alpha = 0.4f)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "STAR $i"
                    )
                }
            }
        }
    }
}
