package gsg.corp.driver_data.remote

import gsg.corp.core.data.network.BaseResponse
import gsg.corp.core.util.STATUS_ROUTE_V3
import gsg.corp.driver_data.remote.dto.UploadDto
import gsg.corp.driver_data.remote.dto.route.ResponseRouteDetailDto
import gsg.corp.driver_data.remote.dto.route.RouteTypeDto
import gsg.corp.driver_data.remote.dto.route.RoutesDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface DriverApi {


    @Multipart
    @POST(STATUS_ROUTE_V3)
    suspend fun getUpload(@Part file: MultipartBody.Part,
                          @Part("id") id: RequestBody,
                          @Part("id_state") id_state: RequestBody
    ): UploadDto

    @GET(DRIVER_ROUTE_V2)
    suspend fun getRoutes(): Response<BaseResponse<RoutesDto>>

    @GET(DRIVER_ROUTE_AND_TYPE_ROUTE_V2)
    suspend fun getRouteType(): Response<BaseResponse<RouteTypeDto>>

    @GET(DRIVER_ROUTE_DETAIL_V2)
    suspend fun getRouteDetail(@Path("idRoute") idRoute: Int): Response<BaseResponse<ResponseRouteDetailDto>>

    companion object {
        const val DRIVER_ROUTE_V2 = "route/getRoutes/2/1"
        const val DRIVER_ROUTE_AND_TYPE_ROUTE_V2 = "route/getRoutes/2/1"
        const val DRIVER_ROUTE_DETAIL_V2 = "route/getRouteDetail/{idRoute}"
    }

}