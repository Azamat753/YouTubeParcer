package com.lawlett.youtubeparcer.network

import com.lawlett.youtubeparcer.model.Playlist
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("v3/playlists")
    fun fetchAllPlaylists(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("maxResult") maxResult: String
    ): retrofit2.Call<Playlist>

}