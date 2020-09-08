package com.youtubeparcer.repository


import androidx.lifecycle.MutableLiveData
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.network.RetrofitClient
import com.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistRepository (private var apiService: YoutubeApi?){
    val channelId = "UC_IfiZu3VkesO3L58L9WPhA"
    val apiKey = "AIzaSyDZjxFOK1iJfjNJ1Purn2XDTfyRo7PTBxQ"
    val part = "snippet,contentDetails"
    val maxResult = "50"

    fun fetchYoutubePlaylist(): MutableLiveData<Playlist?> {
        val data = MutableLiveData<Playlist?>()
        apiService?.fetchAllPlaylists(part, apiKey, channelId, maxResult)?.enqueue(object :
           Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                // data.value = null
            }
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {


                data.value = response.body()

            }

        })
        return data

    }

    
}