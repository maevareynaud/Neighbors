package com.example.neighbors.adapter

import com.example.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeibor(neighbor: Neighbor)
    fun onAddFavorite(neighbor: Neighbor)
    fun onWebsiteButton(neighbor: Neighbor)
    fun onAddNeighbor()
}