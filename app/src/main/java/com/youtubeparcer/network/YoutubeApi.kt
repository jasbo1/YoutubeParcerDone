package com.youtubeparcer.network

import com.youtubeparcer.model.Playlist
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("v3/playlists")

    fun fetchAllPlaylists(
        @Query("part",encoded=true) part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("maxResult") maxResult: String
    ): Call<Playlist>

    @GET("v3/playlistItems")

    fun getSelectedPlaylist(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("playlistId") playlist: String,
        @Query("maxResults") maxResult: String

    ): Call<Playlist>

    @GET("v3/videos")

    fun getDetailVideo(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("id") id: String
    ): Call<Playlist>
}