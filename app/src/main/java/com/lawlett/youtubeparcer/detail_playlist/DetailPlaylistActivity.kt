package com.lawlett.youtubeparcer.detail_playlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.detail_playlist.recycler.DetailAdapter
import com.lawlett.youtubeparcer.extension.showToast
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.ui.playlist.PlaylistViewModel
import com.lawlett.youtubeparcer.ui.playlist.recycler.PlayListAdapter
import kotlinx.android.synthetic.main.activity_detail_playlist.*
import kotlinx.android.synthetic.main.activity_playlist.*

class DetailPlaylistActivity : AppCompatActivity(), DetailAdapter.Listener {
    private var viewModel: DetailModel? = null
    private var adapter = DetailAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_playlist)
        viewModel = ViewModelProviders.of(this).get(DetailModel::class.java)
        setupToSubscribe()

        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
        detial_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        detial_recycler.adapter = adapter
    }

    companion object {
        private var id: String? = null
        fun instance(activity: Activity?, id: String?) {
            val intent = Intent(activity, DetailPlaylistActivity::class.java)
            activity?.startActivity(intent)
            this.id = id
        }
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {
                adapter.addItems(it?.items!!)
                Log.e("detail", "setupToSubscribe: ${it.items}" )
            }
        })
    }

    override fun onItemClick(dto: PlaylistItem) {
        Toast.makeText(this, dto.id, Toast.LENGTH_SHORT).show()
    }
}