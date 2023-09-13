package com.sample.users_list.domain.repo

import com.sample.network.ApiService
import com.sample.users_list.api.User
import com.sample.users_list.api.repo.UsersListRepository
import com.sample.users_list.domain.extension.toUsers

class UsersListRepositoryImpl(val apiService: ApiService): UsersListRepository {
    override suspend fun fetchUsers(): List<User> {
        return apiService.fetchUsersList().toUsers()
    }

}