package com.example.neighbors.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.neighbors.R
import com.example.neighbors.databinding.NeighbourDetailBinding
import com.example.neighbors.di.DI
import com.example.neighbors.models.Neighbor
import java.util.concurrent.Executors

class SingleNeighborFragment(val neighbor: Neighbor) : androidx.fragment.app.Fragment() {

    private lateinit var binding: NeighbourDetailBinding
    private var singleNeighbor = neighbor
    var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.neighbour_detail, container, false)
        binding.neighbor = singleNeighbor
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.neighborFavorite.setOnClickListener {
            onAddFavorite(neighbor)
        }
    }

    private fun onAddFavorite(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            DI.repository.favoriteNeighbor(neighbor)
        }

        isFavorite = !isFavorite
        changeFavoriteIcon()
    }

    private fun changeFavoriteIcon() {
        if (isFavorite) {
            binding.neighborFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.neighborFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}
