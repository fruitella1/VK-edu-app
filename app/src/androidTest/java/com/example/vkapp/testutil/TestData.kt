package com.example.vkapp.testutil

import com.example.vkapp.data.dto.AppDto
import com.example.vkapp.domain.model.AppInfo

fun appDto(
    id: String = "id-1",
    name: String = "Name 1",
    description: String = "Description 1",
    iconUrl: String = "https://example.com/icon1.png",
    category: String = "Category 1"
) = AppDto(
    id = id,
    name = name,
    description = description,
    iconUrl = iconUrl,
    category = category
)

fun appInfo(
    id: String = "id-1",
    name: String = "Name 1",
    description: String = "Description 1",
    iconUrl: String = "https://example.com/icon1.png",
    category: String = "Category 1"
) = AppInfo(
    id = id,
    name = name,
    description = description,
    iconUrl = iconUrl,
    category = category
)