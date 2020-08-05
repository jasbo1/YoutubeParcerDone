package com.youtubeparcer.ui.playlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.network.RetrofitClient
import com.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PlaylistViewModel : ViewModel() {
    fun fetchPlaylist(): LiveData<Playlist> {
        return fetchYoutubePlaylist()

    }

    val channelId = "UC_IfiZu3VkesO3L58L9WPhA"
    val apiKey = "AIzaSyCqZYaNdexvX-xRfYrZSNfCnNx98tYea7Q"
    val part = "snippet,contentDetails"
    val maxResult = "50"

    private var apiServise: YoutubeApi? = null
    fun fetchYoutubePlaylist(): LiveData<Playlist> {
        apiServise = RetrofitClient.create()
        val data = MutableLiveData<Playlist>()
        apiServise?.fetchAllPlaylists(part, apiKey, channelId, maxResult)?.enqueue(object :
            retrofit2.Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = null

            }

            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
               // Log.v("TAG", "message")
                data.value = response.body()
            }

        })
        return data

    }
}