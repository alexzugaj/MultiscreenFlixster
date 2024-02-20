package com.codepath.flixsterProject

import com.google.gson.annotations.SerializedName
import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Keep
@Serializable
data class BaseResults(
    @SerialName("results")
    val docs: List<PopularActor>?
)
@Keep
@Serializable
data class PopularActor (
    @SerialName("name")  val name: String?,
    @SerialName("profile_path") val profileImageUrl: String?,
    @SerialName("popularity") val popularity: String?,
    @SerialName("known_for_department") val known_for_dpt: String?,
    @SerialName("known_for") val known_for: List<Media>?
    )

@Keep
@Serializable
data class Media (
    @SerialName("title") val title: String?,
    @SerialName("name") val name: String?,
    @SerialName("poster_path") val poster_path: String?,
    @SerialName("media_type") val media_type: String?
): java.io.Serializable {
    val posterImageUrl = "https://image.tmdb.org/t/p/w500/${poster_path}"
}