package gsg.corp.driver_data.remote.request

import com.squareup.moshi.Json

data class RouteDriverRequest(
    @field:Json(name = "id")val idDriver:Int
)