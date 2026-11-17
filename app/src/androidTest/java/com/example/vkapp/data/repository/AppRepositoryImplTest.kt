package com.example.vkapp.data.repository

import com.example.vkapp.domain.model.AppInfo
import com.example.vkapp.fakes.FakeAppApi
import com.example.vkapp.testutil.appDto
import com.example.vkapp.testutil.appInfo
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertFailsWith

class AppRepositoryImplTest {

    private lateinit var api: FakeAppApi
    private lateinit var repository: AppRepositoryImpl

    @Before
    fun setUp() {
        api = FakeAppApi()
        repository = AppRepositoryImpl(api)
    }

    @Test
    fun getAppsAppInfo () = runTest {

        api.catalog = listOf(
            appDto(id = "3", name = "C"),
            appDto(id = "1", name = "A"),
            appDto(id = "2", name = "B")
        )

        val result = repository.getApps()

        val expected: List<AppInfo> = listOf(
            appInfo(id = "3", name = "C"),
            appInfo(id = "1", name = "A"),
            appInfo(id = "2", name = "B")
        )
        assertEquals(expected, result)
    }

    @Test
    fun getApps() = runTest {

        api.catalog = emptyList()

        val result = repository.getApps()

        assertTrue(result.isEmpty())
    }

    @Test
    fun getApps_apiThrows_rethrowsSameException() = runTest {
        // Arrange
        val error = IOException("network down")
        api.catalogException = error

        // Act
        val thrown = assertFailsWith<IOException> { repository.getApps() }

        // Assert
        assertSame(error, thrown)
    }
}