package com.youtubeparcer.ui.detail_playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.repository.DetailPlaylistRepository

class DetailPlaylistViewModel (private val detailPlaylistRepository: DetailPlaylistRepository): ViewModel() {
    fun fetchDetailPlaylist(id: String): MutableLiveData<Playlist> {
        return detailPlaylistRepository.fetchYoutubePlaylistById(id)
    }

}