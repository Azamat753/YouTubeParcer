package com.lawlett.youtubeparcer.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.youtubeparcer.R
import kotlinx.android.synthetic.main.dialog_item.view.*


class DialogAdapter(
    private val listener: IDialogClickListener
) :
    RecyclerView.Adapter<DialogAdapter.DialogViewHolder>() {
    var list = mutableListOf<YoutubeVideo>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DialogViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.dialog_item, parent, false)
        return DialogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(list: MutableList<YoutubeVideo>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }
    class DialogViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(item: YoutubeVideo, listener: IDialogClickListener) {
            itemView.first_q.text = item.height.toString()
            itemView.first_q.setOnClickListener { listener }
            Log.e("quality", "bind:${item.videoFile} ")
            Log.e("quality", "bind:${item.audioFile} " )
            Log.e("quality", "bind:${item.height} " )
        }
    }

    interface IDialogClickListener {
        fun onItemClick(dto: YoutubeVideo)
    }
}


