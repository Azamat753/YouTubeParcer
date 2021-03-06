package com.lawlett.youtubeparcer.ui.playlist.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.extension.loadImage
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.model.Playlist
import kotlinx.android.synthetic.main.playlist_item.view.*

class PlayListAdapter(
    private val listener: IOnClickListener
) :
    RecyclerView.Adapter<PlayListAdapter.PlaylistViewHolder>() {
    var list = mutableListOf<PlaylistItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaylistViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.playlist_item, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(list: MutableList<PlaylistItem>) {
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(list[position], listener)

    }

    class PlaylistViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(item: PlaylistItem, listener: IOnClickListener) {
            itemView.playlist_image.loadImage(item.snippet.thumbnails.high.url)
            itemView.video_name.text=item.snippet.channelTitle
            itemView.video_amount.text=item.contentDetails.itemCount+" videos in playlist"
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }

    interface IOnClickListener {
        fun onItemClick(dto: PlaylistItem)
    }
}


