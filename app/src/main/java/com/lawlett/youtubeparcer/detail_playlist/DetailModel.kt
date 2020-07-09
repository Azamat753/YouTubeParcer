package com.lawlett.youtubeparcer.detail_playlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.network.RetrofitClient
import com.lawlett.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Response

class DetailModel : ViewModel() {

    fun fetchPlaylist(): LiveData<Playlist?> {
        return fetchYoutubePlaylist()
    }

    val channelId = "UC_IfiZu3VkesO3L58L9WPhA"
    val apiKey = "AIzaSyCGP5L-IV8ROZimTGrfUZyehgLhbU5tXCw"
    val part = "snippet,contentDetails"
    val maxResult = "50"

    private var apiService: YoutubeApi? = null

    fun fetchYoutubePlaylist(): LiveData<Playlist?> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Playlist?>()
        apiService?.getSelectedPlaylist(part, apiKey, channelId, maxResult)?.enqueue(object :
            retrofit2.Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = null
                Log.e("ololo", "onFailure: ${t.localizedMessage}")

            }

            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                data.value = response.body()
                Log.e("ololo", "onResponse: ${response.body()}")
            }

        })
        return data
    }
}

