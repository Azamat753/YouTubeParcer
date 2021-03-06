package com.lawlett.youtubeparcer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lawlett.youtubeparcer.Constants.apiKey
import com.lawlett.youtubeparcer.Constants.channelId
import com.lawlett.youtubeparcer.Constants.maxResult
import com.lawlett.youtubeparcer.Constants.part
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistRepository(private var apiService:YoutubeApi?): ViewModel() {


    fun fetchYoutubePlaylist(): LiveData<Playlist?> {
        val data = MutableLiveData<Playlist?>()
        apiService?.fetchAllPlaylists(part, apiKey, channelId, maxResult)?.enqueue(object :
            Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                //500.. и выше
                data.value = null
            }
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()
            }

        })
        return data
    }

}

