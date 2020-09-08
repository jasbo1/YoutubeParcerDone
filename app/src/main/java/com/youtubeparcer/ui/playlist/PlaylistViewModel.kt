package com.youtubeparcer.ui.playlist


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.repository.PlaylistRepository




class PlaylistViewModel (private  var repository: PlaylistRepository) : ViewModel() {
    fun fetchPlaylist(): LiveData<Playlist?> {
        return repository.fetchYoutubePlaylist()

    }


}