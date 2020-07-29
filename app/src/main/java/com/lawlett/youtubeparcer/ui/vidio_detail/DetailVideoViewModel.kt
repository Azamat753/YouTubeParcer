package com.lawlett.youtubeparcer.ui.vidio_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.repository.VideoRepository

class DetailVideoViewModel(private val videoRepository: VideoRepository):ViewModel(){
    fun fetchVideoById(relatedToVideoId:String):LiveData<Playlist?>{
        return videoRepository.fetchVideoById(relatedToVideoId)
    }
}