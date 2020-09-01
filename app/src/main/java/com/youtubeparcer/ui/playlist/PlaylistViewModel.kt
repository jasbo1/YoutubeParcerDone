package com.youtubeparcer.ui.playlist


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.repository.PlaylistRepository
private val repository = PlaylistRepository()



class PlaylistViewModel : ViewModel() {
    fun fetchPlaylist(): LiveData<Playlist?> {
        return repository.fetchYoutubePlaylist()

    }


}