package gsg.corp.core.data.network

import gsg.corp.core.data.network.model.response.ErrorData

class ApiException(   val title: String,
                      var code: Int,
                      val description: String) : Exception()
class UserNotFoundException(mgs: String = "") : Exception(mgs)
class GenericException : Exception()

class NetworkException : Exception()

class UnstableNetworkException : Exception()
class UnAuthorizedException : Exception()
class NotFoundException : Exception()
class ClientServerException(val error: ErrorData) : Exception()

class ServerInternalException(val error: ErrorData) : Exception()

class ServerBodyException : Exception()
