package gsg.corp.core.preferences

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.Flow


class PreferenceManager(preferencesName: String, context: Context) {
    private val masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    val encryptPreferences =
        EncryptedSharedPreferences.create(
            context,
            preferencesName,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    inline fun <reified T> getObserve(key: String, default: T): Flow<T> {
        return encryptPreferences.observeKey(key, default)
    }

    fun setValue(key: String, value: String) {
        encryptPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    fun setValue(key: String, value: Int) {
        encryptPreferences
            .edit()
            .putInt(key, value)
            .apply()
    }

    fun setValue(key: String, value: Boolean) {
        encryptPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun setValue(key: String, value: Float) {
        encryptPreferences
            .edit()
            .putFloat(key, value)
            .apply()
    }

    fun setValue(key: String, value: Long) {
        encryptPreferences
            .edit()
            .putLong(key, value)
            .apply()
    }

    fun getString(key: String): String {
        return encryptPreferences.getString(key, String()) ?: String()
    }

    fun getInt(key: String): Int {
        return encryptPreferences.getInt(key, 0)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return encryptPreferences.getBoolean(key, defaultValue)
    }

    fun getFloat(key: String): Float {
        return encryptPreferences.getFloat(key, 0f)
    }

    fun getLong(key: String): Long {
        return encryptPreferences.getLong(key, 0)
    }

    fun contains(key: String): Boolean {
        return encryptPreferences.contains(key)
    }

    fun remove(key: String) {
        encryptPreferences
            .edit()
            .remove(key)
            .apply()
    }

    fun clearData() {
        encryptPreferences.edit().clear().apply()
    }
}