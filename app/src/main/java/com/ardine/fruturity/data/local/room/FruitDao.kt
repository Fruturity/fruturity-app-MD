//package com.ardine.fruturity.data.local.room
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Update
//import com.ardine.fruturity.data.response.FruitResponse
//import kotlinx.coroutines.flow.MutableStateFlow
//
//@Dao
//interface FruitDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(fruitBookmarked: List<FruitResponse>)
//
//    @Update
//    fun update(fruitBookmarked: FruitResponse)
//
//    @Delete
//    fun delete(fruitBookmarked: FruitResponse)
//
//    @Query("SELECT * FROM Fruit WHERE isBookmark")
//    fun getBookmarkedFruits(): List<FruitResponse>
//
//    @Query("UPDATE Fruit SET isBookmark = :newStatus WHERE id = :id")
//    suspend fun updateBookmarkStatus(id: String, newStatus: Boolean)
//}
