package com.example.vkapp.data.mapper

import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.testutil.appDto
import org.junit.Assert.assertEquals
import org.junit.Test

class AppDtoMapperTest {

    @Test
    fun toDomain() {
        val dto = appDto(
            id = "app-42",
            name = "Мессенджер",
            description = "Общение с друзьями",
            iconUrl = "https://example.com/42.png",
            category = "Социальные сети"
        )

        val result = dto.toDomain()
        assertEquals(
            AppInfo(
                id = "app-42",
                name = "Мессенджер",
                description = "Общение с друзьями",
                iconUrl = "https://example.com/42.png",
                category = "Социальные сети"
            ),
            result
        )
    }
}