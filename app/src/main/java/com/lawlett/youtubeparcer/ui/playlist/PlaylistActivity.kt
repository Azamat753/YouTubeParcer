package com.lawlett.youtubeparcer.ui.playlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.detail_playlist.DetailPlaylistActivity
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.ui.playlist.recycler.PlayListAdapter
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity(),PlayListAdapter.IOnClickListener {

    private var viewModel: PlaylistViewModel? = null
    private var adapter = PlayListAdapter( this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        setupToSubscribe()

        playlist_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        playlist_recycler.adapter = adapter
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                adapter.addItems(it?.items!!)
            }
        })
    }

    override fun onItemClick(dto: PlaylistItem) {
        DetailPlaylistActivity.instance(this,dto.id)
    }
}