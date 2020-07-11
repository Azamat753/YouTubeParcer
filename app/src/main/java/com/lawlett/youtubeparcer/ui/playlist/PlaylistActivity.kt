package com.lawlett.youtubeparcer.ui.playlist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.extension.showToast
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.ui.detail_playlist.DetailPlaylistActivity
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.repository.PlaylistRepository
import com.lawlett.youtubeparcer.ui.playlist.recycler.PlayListAdapter
import kotlinx.android.synthetic.main.activity_playlist.*
import kotlinx.android.synthetic.main.internet_check.*
import okhttp3.internal.waitMillis
import java.net.NetworkInterface

class PlaylistActivity : AppCompatActivity(), PlayListAdapter.IOnClickListener {

    private var viewModel: PlaylistRepository? = null
    private var adapter = PlayListAdapter(this)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        viewModel = ViewModelProviders.of(this).get(PlaylistRepository::class.java)
        setupToSubscribe()

        playlist_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        playlist_recycler.adapter = adapter

        val connectivityManager: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected) {
            first_internet_check.visibility = GONE
            playlist_recycler.visibility = VISIBLE
            showToast(this, "connected")
        } else {
            first_internet_check.visibility = VISIBLE
            playlist_recycler.visibility = GONE
        }
        try_again.setOnClickListener {
            showToast(this,"try again")
            startActivity(Intent(this,PlaylistActivity::class.java))
        }
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                adapter.addItems(it?.items!!)
            }
        })
    }

    override fun onItemClick(dto: PlaylistItem) {
        DetailPlaylistActivity.instance(this, dto.id)
    }
}