package com.sample.users_list.api

interface UsersListUseCase {
    suspend operator fun invoke(): UsersListUseCaseResult
}

sealed class UsersListUseCaseResult {
    data class Success(val user: List<User>): UsersListUseCaseResult()
    data class Error(val throwable: Throwable?): UsersListUseCaseResult()
}