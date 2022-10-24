package gsg.corp.core.data.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gsg.corp.core.R
import gsg.corp.core.data.network.model.response.ErrorBaseResponse
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.core.util.UiText
import java.net.SocketTimeoutException

open class BaseNetwork(private val connectionUtils: ConnectionUtils) {


    suspend fun <T> executeWithConnection(block: suspend () -> T): T {
        if (connectionUtils.isNetworkAvailable().not() || connectionUtils.isAirplaneModeActive()) {
            throw NetworkException()
        }
        if (connectionUtils.isNetworkLowBandWidth()) {
            throw UnstableNetworkException()
        }
        return block()
    }

    fun getConnectionException(e: Exception): String {
        return when (e) {
            is SocketTimeoutException -> {
                "¡Ups! Algo salió mal. Trabajaremos para solucionarlo de inmediato."
            }
            is NetworkException -> {
                "Por favor, revisa tu conexión y vuelve a intentarlo."
            }
            is UnstableNetworkException -> {
                "Tu conexión es inestable. Revísala y vuelve a intentarlo"
            }
            else -> {
                "¡Ups! Algo salió mal. Trabajaremos para solucionarlo de inmediato."
            }
        }
    }

    fun parseException(errorBody: String): ErrorBaseResponse? {
        return try {
            val baseResponse: ErrorBaseResponse = fromJson(errorBody)
            baseResponse
        } catch (e: Exception) {
            null
        }
    }

    private inline fun <reified T> fromJson(json: String?): T =
        Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)
}