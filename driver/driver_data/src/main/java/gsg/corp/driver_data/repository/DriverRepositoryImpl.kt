package gsg.corp.driver_data.repository

import android.net.Uri
import android.util.Log
import com.squareup.moshi.Moshi
import gsg.corp.core.data.network.BaseNetwork
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.core.util.UiText
import gsg.corp.driver_data.mapper.toMetadataRequest
import gsg.corp.driver_data.mapper.toRouteDetail
import gsg.corp.driver_data.mapper.toRoutes
import gsg.corp.driver_data.mapper.toRoutesTypes
import gsg.corp.driver_data.remote.DriverApi
import gsg.corp.driver_data.remote.request.MetadataRequest
import gsg.corp.driver_domain.model.*
import gsg.corp.driver_domain.repository.DriverRepository
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

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
        routeDto.message
    }

    override suspend fun getRoute(): Resource<List<Route>> {
        return try {
            executeWithConnection {
                val routesDto = api.getRoutes()
                if (routesDto.isSuccessful) {
                    Resource.Success(data = routesDto.body()?.data?.toRoutes())
                } else {
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

    override suspend fun getRouteType(): Resource<List<RouteType>> {
        return try {
            executeWithConnection {
                val routesTypesDto = api.getRouteType()
                if (routesTypesDto.isSuccessful) {
                    Resource.Success(data = routesTypesDto.body()?.data?.toRoutesTypes())
                } else {
                    val errorMessage =
                        routesTypesDto.errorBody()?.string()?.let {
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

    override suspend fun getRouteDetail(idRoute: Int): Resource<RouteDetail> {
        return try {
            executeWithConnection {
                val routeDetailDto = api.getRouteDetail(idRoute)
                if (routeDetailDto.isSuccessful) {
                    Resource.Success(data = routeDetailDto.body()?.data?.toRouteDetail())
                } else {
                    val errorMessage =
                        routeDetailDto.errorBody()?.string()?.let {
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

    override suspend fun updateStateOrder(request: RouteStateRequest): Resource<GeneralResponse> {

        return try {
            executeWithConnection {
                val images = mutableListOf<MultipartBody.Part>()
                val fileStatus = File(request.photoStatus)
                if (fileStatus.exists()) {
                    val requestFile = fileStatus.asRequestBody("image/jpg".toMediaTypeOrNull())
                    val body = MultipartBody.Part.createFormData("images", "PHOTO_STATUS.${fileStatus.extension}", requestFile)
                    images.add(body)
                }
                val fileOtherStatus = File(request.otherPhotoStatus)
                if (fileOtherStatus.exists()) {
                    val requestFile = fileOtherStatus.asRequestBody("image/jpg".toMediaTypeOrNull())
                    val body = MultipartBody.Part.createFormData("images", "OTHER_PHOTO_STATUS.${fileOtherStatus.extension}", requestFile)
                    images.add(body)
                }


                val filePay = File(if(request.routesPayments.isNotEmpty()) request.routesPayments[0].pathPhotoPay else "")
                if (filePay.exists()) {
                    val requestFile = filePay.asRequestBody("image/jpg".toMediaTypeOrNull())
                    val body = MultipartBody.Part.createFormData("images", "PHOTO_PAY.${filePay.extension}", requestFile)
                    images.add(body)
                }

                val fileOtherPay = File(if(request.routesPayments.isNotEmpty() && request.routesPayments.size==2) request.routesPayments[1].pathPhotoPay else "")
                if (fileOtherPay.exists()) {
                    val requestFile = fileOtherPay.asRequestBody("image/jpg".toMediaTypeOrNull())
                    val body = MultipartBody.Part.createFormData("images", "OTHER_PHOTO_PAY.${fileOtherPay.extension}", requestFile)
                    images.add(body)
                }

                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(MetadataRequest::class.java)
                //RoutePayments
                val json = jsonAdapter.toJson(request.toMetadataRequest())

                val metadata = json.toRequestBody("application/json".toMediaTypeOrNull())

                val routeDto = api.uploadImagenes(metadata, images)
                if (routeDto.isSuccessful) {
                    // Borra el archivo temporal
                    if (fileStatus.exists()){
                        fileStatus.delete()
                    }
                    if (fileOtherStatus.exists()){
                        fileOtherStatus.delete()
                    }
                    if (filePay.exists()){
                        filePay.delete()
                    }
                    if (fileOtherPay.exists()){
                        fileOtherPay.delete()
                    }

                    Resource.Success(data = GeneralResponse( "estado actualizado corectamente"))
                } else {
                    val errorMessage =
                        routeDto.errorBody()?.string()?.let {
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