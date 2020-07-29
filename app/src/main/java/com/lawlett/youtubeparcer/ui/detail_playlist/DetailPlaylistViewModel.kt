package com.lawlett.youtubeparcer.ui.detail_playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.repository.DetailPlaylistRepository

class DetailPlaylistViewModel(private val detailPlaylistRepository: DetailPlaylistRepository) : ViewModel() {
    fun fetchDetailPlaylist(id: String): MutableLiveData<Playlist> {
        return detailPlaylistRepository.fetchYoutubePlaylistById(id)
    }
}