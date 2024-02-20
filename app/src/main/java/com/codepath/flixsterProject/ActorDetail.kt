package com.codepath.flixsterProject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
class ActorDetail : AppCompatActivity() {
    private lateinit var actorImageView: ImageView
    private lateinit var actorNameView: TextView
    private lateinit var bylineTextView: TextView
    private lateinit var abstractTextView: TextView
    private lateinit var actorMovieView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_actor_detail)
        actorImageView = findViewById(R.id.actorImage)
        actorNameView = findViewById(R.id.ActorName)
        actorMovieView = findViewById(R.id.MovieName)


    }
}