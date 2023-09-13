package com.sample.users_list.domain

import com.sample.users_list.api.User
import com.sample.users_list.api.usecase.UsersListUseCase
import com.sample.users_list.api.usecase.UsersListUseCaseResult
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UsersListUseCaseImplTest {
    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `verify success` () = runBlocking {
        val mockUsers = listOf(mockk<User>())
        val useCase = object : UsersListUseCase {
            override suspend fun invoke(): UsersListUseCaseResult = UsersListUseCaseResult.Success(mockUsers)
        }

        val result = useCase()

        assertEquals(UsersListUseCaseResult.Success(mockUsers), result)
    }

    @Test
    fun `verify error`() = runBlocking {
        val errorMessage = "An error occurred"
        val throwable = Throwable(errorMessage)
        val useCase = object : UsersListUseCase {
            override suspend fun invoke(): UsersListUseCaseResult = UsersListUseCaseResult.Error(throwable)
        }

        val result = useCase()

        assertEquals(UsersListUseCaseResult.Error(throwable), result)
    }
}