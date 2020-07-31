package com.lawlett.youtubeparcer.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.dialog.adapter.DialogAdapter
import com.lawlett.youtubeparcer.utils.YoutubeVideo
import kotlinx.android.synthetic.main.custom_dialog.*

class CustomDialogClass(
    context: Context, private var listener: Listener
    , private var listOfFormatVideo: MutableList<YoutubeVideo>
) : Dialog(context, R.style.DialogStyle),
    DialogAdapter.IDialogClickListener {

    val adapter = DialogAdapter(this)
    var selectedItem: YoutubeVideo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
        setupAdapter()
        downloadAction()

    }

    private fun setupAdapter() {
        dialog_recycler.layoutManager = LinearLayoutManager(context)
        dialog_recycler.adapter = adapter
        adapter.addItems(listOfFormatVideo)
    }

    private fun downloadAction() {
        download_button.setOnClickListener {
            listener.downloadedItem(selectedItem)
            dismiss()
        }
    }

    interface Listener {
        fun downloadedItem(dto: YoutubeVideo?)
    }

    override fun onItemClick(dto: YoutubeVideo) {
        selectedItem = dto
    }
}