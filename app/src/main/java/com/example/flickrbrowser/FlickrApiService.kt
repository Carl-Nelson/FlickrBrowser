package com.example.flickrbrowser

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
private const val API_KEY = "2a1f1f284d58296918bc9a2a9ca834f8"
private const val BASE_URL = "https://api.flickr.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()) //For Moshi's annotations to work properly with Kotlin
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object FlickrApi{
    val retrofitService : FlickrApiService by lazy {
        retrofit.create(FlickrApiService::class.java)
    }
}

interface FlickrApiService {
    @GET("services/rest/")
    suspend fun getPhotos(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("method") method: String = "flickr.photos.search",
        @Query("tags") tags: String = "",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): SearchPhotosResponse

    fun setTags(tag:String)
}