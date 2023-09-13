package com.sample.users_list.presentation.di

import com.sample.users_list.presentation.ui.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usersListModule = module {
    viewModel { UsersListViewModel(get()) }
}