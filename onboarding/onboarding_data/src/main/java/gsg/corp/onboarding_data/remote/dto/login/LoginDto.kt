package gsg.corp.onboarding_data.remote.dto.login

import com.squareup.moshi.Json

data class LoginDto(
    @field:Json(name = "user")
    var user: User
)