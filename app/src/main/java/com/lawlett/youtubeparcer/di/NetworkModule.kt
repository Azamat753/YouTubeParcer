package com.lawlett.youtubeparcer.di

import com.lawlett.youtubeparcer.network.RetrofitClient
import com.lawlett.youtubeparcer.network.YoutubeApi
import com.lawlett.youtubeparcer.network.provideLoggingInterceptor
import com.lawlett.youtubeparcer.network.provideOkHttpClient
import org.koin.dsl.module

var networkModule = module {
    single { RetrofitClient(get()) }
    single { provideOkHttpClient(get()) }
    single { provideLoggingInterceptor() }
    factory { provideYoutubeApi(get()) }
}

fun provideYoutubeApi(retrofit: RetrofitClient): YoutubeApi = retrofit.provideRetrofit().create(YoutubeApi::class.java)