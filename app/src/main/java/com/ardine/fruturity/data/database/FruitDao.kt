//package com.ardine.fruturity.data.database
//
//import androidx.room.Dao
//import androidx.room.Query
//import com.ardine.fruturity.data.response.FruitResponse
//
//@Dao
//interface FruitDao {
//    @Query("SELECT * FROM fruits WHERE id = :id")
//    suspend fun getFruitById(id: String): FruitResponse
//
//    @Query("SELECT * FROM fruits")
//    suspend fun getFruits(): List<FruitResponse>
//}