package com.sample.users_list.domain.usecase

import com.sample.users_list.api.UsersListUseCase
import com.sample.users_list.api.UsersListUseCaseResult
import com.sample.users_list.domain.repo.UsersListRepository
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class UsersListUseCaseImpl(
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
    private val usersListRepository: UsersListRepository
) : UsersListUseCase {
    override suspend fun invoke(): UsersListUseCaseResult {
        TODO("Not yet implemented")
    }
}