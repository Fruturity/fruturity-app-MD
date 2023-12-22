package com.ardine.fruturity.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FruitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun Insert(fruitBookmarked: FruitBookmark)

    @Update
    fun update(fruitBookmarked: FruitBookmark)

    @Delete
    fun delete(fruitBookmarked: FruitBookmark)

    @Query("SELECT * FROM fruitBookmarked WHERE id = :id")
    fun getBookmarkedFruitsById(id: String) : LiveData<FruitBookmark?>

    @Query("SELECT * FROM fruitBookmarked")
    fun getBookmarkedFruits() : LiveData<List<FruitBookmark>>
}