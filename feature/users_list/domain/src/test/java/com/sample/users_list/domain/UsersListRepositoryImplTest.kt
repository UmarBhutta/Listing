package com.sample.users_list.domain

import com.sample.network.ApiService
import com.sample.network.model.UserDto
import com.sample.users_list.api.repo.UsersListRepository
import com.sample.users_list.domain.repo.UsersListRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UsersListRepositoryImplTest {
    @MockK
    private lateinit var apiService: ApiService
    private lateinit var repository: UsersListRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = UsersListRepositoryImpl(apiService)
    }

    @Test
    fun `verify user list call`() = runBlocking {
        coEvery { apiService.fetchUsersList() } returns listOf(mockk<UserDto>(relaxed = true))

        repository.fetchUsers()

        coVerify { apiService.fetchUsersList() }
    }
}