package gsg.corp.core.data.network.model.response

import gsg.corp.core.util.UiText
import retrofit2.Response


sealed class Resource<T>(
    val data: T? = null,
    val message: UiText? = null,
    val response: Response<T>? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null, message: UiText, response: Response<T>? = null) :
        Resource<T>(data, message, response)

}