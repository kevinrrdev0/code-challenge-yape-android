package gsg.corp.core.domain.preferences

import gsg.corp.core.domain.model.UserInfo

interface Preferences {


    fun saveUserName(username:String)
    suspend fun saveToken(token: String)

    fun saveUser(username:String)
    fun loadUserInfo(): UserInfo

    fun saveCredentials(flk:Boolean)
    fun loadSaveCredentials(): Boolean

    companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
        const val KEY_ROLE = "role"
        const val KEY_USER_NAME = "username"
        const val KEY_SAVE_CREDENTIALS = "save_credentials"

    }
}