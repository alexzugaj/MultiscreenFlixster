package com.codepath.flixsterProject


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import okhttp3.Headers
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val BASE_URL = "https://api.themoviedb.org/3/movie/now_playing"
class LatestMovieFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_latest_movie_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        // Using the client, perform the HTTP request
        client[BASE_URL, params, object : JsonHttpResponseHandler()
            {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    //progressBar.hide()
                    val jsonString = json.toString()
                    val jsonData = jsonString.substring("jsonObject=".length)
                    val jsonObject = JSONObject(jsonData)
                    //val moviesArray = jsonObject.getJSONArray("results")
                    val movieRawJSON : String = jsonObject.get("results").toString()
                    val arrayMovieType = object : TypeToken<List<LatestMovie>>() {}.type
                    /*val resultsJSON : JSONObject = json.jsonObject as JSONObject
                    val moviesRawJSON : String = resultsJSON.get("results").toString()*/
                    //val arrayMovie = object : TypeToken<List<LatestMovie>>() {}.type
                    val gson = Gson()

                    val movies : MutableList<LatestMovie> = gson.fromJson(movieRawJSON, arrayMovieType)
                    recyclerView.adapter = LatestMovieRecyclerViewAdapter(movies, this@LatestMovieFragment)
                    progressBar.hide()
                    Log.d("LatestMovieFragment", "response successful")
                   /*
                    try {
                        val movies = parseTmdbResponse(json)
                        recyclerView.adapter = LatestMovieRecyclerViewAdapter(movies, this@LatestMovieFragment)
                        progressBar.hide()
                        Log.d("LatestMovieFragment", "response successful")
                    } catch (e: Exception) {
                        Log.e("MoviesFragment", "Error parsing JSON: ${e.message}")
                    } */

                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    //progressBar.hide()

                    t?.message?.let {
                        Log.e("LatestMovieFragment", errorResponse)
                    }
                }
            }]


    }

    override fun onItemClick(item: LatestMovie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }
    /*data class TmdbResponse(
        val results: List<LatestMovie>
    )

    private fun parseTmdbResponse(json: JsonHttpResponseHandler.JSON): List<LatestMovie> {
        val movieList = mutableListOf<LatestMovie>()

        try {
            val jsonString = json.toString() // Convert the JSON response to a string

            // Check if the string starts with "jsonObject="
            if (jsonString.startsWith("jsonObject=")) {
                // Remove the prefix to get the JSON data
                val jsonData = jsonString.substring("jsonObject=".length)

                // Create a JSON object from the JSON data
                val jsonObject = JSONObject(jsonData)

                // Extract the "results" array
                val resultsArray = jsonObject.getJSONArray("results")

                val gson = Gson()

                for (i in 0 until resultsArray.length()) {
                    val movieResponse = resultsArray.getJSONObject(i)
                    val title = movieResponse.getString("title")
                    val posterPath = movieResponse.getString("poster_path")
                    val overview = movieResponse.getString("overview") // Extract the overview

                    val movieInfo = LatestMovie(title = title, posterImageUrl = posterPath, overview = overview)

                    movieList.add(movieInfo)
                }

                Log.d("MoviesFragment", "Number of movies: ${movieList.size}")
            } else {
                Log.e("MoviesFragment", "Invalid JSON format: $jsonString")
            }
        } catch (e: Exception) {
            Log.e("MoviesFragment", "Error parsing JSON: ${e.message}")
            Log.e("MoviesFragment", "JSON Response: ${json.toString()}")
        }

        // Return the list of InTheatersMovie objects
        return movieList
    }*/
}