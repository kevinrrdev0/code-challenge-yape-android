package gsg.corp.driver_data.repository

import android.net.Uri
import gsg.corp.core.data.network.BaseNetwork
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.core.util.UiText
import gsg.corp.driver_data.mapper.toRoutes
import gsg.corp.driver_data.remote.DriverApi
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.repository.DriverRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import kotlin.Exception

class DriverRepositoryImpl(
    private val api: DriverApi, connectionUtils: ConnectionUtils
) : DriverRepository, BaseNetwork(connectionUtils) {

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

    override suspend fun getRoute(): Resource<List<Route>> {
        return try {
            executeWithConnection {
                val routesDto = api.getRoute()
                if (routesDto.isSuccessful) {
                    Resource.Success(data = routesDto.body()?.data?.toRoutes())
                }else{
                    val errorMessage =
                        routesDto.errorBody()?.string()?.let {
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

    fun getMultiPartFormRequestBody(tag: String?): RequestBody {
        return RequestBody.create(MultipartBody.FORM, tag!!)

    }
}