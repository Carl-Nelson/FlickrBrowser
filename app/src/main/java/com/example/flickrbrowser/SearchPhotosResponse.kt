package com.example.flickrbrowser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPhotosResponse(
   @Json(name = "photos") val photos : Photos
)
@JsonClass(generateAdapter = true)
data class Photos(
    @Json(name = "photo")  val photo : List<Photo>
)
@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "id") val id: String,
    @Json(name = "secret") val secret: String
) {
    override fun toString():String{
        return "https://live.staticflickr.com/${id}/${id}_${secret}_w.jpg"
    }
}
/**
 *
 * DONE
 *
 * */