package com.sample.users_list.domain.extension

import com.sample.network.model.AddressDto
import com.sample.network.model.CompanyDto
import com.sample.network.model.GeoDto
import com.sample.network.model.UserDto
import com.sample.users_list.api.Address
import com.sample.users_list.api.Company
import com.sample.users_list.api.Geo
import com.sample.users_list.api.User

fun UserDto.toUser() : User{
    return User(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        address = this.address.toAddress(),
        phone = this.phone,
        website = this.website,
        company = this.company.toCompany()
    )
}

fun AddressDto.toAddress() : Address {
    return Address(
        street = this.street,
        suite = this.suite,
        city = this.city,
        zipcode = this.zipcode,
        geo = this.geo.toGeo()
    )
}

fun CompanyDto.toCompany() : Company {
    return Company(
        name = this.name,
        catchPhrase = this.catchPhrase,
        bs = this.bs
    )
}

fun GeoDto.toGeo() : Geo{
    return Geo(lat = this.lat, lng = this.lng)
}

fun List<UserDto>.toUsers() : List<User> {
    return this.map { it.toUser() }
}