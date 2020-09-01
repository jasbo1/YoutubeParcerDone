package com.youtubeparcer.ui.detail_playlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.youtubeparcer.R
import com.youtubeparcer.base.BaseActivity
import com.youtubeparcer.extension.showToast
import com.youtubeparcer.model.PlaylistItem
import com.youtubeparcer.ui.detail_playlist.adapter.DetailPlaylistAdapter
import kotlinx.android.synthetic.main.activity_detail_playlist.*


class DetailPlaylistActivity : BaseActivity(R.layout.activity_detail_playlist),

    DetailPlaylistAdapter.Listener {

    private lateinit var viewModel: DetailPlaylistViewModel
    private var adapter: DetailPlaylistAdapter? = DetailPlaylistAdapter(this)


    override fun setupUI() {
        viewModel = ViewModelProviders.of(this).get(DetailPlaylistViewModel::class.java)
        setupAdapter()
    }
    private fun setupAdapter() {

        detail_rview.layoutManager = LinearLayoutManager(this)
        detail_rview.adapter = adapter

    }


    override fun onItemClick(dto: PlaylistItem) {
        //instance DetailVideoActivity.instance(this,dto.snipper?.resourceId?.videoId)

    }

    override fun setupLiveData() {
        subscribeToDetailPlaylist()
    }


    private fun subscribeToDetailPlaylist() {
        id?.let {
            viewModel.fetchDetailPlaylist(it).observe(this, Observer {
                adapter?.addItems(it?.items!!)

            })
        }


    }


    companion object {
        private var id: String? = null
        fun instance(activity: Activity, id: String?) {
            val intent = Intent(activity, DetailPlaylistActivity::class.java)
            activity?.startActivity(intent)
            this.id = id
        }

    }


}
