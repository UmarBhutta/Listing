package com.sample.users_list.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.sample.common.AppTheme
import com.sample.users_list.presentation.R
import com.sample.users_list.presentation.ui.composable.UserListScreen
import org.koin.android.ext.android.inject

class UsersListActivity : AppCompatActivity() {

    private val usersListViewModel by inject<UsersListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                UserListScreen(usersListViewModel)
            }
        }
    }
}