package com.lawlett.youtubeparcer.ui.detail_playlist

import DetailAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.base.BaseActivity
import com.lawlett.youtubeparcer.extension.showToast
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.repository.PlaylistRepository
import com.lawlett.youtubeparcer.ui.vidio_detail.VideoDetailActivity
import kotlinx.android.synthetic.main.activity_detail_playlist.*

class DetailPlaylistActivity : BaseActivity(R.layout.activity_detail_playlist),
    DetailAdapter.Listener {
    private var viewModel: PlaylistRepository? = null
    private var adapter = DetailAdapter(this)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


    override fun setupLiveData() {
        setupToSubscribe()
    }

    override fun setupUI() {
        viewModel = ViewModelProviders.of(this).get(PlaylistRepository::class.java)

        detail_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        detail_recycler.adapter = adapter
    }

    override fun onItemClick(dto: PlaylistItem) {
VideoDetailActivity.instanceVideoDetail(this,dto.snippet.resourceId.videoId)
        showToast(this,dto.snippet.resourceId.videoId)
    }

    private fun setupToSubscribe() {
        viewModel?.fetchYoutubePlaylistById()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                adapter.addItems(it?.items!!)
            }
        })
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
