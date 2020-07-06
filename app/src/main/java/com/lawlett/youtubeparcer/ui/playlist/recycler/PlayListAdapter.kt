package com.lawlett.youtubeparcer.ui.playlist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.model.Playlist
import com.squareup.picasso.Picasso

class PlayListAdapter(
    private val list: ArrayList<Playlist>,
    var listener: PlaylistViewHolder.IOnClickListener
) :
    RecyclerView.Adapter<PlayListAdapter.PlaylistViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayListAdapter.PlaylistViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.playlist_item, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(
        playlistModel: Playlist
    ) {
        list.add(playlistModel)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PlayListAdapter.PlaylistViewHolder, position: Int) {
        val model: Playlist = list[position]
        holder.bind(model)
        holder.initialize(list[position],listener)

    }

    class PlaylistViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var videoImage: ImageView = item.findViewById(R.id.playlist_image)
        var videoName: TextView = item.findViewById(R.id.video_name)
        var playlistName:TextView=item.findViewById(R.id.playlist_name)
        var videoAmount: TextView = item.findViewById(R.id.video_amount)

        fun bind(playlistModel: Playlist) {
            videoName.text = playlistModel.kind
        }
        fun initialize(list: Playlist, action: IOnClickListener) {
            itemView.setOnClickListener {
                action.onItemClick(adapterPosition)
            }
        }
        interface IOnClickListener {
            fun onItemClick(position: Int)
        }
    }
}


