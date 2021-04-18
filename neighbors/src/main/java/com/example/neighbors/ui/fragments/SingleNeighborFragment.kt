package com.example.neighbors.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.neighbors.R
import com.example.neighbors.adapter.NeighborBindingAdapter
import com.example.neighbors.databinding.NeighbourDetailBinding
import com.example.neighbors.di.DI
import com.example.neighbors.models.Neighbor

class SingleNeighborFragment(val neighbor: Neighbor) : androidx.fragment.app.Fragment() {

    private lateinit var binding: NeighbourDetailBinding
    private var noPersistentNeighbor = neighbor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.neighbour_detail, container, false)
        binding.neighbor = noPersistentNeighbor
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.neighborFavorite.setOnClickListener {
            DI.repository.favoriteNeighbor(neighbor)
        }
    }


}
