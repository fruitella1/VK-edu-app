package com.example.vkapp.data

import com.example.vkapp.R

fun loadApps(): List<AppInfo> {
    return listOf(
        AppInfo(
            id = 0,
            name = "Telegram",
            description = "Быстрый мессенджер с чатами, каналами и облачным хранением сообщений",
            image = R.drawable.tg_icon,
            category = "Общение"
        ),
        AppInfo(
            id = 1,
            name = "Ozon",
            description = "Маркетплейс: товары с доставкой и пунктами выдачи",
            image = R.drawable.ozon_icon,
            category = "Покупки"
        ),
        AppInfo(
            id = 2,
            name = "Кинопоиск",
            description = "Фильмы и сериалы: онлайн-кинотеатр, рейтинги и подборки",
            image = R.drawable.kinpoisk_con,
            category = "Кино и сериалы"
        ),
        AppInfo(
            id = 3,
            name = "Spotify",
            description = "Стриминг музыки и подкастов с персональными подборками",
            image = R.drawable.spoty_icon,
            category = "Музыка"
        ),
        AppInfo(
            id = 4,
            name = "YouTube",
            description = "Видеохостинг: ролики, трансляции и подписки на каналы",
            image = R.drawable.youtube_icon,
            category = "Видео"
        ),
        AppInfo(
            id = 5,
            name = "Яндекс Карты",
            description = "Карты, маршруты и пробки, поиск организаций рядом",
            image = R.drawable.y_maps_icon,
            category = "Навигация"
        ),
        AppInfo(
            id = 6,
            name = "VK",
            description = "Социальная сеть: друзья, сообщества, музыка и видео",
            image = R.drawable.vk_icon,
            category = "Соцсети"
        ),
        AppInfo(
            id = 7,
            name = "Яндекс Go",
            description = "Заказ такси, самокатов и доставки в одном приложении",
            image = R.drawable.y_go_icon,
            category = "Транспорт"
        ),
        AppInfo(
            id = 8,
            name = "Duolingo",
            description = "Изучение языков в формате коротких игровых уроков",
            image = R.drawable.duolingo_icon,
            category = "Образование"
        )
    )
}