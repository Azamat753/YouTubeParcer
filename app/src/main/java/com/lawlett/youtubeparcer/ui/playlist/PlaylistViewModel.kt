package com.lawlett.youtubeparcer.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.repository.PlaylistRepository
import com.lawlett.youtubeparcer.repository.VideoRepository

class PlaylistViewModel(private val repository: PlaylistRepository) : ViewModel() {
    fun fetchPlaylist(): LiveData<Playlist?> {
        return repository.fetchYoutubePlaylist()
    }
}