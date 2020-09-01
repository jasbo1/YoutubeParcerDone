package com.youtubeparcer.ui.detail_playlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.youtubeparcer.R
import com.youtubeparcer.extension.loadImage
import com.youtubeparcer.model.PlaylistItem
import kotlinx.android.synthetic.main.item_detail_playlist.view.*

class DetailPlaylistAdapter(private val listener: Listener) :
    RecyclerView.Adapter<DetailPlaylistAdapter.ViewHolder>() {

    var list = mutableListOf<PlaylistItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_playlist, parent, false)
        return ViewHolder(view, parent.context)
    }

    fun addItems(list: MutableList<PlaylistItem>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class ViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: PlaylistItem, listener: Listener) {


            itemView.detail_image.loadImage(context, item.snippet?.thumbnails?.high?.url)
            itemView.detail_tittle.text = item.snippet?.channelTitle
            itemView.detail_subtittle.text = item.contentDetails?.itemCount + " videos in playlist"
            itemView.setOnClickListener {
                listener.onItemClick(item)

            }
        }


    }

    interface Listener {
        fun onItemClick(dto: PlaylistItem)

    }
}
