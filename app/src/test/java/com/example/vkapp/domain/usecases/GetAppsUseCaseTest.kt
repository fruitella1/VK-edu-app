package com.example.vkapp.domain.usecases

import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.domain.repository.AppRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetAppsUseCaseTest {
    @Test
    fun testDataReceive() {
        runTest {
            val repository = mock<AppRepository>()
            val expected = listOf<AppInfo>(
                AppInfo(
                    id = "1",
                    name = "yandex",
                    description = "a",
                    iconUrl = "frfr",
                    category = "work"
                )
            )
            whenever(repository.getApps()).thenReturn(expected)
            val useCase = GetAppsUseCase(repository)

            val actual = useCase()

            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun returnsEmptyAppsList() {
        runTest {
            val repository = mock<AppRepository>()
            val expected = emptyList<AppInfo>()
            whenever(repository.getApps()).thenReturn(expected)

            val useCase = GetAppsUseCase(repository)

            val actual = useCase()

            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun propagatesRepositoryError() {
        runTest {
            val repository = mock<AppRepository>()
            val error = IllegalStateException("Ошибка загрузки")
            whenever(repository.getApps()).thenThrow(error)

            val useCase = GetAppsUseCase(repository)
            try {
                useCase()
                Assert.fail("Await exception")
            } catch (e: IllegalStateException) {
                Assert.assertSame(error, e)
            }
        }
    }
}