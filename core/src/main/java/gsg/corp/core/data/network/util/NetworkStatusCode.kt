package gsg.corp.core.data.network.util


enum class NetworkStatusCode(val code: Int) {
    Unknown(0),

    OK(200),
    Created(201),
    Accepted(202),

    BadRequest(400),
    Unauthorized(401),
    PaymentRequired(402),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    NotAcceptable(406),
    ProxyAuthenticationRequired(407),
    RequestTimeout(408),
    Conflict(409),


    InternalServerError(500),
    NotImplemented(501),
    BadGateway(502),
    ServiceUnavailable(503),
    GatewayTimeout(504),
    HTTPVersionNotSupported(505),
    NotExtended(510),
    NetworkAuthenticationRequired(511);

    companion object {
        fun fromCode(code: Int) = values().find { it.code == code } ?: Unknown
    }

}