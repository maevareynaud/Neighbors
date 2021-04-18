package com.example.neighbors.adapter

import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.neighbors.R
import com.example.neighbors.models.Neighbor

object NeighborBindingAdapter {
    @BindingAdapter("app:avatar")
    @JvmStatic
    fun bindImage(imageView: ImageView, url: String) {
        // Display Neighbour Avatar
        Glide.with(imageView.context)
            .load(url) // charger l'url
            .apply(RequestOptions.circleCropTransform()) // prend l'image qu'on a download et ajouter un effet cerlce
            .placeholder(R.drawable.ic_baseline_person_outline_24) // add placeholder
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(imageView)
    }

    @BindingAdapter("app:favorite")
    @JvmStatic
    fun likeUpdate(imageButton: ImageButton, favorite: Boolean) {
        if (favorite) {
            // on met le coeur plein
            imageButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            // on met le coeur video
            imageButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }


}
