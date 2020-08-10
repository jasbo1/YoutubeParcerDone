package com.youtubeparcer.ui.detail_playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.repository.PlaylistRepository

class DetailPlaylistViewModel : ViewModel() {
    fun fetchDetailPlaylist(id: String): MutableLiveData<Playlist> {
        return PlaylistRepository().fetchYoutubePlaylistById(id)
    }

}