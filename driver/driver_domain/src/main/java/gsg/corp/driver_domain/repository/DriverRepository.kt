package gsg.corp.driver_domain.repository

import android.net.Uri
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_domain.model.RouteType
import java.io.File

interface DriverRepository {

    suspend fun updateRoute(file: File, uri:Uri,path:String)

    suspend fun getRoute(): Resource<List<Route>>

    suspend fun getRouteType(): Resource<List<RouteType>>

    suspend fun getRouteDetail(idRoute:Int): Resource<RouteDetail>
}