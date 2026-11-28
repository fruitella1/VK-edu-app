package com.example.vkapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_table")
data class AppEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val iconUrl: String,
    val category: String,
    val isInWishlist: Boolean = false,
)