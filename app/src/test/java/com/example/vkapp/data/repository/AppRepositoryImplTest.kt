package com.example.vkapp.data.repository

import com.example.vkapp.data.dto.AppDto
import com.example.vkapp.data.remote.AppApi
import com.example.vkapp.data.remote.RatingApi
import com.example.vkapp.domain.model.AppInfo
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AppRepositoryImplTest {
    @Test
    fun dtoCatalogToAppInfoCatalog() {
        runTest {
            val appApi = mock<AppApi>()
            val ratingApi = mock<RatingApi>()

            val expected = listOf(
                AppInfo(
                    id = "1",
                    name = "yandex",
                    description = "a",
                    iconUrl = "frfr",
                    category = "work"
                )
            )

            val dto = listOf(
                AppDto(
                    id = "1",
                    name = "yandex",
                    description = "a",
                    iconUrl = "frfr",
                    category = "work"
                )
            )
            whenever(appApi.getCatalog()).thenReturn(dto)

            val repository = AppRepositoryImpl(appApi, ratingApi)
            val actual = repository.getApps()
            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun returnEmptyCatalog() {
        runTest {
            val appApi = mock<AppApi>()
            val ratingApi = mock<RatingApi>()

            val expected = emptyList<AppInfo>()
            val emptyListDto = emptyList<AppDto>()
            whenever(appApi.getCatalog()).thenReturn(emptyListDto)

            val repository = AppRepositoryImpl(appApi, ratingApi)
            val actual = repository.getApps()
            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun propagatesApiError() {
        runTest {
            val appApi = mock<AppApi>()
            val ratingApi = mock<RatingApi>()

            val error = IllegalStateException("Ошибка сети")
            whenever(appApi.getCatalog()).thenThrow(error)

            val repository = AppRepositoryImpl(appApi, ratingApi)

            try {
                repository.getApps()
                Assert.fail("Await exception")
            } catch (e: IllegalStateException) {
                Assert.assertSame(error, e)
            }
        }
    }

    @Test
    fun returnsSequentialRatings() {
        runTest {
            val appApi = mock<AppApi>()
            val ratingApi = mock<RatingApi>()

            val ids = listOf("1", "2")

            val expected = mapOf("1" to 4.5f, "2" to 3.8f)

            whenever(ratingApi.getRating("1")).thenReturn(4.5f)
            whenever(ratingApi.getRating("2")).thenReturn(3.8f)

            val repository = AppRepositoryImpl(appApi, ratingApi)
            val actual = repository.getRatingsSequentially(ids)

            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun returnParallelRatings() {
        runTest {
            val appApi = mock<AppApi>()
            val ratingApi = mock<RatingApi>()

            val ids = listOf("1", "2")

            val expected = mapOf("1" to 4.5f, "2" to 3.8f)

            whenever(ratingApi.getRating("1")).thenReturn(4.5f)
            whenever(ratingApi.getRating("2")).thenReturn(3.8f)

            val repository = AppRepositoryImpl(appApi, ratingApi)
            val actual = repository.getRatingsParallel(ids)

            Assert.assertEquals(expected, actual)
        }
    }
}