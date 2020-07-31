package com.lawlett.youtubeparcer.dialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.utils.YoutubeVideo


class DialogAdapter(private val listener: IDialogClickListener) : RecyclerView.Adapter<DialogAdapter.DialogViewHolder>() {
    var list = mutableListOf<YoutubeVideo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
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

    private fun deselectItems() {
        for (i in 0 until list.size) {
            if (list[i].isSelected) list[i].isSelected = false
        }
    }


    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        val item = list[position]
        holder.radio.isChecked = list[position].isSelected
        holder.radio.text = "${item.height} p"
        holder.radio.setOnClickListener {
            deselectItems()
            list[position].isSelected = true
            listener.onItemClick(list[position])
            notifyDataSetChanged()
        }
    }


    class DialogViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var radio: RadioButton = itemView.findViewById(R.id.first_q)
    }

    interface IDialogClickListener {
        fun onItemClick(dto: YoutubeVideo)
    }
}


