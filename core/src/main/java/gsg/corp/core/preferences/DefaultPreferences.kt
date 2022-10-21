package gsg.corp.core.preferences

import gsg.corp.core.domain.model.UserInfo
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.core.util.PREFERENCES_TOKEN

class DefaultPreferences(
    private val preferenceManager: PreferenceManager,
) : Preferences {

    override fun saveUserName(username: String) =
        preferenceManager.setValue(Preferences.KEY_USER_NAME, username)

    override suspend fun saveToken(token: String) =
        preferenceManager.setValue(PREFERENCES_TOKEN, token)

    override fun saveUser(username: String) {
        TODO("Not yet implemented")
    }

    override fun loadUserInfo(): UserInfo {
        val id = preferenceManager.getInt(Preferences.KEY_ID)
        val name = preferenceManager.getString(Preferences.KEY_NAME)
        val username = preferenceManager.getString(Preferences.KEY_USER_NAME)
        return UserInfo(
            id,
            name,
            username
        )
    }

    override fun saveCredentials(flk: Boolean) {
        preferenceManager.setValue(Preferences.KEY_SAVE_CREDENTIALS, flk)
    }

    override fun loadSaveCredentials(): Boolean {
        return preferenceManager.getBoolean(Preferences.KEY_SAVE_CREDENTIALS)
    }
}