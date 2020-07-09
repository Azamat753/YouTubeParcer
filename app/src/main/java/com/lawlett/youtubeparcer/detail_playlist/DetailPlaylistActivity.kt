package com.lawlett.youtubeparcer.detail_playlist

import DetailAdapter
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.extension.showToast
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.model.PlaylistItem
import kotlinx.android.synthetic.main.activity_detail_playlist.*

class DetailPlaylistActivity : AppCompatActivity(), DetailAdapter.Listener {
    private var viewModel: DetailViewlModel? = null
    private var adapter = DetailAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_playlist)

        viewModel = ViewModelProviders.of(this).get(DetailViewlModel::class.java)
        setupToSubscribe()

        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
        detail_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        detail_recycler.adapter = adapter
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                adapter.addItems(it?.items!!)
            }
        })
    }

    companion object {
        public var id: String? = null
        fun instance(activity: Activity?, id: String?) {
            val intent = Intent(activity, DetailPlaylistActivity::class.java)
            activity?.startActivity(intent)
            this.id = id
        }
    }


    override fun onItemClick(dto: PlaylistItem) {
showToast(this,dto.id)
    }


}
