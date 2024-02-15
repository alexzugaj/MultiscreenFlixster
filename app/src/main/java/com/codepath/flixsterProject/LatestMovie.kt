package com.codepath.flixsterProject

import com.google.gson.annotations.SerializedName

data class LatestMovie (
    @SerializedName("title")  val title: String?,
    @SerializedName("poster_path") val posterImageUrl: String?,
    @SerializedName("overview") val overview: String?
    )