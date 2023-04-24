package gsg.corp.onboarding_data.mapper

import gsg.corp.onboarding_data.remote.dto.login.LoginDto
import gsg.corp.onboarding_domain.model.UserInfo

fun LoginDto.toUserInfo(token:String): UserInfo {
    return UserInfo(
        id = user.uid,
        name = user.name,
        telephone = user.telephone,
        user = user.username,
        role = user.role,
        token = token,
        imageUrl = user.image
    )
}