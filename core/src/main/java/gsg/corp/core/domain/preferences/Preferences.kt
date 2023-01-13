package gsg.corp.core.domain.preferences

import gsg.corp.core.domain.model.UserInfo

interface Preferences {


    fun saveUserName(username: String)
    suspend fun saveToken(token: String)

    fun saveUser(userInfo: UserInfo)
    fun loadUserInfo(): UserInfo

    fun saveCredentials(flk: Boolean)
    fun loadSaveCredentials(): Boolean

    fun getToken():String

    companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_ROLE = "role"
        const val KEY_USER_NAME = "username"
        const val KEY_SAVE_CREDENTIALS = "save_credentials"
        const val KEY_USER = "userinfo"

    }
}