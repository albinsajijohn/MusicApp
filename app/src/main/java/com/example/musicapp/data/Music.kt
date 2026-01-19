package com.example.musicapp.data

data class Music(
    val id: Int,
    val name: String,
    val duration: String,
    val singer: String,
    val rating: Float = 0f
)
