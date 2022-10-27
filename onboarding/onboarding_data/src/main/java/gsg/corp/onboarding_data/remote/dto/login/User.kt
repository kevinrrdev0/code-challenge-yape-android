package gsg.corp.onboarding_data.remote.dto.login

import com.squareup.moshi.Json


data class User(
    @field:Json(name = "uid")
    var uid: Int,
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "last_name_1")
    var lastName1: String,
    @field:Json(name = "last_name_2")
    var lastName2: String,
    @field:Json(name = "telephone")
    var telephone: String,
    @field:Json(name = "id_state")
    var idState: Int,
    @field:Json(name = "username")
    var username: String,
    @field:Json(name = "image")
    var image: String,
    @field:Json(name = "role")
    var role: String
)