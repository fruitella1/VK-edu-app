package com.example.vkapp.data

import com.example.vkapp.data.dto.AppDto
import com.example.vkapp.data.mapper.toDomain
import com.example.vkapp.data.mapper.toDto
import com.example.vkapp.domain.model.AppInfo
import org.junit.Assert
import org.junit.Test

class AppMapperTest {
    @Test
    fun toDomainAllFields() {
        val dto = AppDto(
            id = "1",
            name = "yandex",
            description = "a",
            iconUrl = "frfr",
            category = "work"
        )

        val expected = AppInfo(
            id = "1",
            name = "yandex",
            description = "a",
            iconUrl = "frfr",
            category = "work"
        )

        val actual = dto.toDomain()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun emptyFieldsToDomain() {
        val dto = AppDto(
            id = "",
            name = "",
            description = "",
            iconUrl = "",
            category = ""
        )

        val expected = AppInfo(
            id = "",
            name = "",
            description = "",
            iconUrl = "",
            category = ""
        )

        val actual = dto.toDomain()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun cyrillicToDomain() {
        val dto = AppDto(
            id = "1",
            name = "такси",
            description = "приложение для вызова такси",
            iconUrl = "такси иконка",
            category = "транспорт"
        )

        val expected = AppInfo(
            id = "1",
            name = "такси",
            description = "приложение для вызова такси",
            iconUrl = "такси иконка",
            category = "транспорт"
        )

        val actual = dto.toDomain()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun whitespaceToDomain() {
        val dto = AppDto(
            id = " 1 ",
            name = " taxi ",
            description = " call taxi app ",
            iconUrl = " taxi icon ",
            category = " transport "
        )

        val expected = AppInfo(
            id = " 1 ",
            name = " taxi ",
            description = " call taxi app ",
            iconUrl = " taxi icon ",
            category = " transport "
        )

        val actual = dto.toDomain()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun toDtoAllFields() {
        val expected = AppDto(
            id = "1",
            name = "yandex",
            description = "a",
            iconUrl = "frfr",
            category = "work"
        )

        val info = AppInfo(
            id = "1",
            name = "yandex",
            description = "a",
            iconUrl = "frfr",
            category = "work"
        )

        val actual = info.toDto()
        Assert.assertEquals(expected, actual)
    }
}