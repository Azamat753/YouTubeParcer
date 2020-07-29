package com.lawlett.youtubeparcer.repository

import androidx.lifecycle.MutableLiveData
import com.lawlett.youtubeparcer.Constants.apiKey
import com.lawlett.youtubeparcer.Constants.maxResult
import com.lawlett.youtubeparcer.Constants.part
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DetailPlaylistRepository(private var apiService: YoutubeApi?) {

    fun fetchYoutubePlaylistById(id: String): MutableLiveData<Playlist> {
        val data = MutableLiveData<Playlist>()
        apiService?.getSelectedPlaylist(part, apiKey, id, maxResult)?.enqueue(object :
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
}
