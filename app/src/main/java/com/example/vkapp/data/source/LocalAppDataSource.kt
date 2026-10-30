package com.example.vkapp.data.source

import com.example.vkapp.R
import com.example.vkapp.data.dto.AppDto

class LocalAppDataSource {
    fun getApps():List<AppDto>{
        return listOf(
            AppDto(
                id = 0,
                name = "Telegram",
                description = "Быстрый мессенджер с чатами, каналами и облачным хранением сообщений",
                image = R.drawable.tg_icon,
                category = "Общение"
            ),
            AppDto(
                id = 1,
                name = "Ozon",
                description = "Маркетплейс: товары с доставкой и пунктами выдачи",
                image = R.drawable.ozon_icon,
                category = "Покупки"
            ),
            AppDto(
                id = 2,
                name = "Кинопоиск",
                description = "Фильмы и сериалы: онлайн-кинотеатр, рейтинги и подборки",
                image = R.drawable.kinpoisk_con,
                category = "Кино и сериалы"
            ),
            AppDto(
                id = 3,
                name = "Spotify",
                description = "Стриминг музыки и подкастов с персональными подборками",
                image = R.drawable.spoty_icon,
                category = "Музыка"
            ),
            AppDto(
                id = 4,
                name = "YouTube",
                description = "Видеохостинг: ролики, трансляции и подписки на каналы",
                image = R.drawable.youtube_icon,
                category = "Видео"
            ),
            AppDto(
                id = 5,
                name = "Яндекс Карты",
                description = "Карты, маршруты и пробки, поиск организаций рядом",
                image = R.drawable.y_maps_icon,
                category = "Навигация"
            ),
            AppDto(
                id = 6,
                name = "VK",
                description = "Социальная сеть: друзья, сообщества, музыка и видео",
                image = R.drawable.vk_icon,
                category = "Соцсети"
            ),
            AppDto(
                id = 7,
                name = "Яндекс Go",
                description = "Заказ такси, самокатов и доставки в одном приложении",
                image = R.drawable.y_go_icon,
                category = "Транспорт"
            ),
            AppDto(
                id = 8,
                name = "Duolingo",
                description = "Изучение языков в формате коротких игровых уроков",
                image = R.drawable.duolingo_icon,
                category = "Образование"
            )
        )
    }
}