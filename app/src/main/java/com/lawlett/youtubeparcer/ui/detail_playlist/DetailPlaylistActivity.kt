package com.lawlett.youtubeparcer.ui.detail_playlist

import DetailAdapter
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.Constants.playlistId
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.base.BaseActivity
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.repository.PlaylistRepository
import com.lawlett.youtubeparcer.ui.vidio_detail.DetailVideoActivity
import kotlinx.android.synthetic.main.activity_detail_playlist.*
import kotlinx.android.synthetic.main.detail_play_list_tool_bar.*
import org.koin.android.ext.android.inject

class DetailPlaylistActivity : BaseActivity(R.layout.activity_detail_playlist),
    DetailAdapter.Listener {
    private val viewModel by inject<DetailPlaylistViewModel>()
    private var adapter = DetailAdapter(this)


    override fun setupLiveData() {
        setupToSubscribe()
    }

    override fun setupUI() {
        onBackPressedClick()
        detail_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        detail_recycler.adapter = adapter
    }

    private fun onBackPressedClick() {
        back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onItemClick(dto: PlaylistItem) {
        DetailVideoActivity.instanceVideoDetail(this, dto.snippet.resourceId.videoId)
    }

    private fun setupToSubscribe() {
        id?.let {
            viewModel?.fetchDetailPlaylist(it)?.observe(this, Observer {
                if (!it?.items.isNullOrEmpty()) {
                    adapter.addItems(it?.items!!)
                }
            })
        }
    }

    companion object {
        var id: String? = null
        fun instance(activity: Activity?, id: String?) {
            val intent = Intent(activity, DetailPlaylistActivity::class.java)
            activity?.startActivity(intent)
            this.id = id
        }
    }
}
