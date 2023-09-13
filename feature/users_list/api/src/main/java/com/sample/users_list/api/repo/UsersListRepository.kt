package com.sample.users_list.api.repo

import com.sample.users_list.api.User

interface UsersListRepository {
    suspend fun fetchUsers(): List<User>
}