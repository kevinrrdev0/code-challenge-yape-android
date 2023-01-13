package gsg.corp.ruterito.interceptors

import gsg.corp.core.domain.preferences.Preferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val preferences: Preferences) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = runBlocking {
            preferences.getToken()
        }

        val newRequest = chain.request().newBuilder()

        val uri = request.url.toUri()
        val rawPath = uri.rawPath
        if (isValidPath(rawPath)){
            newRequest.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(newRequest.build())
    }

    private fun isValidPath(rawPath: String): Boolean {
        return when (rawPath) {
            "/api/auth/login" -> false
            else -> true
        }
    }

}