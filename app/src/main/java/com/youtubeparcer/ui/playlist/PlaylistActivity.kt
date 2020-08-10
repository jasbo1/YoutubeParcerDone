package com.youtubeparcer.ui.playlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.youtubeparcer.R
import com.youtubeparcer.model.PlaylistItem
import com.youtubeparcer.ui.playlist.adapter.PlaylistAdapter
import com.youtubeparcer.ui.detail_playlist.DetailPlaylistActivity
import kotlinx.android.synthetic.main.activity_main.*

class PlaylistActivity : AppCompatActivity(), PlaylistAdapter.Listener {

    private var adapter: PlaylistAdapter? = PlaylistAdapter(this)
    private  var viewModel: PlaylistViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        setupAdapter()
        setupToSubscribe()
    }


    private fun setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = adapter

    }
    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {

            if (!it?.items.isNullOrEmpty()) {
                adapter?.addItems(it?.items!!)

            }

        })
    }

    override fun onItemClick(dto: PlaylistItem) {
        DetailPlaylistActivity.instance(this, dto.id)

    }

}
