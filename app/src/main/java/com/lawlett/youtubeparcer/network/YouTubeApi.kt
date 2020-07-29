package com.lawlett.youtubeparcer.network

import com.lawlett.youtubeparcer.model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("v3/playlists")
    fun fetchAllPlaylists(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("maxResult") maxResult: String
    ): Call<Playlist>

    @GET("v3/playlistItems")
    fun getSelectedPlaylist(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("playlistId") playlist: String,
        @Query("maxResults") maxResult: String
    ): Call<Playlist>

    @GET("v3/videos")
    fun getSelectedVideo(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("id") videoId: String
    ):Call<Playlist>
}
