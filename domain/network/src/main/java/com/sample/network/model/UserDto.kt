package com.sample.network.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("username") val username : String,
    @SerializedName("email") val email : String,
    @SerializedName("address") val address : AddressDto,
    @SerializedName("phone") val phone : String,
    @SerializedName("website") val website : String,
    @SerializedName("company") val company : CompanyDto
)


data class AddressDto (

    @SerializedName("street") val street : String,
    @SerializedName("suite") val suite : String,
    @SerializedName("city") val city : String,
    @SerializedName("zipcode") val zipcode : String,
    @SerializedName("geo") val geo : GeoDto
)

data class CompanyDto (

    @SerializedName("name") val name : String,
    @SerializedName("catchPhrase") val catchPhrase : String,
    @SerializedName("bs") val bs : String
)

data class GeoDto (

    @SerializedName("lat") val lat : Double,
    @SerializedName("lng") val lng : Double
)