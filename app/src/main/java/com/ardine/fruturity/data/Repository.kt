package com.ardine.fruturity.data

import com.ardine.fruturity.model.FruitHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository {
    private  val fruits = mutableListOf<FruitHistory>()

    fun getAllFruits() : Flow<List<FruitHistory>> {
        return flowOf(fruits)
    }

    fun searchFruits(query: String): Flow<List<FruitHistory>> {
        return getAllFruits()
            .map { result ->
                result.filter {
                    it.fruits.title.contains(query, ignoreCase = true)
                }
            }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}