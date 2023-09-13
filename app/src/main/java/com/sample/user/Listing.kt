package com.sample.user

import android.app.Application
import com.sample.network.di.networkModule
import com.sample.users_list.domain.di.usersListDomainModule
import com.sample.users_list.presentation.di.usersListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Listing: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Listing)
            modules(listOf(networkModule, usersListDomainModule, usersListModule))
        }
    }
}