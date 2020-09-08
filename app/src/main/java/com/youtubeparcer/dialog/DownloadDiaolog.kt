package com.youtubeparcer.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.youtubeparcer.R
import com.youtubeparcer.utils.YoutubeVideo
import kotlinx.android.synthetic.main.dialog_download.*

class DownloadDialog( context: Context, private var listener: DownloadDialog.Listener,
    private var listOfFormatVideo: MutableList<YoutubeVideo>) : Dialog(context, R.style.DialogStyle), DownloadAdapter.Listener {



    val adapter = DownloadAdapter(this)
    var selectedItem: YoutubeVideo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_download)
        setupAdapter()
        downloadAction()
    }


    private fun setupAdapter() {

        alert_recycler.layoutManager = LinearLayoutManager(context)
        alert_recycler.adapter = adapter
        adapter.addItems(listOfFormatVideo)

    }



    override fun onItemClick(dto: YoutubeVideo) {
        selectedItem = dto

    }



    private fun downloadAction() {

        alert_btn.setOnClickListener {
            listener.downloadedItem(selectedItem)
            dismiss()
        }

    }



    interface Listener {

        fun downloadedItem(dto: YoutubeVideo?)

    }



}