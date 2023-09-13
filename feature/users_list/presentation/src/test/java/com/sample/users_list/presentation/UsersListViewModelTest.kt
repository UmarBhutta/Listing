package com.sample.users_list.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.sample.users_list.api.User
import com.sample.users_list.api.usecase.UsersListUseCase
import com.sample.users_list.api.usecase.UsersListUseCaseResult
import com.sample.users_list.presentation.ui.UsersListState
import com.sample.users_list.presentation.ui.UsersListViewModel
import com.sample.users_list.presentation.ui.data.toListUserUiModel
import com.sample.users_list.presentation.ui.data.toUserDetailsModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class UsersListViewModelTest {

    private lateinit var getUsersListUseCase: UsersListUseCase

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        getUsersListUseCase = mockk<UsersListUseCase>()
    }

    @Test
    fun `verify list is loaded`() = runTest {
        coEvery { getUsersListUseCase() } returns UsersListUseCaseResult.Success(users = emptyList())
        val viewModel = UsersListViewModel(getUsersListUseCase)

        viewModel.usersList.test {
            assertEquals(UsersListState(), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify data is loaded and mapped to UI Model`() = runTest {
        val users = listOf<User>(
            mockk(relaxed = true)
        )
        coEvery { getUsersListUseCase() } returns UsersListUseCaseResult.Success(
            users =  users
        )
        val viewModel = UsersListViewModel(getUsersListUseCase)


        viewModel.usersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(users.toListUserUiModel(), state.users)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading`() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getUsersListUseCase() } returns UsersListUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = UsersListViewModel(getUsersListUseCase)

        viewModel.usersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
            assertTrue(state.users.isEmpty())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading and retry success `() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getUsersListUseCase() } returns UsersListUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = UsersListViewModel(getUsersListUseCase)

        viewModel.usersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
            assertTrue(state.users.isEmpty())
            cancelAndConsumeRemainingEvents()
        }

        val users = listOf<User>(
            mockk(relaxed = true)
        )
        coEvery { getUsersListUseCase() } returns UsersListUseCaseResult.Success(
            users =  users
        )

        viewModel.retry()

        viewModel.usersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(users.toListUserUiModel(), state.users)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify user data is loaded for specific user id`() = runTest {
        val userId = 0
        val users = listOf<User>(
            mockk<User>(relaxed = true).copy(
                id = userId,
                username = "",
                email = "",
                address = mockk(),
                phone = "",
                website = "",
                company = mockk()
                )
        )
        coEvery { getUsersListUseCase() } returns UsersListUseCaseResult.Success(
            users =  users
        )
        val viewModel = UsersListViewModel(getUsersListUseCase)

        viewModel.fetchUserDetails(userId)

        viewModel.userDetails.test {
            val item = awaitItem()
            assertEquals(users.first().toUserDetailsModel(), item)
            cancelAndConsumeRemainingEvents()
        }

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}