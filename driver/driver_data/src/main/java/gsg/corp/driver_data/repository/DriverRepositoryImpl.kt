package gsg.corp.driver_data.repository

import android.net.Uri
import gsg.corp.core.data.network.BaseNetwork
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.core.util.UiText
import gsg.corp.driver_data.mapper.toRoute
import gsg.corp.driver_data.mapper.toUserInfo
import gsg.corp.driver_data.remote.DriverApi
import gsg.corp.driver_data.remote.request.RouteDriverRequest
import gsg.corp.driver_data.remote.request.VerificationRequest
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.UserInfo
import gsg.corp.driver_domain.repository.DriverRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import kotlin.Exception

class DriverRepositoryImpl(
    private val api: DriverApi, connectionUtils: ConnectionUtils
) : DriverRepository, BaseNetwork(connectionUtils) {

    override suspend fun verificationUser(user: String, password: String): Resource<UserInfo> {

        return try {
            executeWithConnection {
                val loginDto = api.verificationUser(VerificationRequest(user, password))
                if (loginDto.isSuccessful) {
                    Resource.Success(data = loginDto.body()?.data?.toUserInfo())
                } else {
                    val errorMessage =
                        loginDto.errorBody()?.string()?.let{
                            parseException(
                                it
                            )?.message?.description
                        } ?: run {
                            "Error server"
                        }
                    Resource.Error(
                        message = UiText.DynamicString(errorMessage)
                    )
                }
            }
        } catch (e: Exception) {
            Resource.Error(message = UiText.DynamicString(getConnectionException(e)))
        }
    }

    override suspend fun getRoutes(id: Int): Result<List<Route>> {
        return try {
            val routeDto = api.getRoutes(RouteDriverRequest(id))
            Result.success(routeDto.routes.map {
                it.toRoute()
            })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun updateRoute(file: File, uri: Uri, path: String) {

        val profileImage: RequestBody = RequestBody.create(
            "image/jpg".toMediaTypeOrNull(),
            file
        )

        val profileImageBody: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "file",
                file.name, profileImage
            )
        val id = getMultiPartFormRequestBody("1")
        val id_state = getMultiPartFormRequestBody("4")
        val routeDto = api.getUpload(profileImageBody, id, id_state)
        routeDto.codigo
    }

    fun getMultiPartFormRequestBody(tag: String?): RequestBody {
        return RequestBody.create(MultipartBody.FORM, tag!!)

    }
}