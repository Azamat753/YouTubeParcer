package com.lawlett.youtubeparcer.di

import com.lawlett.youtubeparcer.repository.DetailPlaylistRepository
import com.lawlett.youtubeparcer.repository.PlaylistRepository
import com.lawlett.youtubeparcer.repository.VideoRepository
import org.koin.dsl.module

var repositoryModule= module {
    factory { DetailPlaylistRepository(get()) }
    factory { PlaylistRepository(get()) }
    factory { VideoRepository(get()) }
}