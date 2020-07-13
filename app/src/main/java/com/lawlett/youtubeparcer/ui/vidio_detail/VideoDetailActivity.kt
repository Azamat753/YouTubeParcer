package com.lawlett.youtubeparcer.ui.vidio_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.youtube.player.*
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.base.BaseActivity
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.repository.PlaylistRepository
import kotlinx.android.synthetic.main.activity_video_detail.*

class VideoDetailActivity : AppCompatActivity () {
    private var viewModel: PlaylistRepository? = null
    var list = mutableListOf<PlaylistItem>()

    override fun onCreate(savedInstanceState: Bundle??) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)
        setupUI()
        setupToSubscribe()


    }
//     fun setupLiveData() {
//        setupToSubscribe()
//    }

     fun setupUI() {
        viewModel = ViewModelProviders.of(this).get(PlaylistRepository::class.java)
    }



    companion object {
        var myVideoId: String? = null
        fun instanceVideoDetail(activity: Activity?, id: String?) {
            val intent = Intent(activity, VideoDetailActivity::class.java)
            activity?.startActivity(intent)
            this.myVideoId = id
        }
    }

    private fun setupToSubscribe() {
        viewModel?.fetchYoutubeVideoById()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                if (it != null) {
                    list.addAll(it.items)
                    title_video.text = list[0].snippet.title
                    video_sub_title.text = list.get(0).snippet.description

                }
            }
        })
    }
}