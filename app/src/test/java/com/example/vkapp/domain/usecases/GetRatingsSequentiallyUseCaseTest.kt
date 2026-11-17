package com.example.vkapp.domain.usecases

import com.example.vkapp.domain.repository.AppRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetRatingsSequentiallyUseCaseTest {
    @Test
    fun returnsSequentialRatings() {
        runTest {
            val repository = mock<AppRepository>()
            val ids = listOf("1", "2")
            val expected = mapOf("1" to 4.5f, "2" to 3.8f)

            whenever(repository.getRatingsSequentially(ids)).thenReturn(expected)

            val useCase = GetRatingsSequentiallyUseCase(repository)
            val actual = useCase(ids)
            verify(repository).getRatingsSequentially(ids)

            Assert.assertEquals(expected, actual)
        }
    }
}