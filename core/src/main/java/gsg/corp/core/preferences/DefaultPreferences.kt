package gsg.corp.core.preferences

import com.google.gson.Gson
import gsg.corp.core.domain.model.UserInfo
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.core.domain.preferences.Preferences.Companion.KEY_SAVE_CREDENTIALS
import gsg.corp.core.domain.preferences.Preferences.Companion.KEY_USER
import gsg.corp.core.util.PREFERENCES_TOKEN

class DefaultPreferences(
    private val preferenceManager: PreferenceManager,
) : Preferences {

    override fun saveUserName(username: String) =
        preferenceManager.setValue(Preferences.KEY_USER_NAME, username)

    override suspend fun saveToken(token: String) =
        preferenceManager.setValue(PREFERENCES_TOKEN, token)

    override fun saveUser(userInfo: UserInfo) {
        saveUserInfo(userInfo)
    }

    override fun loadUserInfo(): UserInfo? {
        return Gson().fromJson(preferenceManager.getString(KEY_USER), UserInfo::class.java)
    }

    override fun saveCredentials(flk: Boolean) {
        preferenceManager.setValue(KEY_SAVE_CREDENTIALS, flk)
    }

    override fun loadSaveCredentials(): Boolean {
        return preferenceManager.getBoolean(KEY_SAVE_CREDENTIALS)
    }

    override fun getToken(): String = preferenceManager.getString(PREFERENCES_TOKEN)

    private fun saveUserInfo(userInfo: UserInfo) {
        val useAsString = Gson().toJson(userInfo)
        preferenceManager.setValue(KEY_USER, useAsString)
    }
}