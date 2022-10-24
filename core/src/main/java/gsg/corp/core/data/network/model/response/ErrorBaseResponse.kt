package gsg.corp.core.data.network.model.response

import gsg.corp.core.data.network.MessageResponse

data class ErrorBaseResponse(
    val message: MessageResponse,
    val errors: List<ErrorResponse>,
)

data class ErrorResponse(
     val value: String? = null,
    val msg: String? = null,
     val param: String? = null,
     val location: String? = null,
)

data class ErrorData(
    val code: String,
    val httpStatus: String,
    val message: String,
    val details: List<String>,
)