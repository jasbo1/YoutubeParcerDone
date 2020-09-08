package com.youtubeparcer.di

import com.youtubeparcer.network.RetrofitClient
import com.youtubeparcer.network.YoutubeApi
import com.youtubeparcer.network.provideLoggingInterceptor
import com.youtubeparcer.network.provideOkHttpClient
import org.koin.dsl.module

var networkModule = module {
    single { RetrofitClient(get()) }
    single { provideOkHttpClient(get()) }
    single { provideLoggingInterceptor() }
    factory { provideYoutubeApi(get()) }
}

//ExoPlayer

fun provideYoutubeApi(retrofit: RetrofitClient): YoutubeApi = retrofit.provideRetrofit().create(YoutubeApi::class.java)