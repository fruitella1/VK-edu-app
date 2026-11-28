package com.example.vkapp.data.mapper

import com.example.vkapp.data.local.AppEntity
import com.example.vkapp.data.dto.AppDto
import com.example.vkapp.domain.model.AppInfo

fun AppDto.toDomain(): AppInfo {
    return AppInfo(
        id = id,
        name = name,
        description = description,
        iconUrl = iconUrl,
        category = category,
        isInWishList = false,
    )
}

fun AppInfo.toDto(): AppDto {
    return AppDto(
        id = id,
        name = name,
        description = description,
        iconUrl = iconUrl,
        category = category,
    )
}

fun AppEntity.toDomain(): AppInfo {
    return AppInfo(
        id = id,
        name = name,
        description = description,
        iconUrl = iconUrl,
        category = category,
        isInWishList = isInWishlist,
    )
}

fun AppInfo.toEntity(): AppEntity {
    return AppEntity(
        id = id,
        name = name,
        description = description,
        iconUrl = iconUrl,
        category = category,
        isInWishlist = this.isInWishList,
    )
}