package com.lawlett.youtubeparcer.ui.playlist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.extension.showToast
import com.lawlett.youtubeparcer.ui.detail_playlist.DetailPlaylistActivity
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.repository.PlaylistRepository
import com.lawlett.youtubeparcer.ui.playlist.recycler.PlayListAdapter
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity(),PlayListAdapter.IOnClickListener {

    private var viewModel: PlaylistRepository? = null
    private var adapter = PlayListAdapter( this)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        viewModel = ViewModelProviders.of(this).get(PlaylistRepository::class.java)
        setupToSubscribe()

        playlist_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        playlist_recycler.adapter = adapter

        isOnline(this)
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
     @RequiresApi(Build.VERSION_CODES.M)
     fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        showToast(this,"disconnect")
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        showToast(this,"wifi")

                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
        }
        return false
    }
}