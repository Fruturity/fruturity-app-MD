package com.ardine.fruturity.data

import com.ardine.fruturity.data.model.FruitHistory
import com.ardine.fruturity.data.model.FruitsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository {
    private  val fruits = mutableListOf<FruitHistory>()

    init{
        if (fruits.isEmpty()){
            FruitsDataSource.dummyFruits.forEach{
                fruits.add(FruitHistory(it, false))
            }
        }
    }

    fun getAllFruits(): Flow<List<FruitHistory>> {
        return flowOf(fruits)
    }

//    fun getGroupedFruits(): Flow<Map<String, List<FruitHistory>>> {
//        return getAllFruits()
//            .map { result ->
//                result.groupBy { it.fruits.category }
//            }
//    }

//    fun getBookmarkedFruits(): Flow<List<FruitHistory>> {
//        return flowOf(fruits.filter { it.fruits.isBookmark })
//    }

//    fun updateBookmarkStatus(fruitId: Long) {
//        val index = fruits.indexOfFirst { it.fruits.id == fruitId }
//        if (index >= 0) {
//            val fruitMarked = fruits[index]
//            fruits[index] = fruitMarked.copy(fruits = fruitMarked.fruits.copy(isBookmark = !fruitMarked.fruits.isBookmark))
//        }
//    }

    fun searchFruits(query: String): Flow<List<FruitHistory>> {
        return getAllFruits()
            .map { result ->
                result.filter {
                    it.fruits.title.contains(query, ignoreCase = true)
                }
            }
    }

    fun getFruitById(fruitId: Long): FruitHistory {
        return fruits.first {
            it.fruits.id == fruitId
        }
    }

//    BOOKMARK
    fun updateFruit(fruitId: Long): Flow<Boolean> {
        val index = fruits.indexOfFirst { it.fruits.id == fruitId }
        val result = if (index >= 0) {
            val fruitMarked = fruits[index]
            fruits[index] = fruitMarked.copy(fruits = fruitMarked.fruits)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedMarkedFruits(): Flow<List<FruitHistory>>{
        return getAllFruits()
            .map{ marked ->
                marked.filter { mark ->
                    mark.isBookmark == true
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