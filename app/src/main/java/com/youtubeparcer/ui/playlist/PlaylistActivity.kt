package com.youtubeparcer.ui.playlist


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.youtubeparcer.R
import com.youtubeparcer.base.BaseActivity
import com.youtubeparcer.model.PlaylistItem
import com.youtubeparcer.ui.detail_playlist.DetailPlaylistActivity
import com.youtubeparcer.ui.detail_playlist.DetailPlaylistViewModel
import com.youtubeparcer.ui.playlist.adapter.PlaylistAdapter
import kotlinx.android.synthetic.main.activity_main.*

class PlaylistActivity : BaseActivity(R.layout.activity_main), PlaylistAdapter.Listener {


    private var adapter: PlaylistAdapter? = PlaylistAdapter(this)
    private var viewModel: PlaylistViewModel? = null


    override fun setupUI() {
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)

        setupAdapter()
    }

    private fun setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = adapter

    }


    override fun onItemClick(dto: PlaylistItem) {
        DetailPlaylistActivity.instance(this, dto.id)

    }

    override fun setupLiveData() {
        setupToSubscribe()
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (!it?.items.isNullOrEmpty()) {

                adapter?.addItems(it?.items!!)

            }

        })
    }

}