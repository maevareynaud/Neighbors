package com.example.neighbors.repositories

import RoomNeighborDataSource
import android.app.Application
import androidx.lifecycle.LiveData
import com.example.neighbors.dal.NeighborApiService
import com.example.neighbors.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val apiService: NeighborApiService

    init {
        apiService = RoomNeighborDataSource(application)
    }

    fun getNeighbours(): LiveData<List<Neighbor>> = apiService.neighbours

    fun deleteNeighbor(neighbor: Neighbor) {
        apiService.deleteNeighbour(neighbor)
    }

    fun favoriteNeighbor(neighbor: Neighbor) {
        apiService.updateFavoriteStatus(neighbor)
    }

    fun createNeighbor(neighbor: Neighbor) {
        apiService.createNeighbour(neighbor)
    }

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
