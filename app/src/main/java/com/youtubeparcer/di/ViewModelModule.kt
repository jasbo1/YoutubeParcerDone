package com.youtubeparcer.di

import com.youtubeparcer.ui.detail_playlist.DetailPlaylistViewModel
import com.youtubeparcer.ui.detail_video.DetailVideoViewModel
import com.youtubeparcer.ui.playlist.PlaylistViewModel
import org.koin.dsl.module

var viewModelModule = module{
    factory { PlaylistViewModel(get()) }
    factory { DetailPlaylistViewModel(get()) }
    factory { DetailVideoViewModel(get()) }

}