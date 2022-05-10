package com.example.paybacktask.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.paybacktask.R
import com.example.paybacktask.domain.Hit
import com.example.paybacktask.presentation.fragments.FirstFragmentDirections
import com.squareup.picasso.Picasso

class PicturesAdapter(private val hits: List<Hit>) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return hits.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CustomViewHolder(layoutInflater.inflate(R.layout.hit_item, parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.userName.text = hits[position].user
        holder.tags.text = hits[position].tags
        holder.hit = hits[position]
        Picasso.with(holder.view.context)
            .load(hits[position].previewURL)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.smallImage)
    }
}

class CustomViewHolder(
    val view: View,
) :
    RecyclerView.ViewHolder(view) {
    val smallImage: ImageView = view.findViewById(R.id.iv_icon)
    val userName: TextView = view.findViewById(R.id.tv_user_name)
    val tags: TextView = view.findViewById(R.id.tv_tags)
    var hit: Hit? = null

    init {
        view.setOnClickListener {
            val navController = Navigation.findNavController(itemView)
            navController.navigate(FirstFragmentDirections.actionFirstFragmentToDetailInfoFragment(hit))
        }
    }
}