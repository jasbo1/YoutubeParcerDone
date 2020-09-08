package com.youtubeparcer.ui.detail_video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.repository.VideoRepository

class DetailVideoViewModel(private val repository: VideoRepository) : ViewModel(){
    fun fetchVideoById(relatedToVideoId: String): LiveData<Playlist?> {
        return repository.fetchVideoById(relatedToVideoId)
    }
}