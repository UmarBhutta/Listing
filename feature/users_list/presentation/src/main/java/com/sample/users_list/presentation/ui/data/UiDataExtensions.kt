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

fun User.toUserDetailsModel(): UserDetailsUiModel {
    return UserDetailsUiModel(
        name = this.name,
        email = this.email,
        website = this.website,
        companyDetails = this.company.name,
        address = this.address.simplifiedAddress()
    )
}