package com.example.vkapp.data

import androidx.annotation.DrawableRes

data class AppInfo(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val category: String,
)