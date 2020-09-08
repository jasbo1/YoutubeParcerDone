package com.youtubeparcer.di

import com.youtubeparcer.repository.DetailPlaylistRepository
import com.youtubeparcer.repository.PlaylistRepository
import com.youtubeparcer.repository.VideoRepository
import org.koin.dsl.module

var repositoryModule = module{
    factory { DetailPlaylistRepository(get()) }
    factory { PlaylistRepository(get()) }
    factory { VideoRepository(get()) }
}