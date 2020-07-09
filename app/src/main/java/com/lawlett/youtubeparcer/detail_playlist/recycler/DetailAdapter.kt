package com.lawlett.youtubeparcer.detail_playlist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.extension.loadImage
import com.lawlett.youtubeparcer.model.PlaylistItem
import kotlinx.android.synthetic.main.detail_item.view.*

class DetailAdapter(
    private val listener: Listener
) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    var list = mutableListOf<PlaylistItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(list: MutableList<PlaylistItem>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class DetailViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(item: PlaylistItem, listener: Listener) {
            itemView.detail_image.loadImage(item.snippet.thumbnails.high.url)
            itemView.detail_video_name.text=item.snippet.channelTitle
            itemView.detail_time.text=item.contentDetails.itemCount
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }
    interface Listener {
        fun onItemClick(dto: PlaylistItem)
    }
}


