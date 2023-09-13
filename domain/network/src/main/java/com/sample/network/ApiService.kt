package com.sample.network

import com.sample.network.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun fetchUsersList(): List<User>
}