package com.sample.network

import com.sample.network.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun fetchUsersList(): List<UserDto>
}