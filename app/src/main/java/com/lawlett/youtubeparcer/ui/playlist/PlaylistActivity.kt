package com.lawlett.youtubeparcer.ui.playlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.model.Playlist
import com.lawlett.youtubeparcer.ui.playlist.recycler.PlayListAdapter
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity(), PlayListAdapter.PlaylistViewHolder.IOnClickListener {

    private var viewModel: PlaylistViewModel? = null
    private var list: MutableList<Playlist> = mutableListOf()
    private var adapter = PlayListAdapter(list as ArrayList<Playlist>, this)
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
            if (it != null) {
                adapter.addItems(Playlist(it.kind))
            }
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            Log.e("ololo", "setupToSubscribe: " + it.toString())
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "$position", Toast.LENGTH_LONG).show()
        var intent=Intent(this,SecondActivity::class.java)
        intent.putExtra("id",position)
        startActivity(intent)
    }
}