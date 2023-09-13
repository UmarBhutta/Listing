package com.sample.users_list.domain.di

import com.sample.users_list.api.repo.UsersListRepository
import com.sample.users_list.api.usecase.UsersListUseCase
import com.sample.users_list.domain.repo.UsersListRepositoryImpl
import com.sample.users_list.domain.usecase.UsersListUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val usersListDomainModule = module {
    single<UsersListRepository> { UsersListRepositoryImpl(get()) }
    single<UsersListUseCase> { UsersListUseCaseImpl(coroutineContext = Dispatchers.IO, get()) }
}