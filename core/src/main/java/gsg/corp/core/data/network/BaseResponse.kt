package gsg.corp.core.data.network

data class BaseResponse<T>(
    val message: T? = null,
    val data: T? = null,
    val errors: T? = null,
)
