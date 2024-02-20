package com.codepath.flixsterProject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codepath.flixsterProject.R.id
const val ACTOR_EXTRA = "ACTOR_EXTRA"
private const val TAG = "PopularActorAdapter"

class PopularActorAdapter (private val actors: List<PopularActor>,
                           private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<PopularActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_popular_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.mActorName.text = actor.name
        val radius = 20;
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/${actor.profileImageUrl}")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .into(holder.mActorImage)
    }
    inner class ActorViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var mItem: PopularActor? = null
        val mActorName: TextView = mView.findViewById<View>(id.actor_name) as TextView
        val mActorImage: ImageView = mView.findViewById<View>(id.actor_image) as ImageView


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val actor = actors[position]

                    // Create an Intent to open PersonDetailActivity
                    val intent = Intent(mView.context, ActorDetail::class.java)

                    // Pass the person's info as an extra
                    intent.putExtra("name", actor.name)
                    intent.putExtra("actor_headshot", actor.profileImageUrl)
                    intent.putExtra("actor_dept", actor.known_for_dpt)
                    mView.context.startActivity(intent)
                }
            }
        }
    }

    }