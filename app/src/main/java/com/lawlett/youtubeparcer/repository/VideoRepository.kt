package com.lawlett.youtubeparcer.repository

import androidx.lifecycle.MutableLiveData
import com.lawlett.youtubeparcer.Constants
import com.lawlett.youtubeparcer.Constants.apiKey
import com.lawlett.youtubeparcer.Constants.part
import com.lawlett.youtubeparcer.Constants.videoId
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoRepository(private var apiService: YoutubeApi?) {

    fun fetchVideoById(videoId: String): MutableLiveData<Playlist> {
        val data = MutableLiveData<Playlist>()
        apiService?.getSelectedVideo(apiKey, part, videoId)?.enqueue(object :
            Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                data.value = response.body()
            }
        })
        return data
    }
}