package com.example.vkapp.data.dto

import androidx.annotation.DrawableRes

data class AppDto(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val category: String,
)