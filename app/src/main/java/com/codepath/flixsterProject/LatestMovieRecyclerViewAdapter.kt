package com.codepath.flixsterProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.flixsterProject.R.id

class LatestMovieRecyclerViewAdapter (
    private val movies: List<LatestMovie>,
    private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<LatestMovieRecyclerViewAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: LatestMovie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.movie_poster) as ImageView
        val mMovieDesc: TextView = mView.findViewById<View>(id.movie_desc) as TextView

        override fun toString(): String {
            return mMovieTitle.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_latest_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDesc.text = movie.overview ?: "No description available"

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/${movie.posterImageUrl}")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(holder.mMovieImage)


        holder.mView.setOnClickListener {
            holder.mItem?.let { movie -> mListener?.onItemClick(movie)}
        }

    }
}