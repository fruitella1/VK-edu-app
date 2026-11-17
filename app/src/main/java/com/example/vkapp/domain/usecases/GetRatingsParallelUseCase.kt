package com.example.vkapp.domain.usecases

import com.example.vkapp.domain.repository.AppRepository
import javax.inject.Inject

class GetRatingsParallelUseCase @Inject constructor(private val repository: AppRepository) {
    suspend operator fun invoke(ids: List<String>): Map<String, Float> {
        return repository.getRatingsParallel(ids)
    }
}