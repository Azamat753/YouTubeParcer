package com.lawlett.youtubeparcer.di

import com.lawlett.youtubeparcer.model.DetailVideoModel
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.ui.detail_playlist.DetailPlaylistViewModel
import com.lawlett.youtubeparcer.ui.playlist.PlaylistViewModel
import com.lawlett.youtubeparcer.ui.vidio_detail.DetailVideoViewModel
import org.koin.dsl.module

var viewModelModule= module {
    factory { PlaylistViewModel(get()) }
    factory { DetailPlaylistViewModel(get()) }
    factory { DetailVideoViewModel(get()) }

}