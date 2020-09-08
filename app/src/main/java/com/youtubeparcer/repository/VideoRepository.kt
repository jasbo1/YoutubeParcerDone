package com.youtubeparcer.repository

import androidx.lifecycle.MutableLiveData
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoRepository (private var service: YoutubeApi){

    val apiKey = "AIzaSyDZjxFOK1iJfjNJ1Purn2XDTfyRo7PTBxQ"
    val part = "snippet,contentDetails"

    fun fetchVideoById(videoId: String): MutableLiveData<Playlist> {

        val data = MutableLiveData<Playlist>()
        service.getDetailVideo(apiKey, part, videoId).enqueue(object :
            Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = null

            }

            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                data.value = response.body()

            }

        })

        return data

    }

}

