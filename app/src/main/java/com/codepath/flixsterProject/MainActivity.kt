package com.codepath.flixsterProject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codepath.flixsterProject.LatestMovieFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, LatestMovieFragment(), null).commit()
    }
}