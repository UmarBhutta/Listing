package com.sample.users_list.domain.usecase

import com.sample.users_list.api.usecase.UsersListUseCase
import com.sample.users_list.api.usecase.UsersListUseCaseResult
import com.sample.users_list.api.repo.UsersListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UsersListUseCaseImpl(
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
    private val usersListRepository: UsersListRepository
) : UsersListUseCase {
    override suspend fun invoke(): UsersListUseCaseResult {
        return withContext(coroutineContext) {
            try {
                val users = usersListRepository.fetchUsers()
                UsersListUseCaseResult.Success(users)
            } catch (e: Exception) {
                UsersListUseCaseResult.Error(e.cause)
            }
        }
    }
}