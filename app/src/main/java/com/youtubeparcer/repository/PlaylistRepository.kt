package com.youtubeparcer.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.network.RetrofitClient
import com.youtubeparcer.network.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistRepository (){
    val channelId = "UC_IfiZu3VkesO3L58L9WPhA"
    val apiKey = "AIzaSyDZjxFOK1iJfjNJ1Purn2XDTfyRo7PTBxQ"
    val part = "snippet,contentDetails"
    val maxResult = "50"

    private var apiServise: YoutubeApi? = null
    fun fetchYoutubePlaylist(): MutableLiveData<Playlist?> {
        apiServise = RetrofitClient.create()
        val data = MutableLiveData<Playlist?>()
        apiServise?.fetchAllPlaylists(part, apiKey, channelId, maxResult)?.enqueue(object :
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
    fun fetchYoutubePlaylistById(id: String): MutableLiveData<Playlist>{
        apiServise = RetrofitClient.create()
        val data = MutableLiveData<Playlist>()
        apiServise?.getSelectedPlaylist(part, apiKey, id, maxResult)?.enqueue(object :
            Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = null
            }
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                Log.d(ContentValues.TAG, "message: ")

                data.value = response.body()

            }



        })
        return data


    }
    
}