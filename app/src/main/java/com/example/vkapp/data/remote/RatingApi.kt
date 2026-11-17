package com.example.vkapp.data.remote

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class RatingApi @Inject constructor() {

    suspend fun getRating(id: String): Float {
        delay(1500)

        return Random.nextInt(30, 51) / 10f
    }
}
