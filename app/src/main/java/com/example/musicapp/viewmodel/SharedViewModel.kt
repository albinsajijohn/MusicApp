package com.example.musicapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.musicapp.data.Music
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel: ViewModel() {
    private val _tracks = MutableStateFlow(
        listOf(
            Music(1, "Song A", "3:40", "Artist A", rating = 3f),
            Music(2, "Song B", "4:10", "Artist B", rating = 4f),
            Music(3, "Song C", "2:59", "Artist C", rating = 5f)
        )
    )


    val tracks: StateFlow<List<Music>> = _tracks.asStateFlow()

    // Selected track
    private val _selectedTrack = MutableStateFlow<Music?>(null)
    val selectedTrack: StateFlow<Music?> = _selectedTrack.asStateFlow()

    fun selectTrack(track: Music) {
        _selectedTrack.value = track
    }

    // Update rating of the track inside the list
    fun updateRating(trackId: Int, rating: Float) {
        _tracks.value = _tracks.value.map { music ->
            if (music.id == trackId) music.copy(rating = rating)
            else music
        }

        // Also update selected track rating
        _selectedTrack.value?.let { selected ->
            if (selected.id == trackId) {
                _selectedTrack.value = selected.copy(rating = rating)
            }
        }
    }
}



//    private val _music = MutableStateFlow<Music?>(null)
//    val currentMusic: MutableStateFlow<Music?> = _music
//    fun updateMusic(music: Music) {
//        _music.value = music
//    }
//}