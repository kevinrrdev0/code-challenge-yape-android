package gsg.corp.driver_data.remote

import gsg.corp.core.data.network.BaseResponse
import gsg.corp.core.util.ROUTE_DRIVER_V2
import gsg.corp.core.util.STATUS_ROUTE_V3
import gsg.corp.driver_data.remote.dto.UploadDto
import gsg.corp.driver_data.remote.dto.route.RouteDto
import gsg.corp.driver_data.remote.request.RouteDriverRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface DriverApi {

    @POST(ROUTE_DRIVER_V2)
    suspend fun getRoutes(@Body routeDriverRequest: RouteDriverRequest): RouteDto

    @Multipart
    @POST(STATUS_ROUTE_V3)
    suspend fun getUpload(@Part file: MultipartBody.Part,
                          @Part("id") id: RequestBody,
                          @Part("id_state") id_state: RequestBody
    ): UploadDto

    @GET(DRIVER_ROUTE_V2)
    suspend fun getRoute(): Response<BaseResponse<RouteDto>>

    companion object {
        const val DRIVER_ROUTE_V2 = "route/getExpressRoutes/1"
    }

}