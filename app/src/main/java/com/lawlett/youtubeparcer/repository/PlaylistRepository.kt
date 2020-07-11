package com.lawlett.youtubeparcer.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.network.RetrofitClient
import com.lawlett.youtubeparcer.network.YoutubeApi
import com.lawlett.youtubeparcer.ui.detail_playlist.DetailPlaylistActivity.Companion.id
import com.lawlett.youtubeparcer.ui.vidio_detail.VideoDetailActivity.Companion.myVideoId
import retrofit2.Call
import retrofit2.Response

class PlaylistRepository : ViewModel() {

    fun fetchPlaylist(): LiveData<Playlist?> {
        return fetchYoutubePlaylist()
    }
    val videoId=myVideoId
    val playlistId =id
    val channelId = "UC2il4ZKYE7gwjoVDGAbgMbQ"
    val apiKey = "AIzaSyAI7JVk-p9YhcvUFZFWXN1USfgBBq_gojk"
    val part = "snippet,contentDetails"
    val maxResult = "50"

    private var apiService: YoutubeApi? = null

    fun fetchYoutubePlaylist(): LiveData<Playlist?> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Playlist?>()
        apiService?.fetchAllPlaylists(part, apiKey, channelId, maxResult)?.enqueue(object :
            retrofit2.Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = null

            }

            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                data.value = response.body()
            }
        })
        return data
    }

    fun fetchYoutubePlaylistById(): LiveData<Playlist?> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Playlist?>()
        if (playlistId != null) {
            apiService?.getSelectedPlaylist(part, apiKey, playlistId, maxResult)?.enqueue(object :
                retrofit2.Callback<Playlist> {
                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    data.value = null

                }

                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    data.value = response.body()
                }
            })
        }
        return data
    }

    fun fetchYoutubeVideoById(): LiveData<Playlist?> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Playlist?>()
            if (videoId != null) {
                apiService?.getSelectedVideo(part, apiKey, videoId, maxResult)?.enqueue(object :
                    retrofit2.Callback<Playlist> {
                    override fun onFailure(call: Call<Playlist>, t: Throwable) {
                        data.value = null

                    }

                    override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                        data.value = response.body()
                    }
                })
            }
        return data
    }
}

