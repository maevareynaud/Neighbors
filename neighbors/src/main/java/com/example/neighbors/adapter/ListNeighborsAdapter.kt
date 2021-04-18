package com.example.neighbors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.R
import com.example.neighbors.databinding.NeighborItemBinding
import com.example.neighbors.models.Neighbor

class ListNeighborsAdapter(
    items: List<Neighbor>,
    listNeighborHandler: ListNeighborHandler
) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {
    private val mNeighbours: List<Neighbor> = items
    private val mNeighboursHandler: ListNeighborHandler = listNeighborHandler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: NeighborItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.neighbor_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]

        holder.binding.neighbor = neighbour

        // Intercepter le click sur le favoris
        holder.binding.itemListLikeButton.setOnClickListener {
            mNeighboursHandler.onAddFavorite(neighbour)
        }

        // Intercepter le click sur le delete
        holder.binding.itemListDeleteButton.setOnClickListener {
            mNeighboursHandler.onDeleteNeibor(neighbour)
        }

        // Intercepter le click du button website
        holder.binding.itemListWebsiteButton.setOnClickListener {
            mNeighboursHandler.onWebsiteButton(neighbour)
        }

        // Intercepter le click sur le voisin
        holder.binding.itemListAvatar.setOnClickListener {
            mNeighboursHandler.onSingleNeighbor(neighbour)
        }
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(val binding: NeighborItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
