package com.example.vkapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM app_table WHERE id =:id")
    fun observeAppById(id: String): Flow<AppEntity?>

    @Query("UPDATE app_table SET isInWishlist = :isInWishlist WHERE id = :id")
    suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApps(apps: List<AppEntity>)

    @Query("""
    UPDATE app_table
    SET isInWishlist = NOT isInWishlist
    WHERE id = :id
""")
    suspend fun toggleWishlist(id: String)
}