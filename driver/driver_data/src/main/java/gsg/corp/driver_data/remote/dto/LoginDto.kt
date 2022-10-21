package gsg.corp.driver_data.remote.dto

import com.squareup.moshi.Json

data class LoginDto(
    @field:Json(name = "user")
    var user: User,
    @field:Json(name = "token")
    var token: String
)