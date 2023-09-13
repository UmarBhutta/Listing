package com.sample.users_list.presentation.ui.data

import com.sample.users_list.api.User

fun User.toUiModel() : UserUiModel {
    return UserUiModel(
        id = this.id,
        name = this.name,
        email = this.email
    )
}

fun List<User>.toListUserUiModel(): List<UserUiModel> {
    return this.map { it.toUiModel() }
}