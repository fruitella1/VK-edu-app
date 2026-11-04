package com.example.vkapp.data.mapper

import com.example.vkapp.data.dto.AppDto
import com.example.vkapp.domain.model.AppInfo

fun AppDto.toDomain(): AppInfo {
    return AppInfo(
        id = id,
        name = name,
        description = description,
        iconUrl = iconUrl,
        category = category
    )
}